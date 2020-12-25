#=
RDStrip:
- Julia version: 1.5.3
- Author: sren
- Date: 2020-12-15
=#

module Strip

export RDStrip

# JULIA_CUDA_USE_BINARYBUILDER=false
ENV["JULIA_CUDA_USE_BINARYBUILDER"] = false
# JULIA_NUM_THREADS=40
ENV["JULIA_NUM_THREADS"] = 8
using MAT
using DSP
using Images
using Plots

useCUDA = false
if useCUDA
    println("use CUDA")
    using CUDA
    import CUDA.CUFFT.fft, CUDA.CUFFT.ifft, CUDA.CUFFT.fftshift
else
    println("use FFTW")
    import FFTW.fft, FFTW.fftshift, FFTW.ifft
end


include("taylorWin.jl")

function ft1(Nr, Srnm, Hshift)
    Threads.@threads for nrn = 1:Nr
        Srnm[nrn, :] = fft(Srnm[nrn, :] .* Hshift)
    end
end

function RDStrip(Srnm, C, fc, Fs, PRF, Kr, Tp, Da, R_taostart, Vr, fdc, Rref)
    println("start RD strip", (C, fc, Fs, PRF, Kr, Tp, Da, R_taostart, Vr, fdc, Rref))
    Nr, Na = size(Srnm)
    lambda = C/fc
    DeltaR = C/2/Fs

    Hshift = exp.(-1im*2*pi*fdc .* collect(0:Na-1) ./PRF)
    ft1(Nr, Srnm, Hshift)

    Br = abs(Kr*Tp)
    Ba = 2*Vr/Da
    Nrb = ceil(Br/(Fs/Nr)/2)*2
    Nab = ceil(Ba/(PRF/Na)/2)*2
    RWin = zeros(Nr, 1)
    AWin = zeros(1, Na)
    WinFlag = 2

    RWin[(convert(Int,-Nrb/2):convert(Int,Nrb/2-1)) .+ convert(Int,Nr/2)] = taylorwin(Nrb, 4, 25)
    AWin[(convert(Int,-Nab/2):convert(Int,Nab/2-1)) .+ convert(Int,Na/2)] = taylorwin(Nab, 4, 25)'

    RWin = fftshift(RWin)
    AWin = fftshift(AWin)

    Ntr = round(Tp*Fs/2)*2
    squint_angle = asin(lambda*fdc/2/Vr) #       % 斜视角
    Rc = R_taostart + (Nr-Ntr)/2*DeltaR
    tc = Rc*tan(squint_angle)/Vr
    Rc = Rc*cos(squint_angle) #                   % 场景中心最短斜距
    Ta = 0.886*lambda/Da*Rref/Vr/cos(squint_angle)
    Nta = round(Ta*PRF/2)*2

    fa = collect(-Na/2:Na/2-1) ./Na*PRF .+ fdc #             % Fdc_image = 0成像到零多普勒
    fa = fftshift(fa)
    sin_theta = lambda*fa ./2 ./Vr
    cos_theta = sqrt.(1 .- sin_theta.^2)

    println("距离压缩与徙动校正")
    fr = collect(-Nr/2:Nr/2-1)./Nr*Fs
    fr = fftshift(fr)

    Threads.@threads for nan = 1:Na
        Filter = (exp.(1im*pi .* fr.^2 ./(Kr./(1-Kr*Rc*2*lambda/C/C*sin_theta[nan]^2/cos_theta[nan]^3)))).*RWin
        RCM = exp.(1im*4*pi/C .* fr*Rc*(1/cos_theta[nan] - 1)) 
        Srnm[:, nan] = ifft(Srnm[:,nan].*Filter.*RCM)
    end

    Srnm = Srnm[ convert(Int, Ntr/2+1):convert(Int,Nr-Ntr/2), :]
    Nr = convert(Int, Nr-Ntr)

    R = R_taostart .+ collect(0:Nr-1)*DeltaR
    Threads.@threads for nrn=1:Nr
        Filter = exp.(1im*4*pi*R[nrn]/lambda*(sqrt.(1 .-(lambda.*fa./2 ./Vr).^2).-1)).*exp.(1im*2*pi*fa*tc).*AWin'
        Srnm[nrn, :] = ifft(Srnm[nrn, :].*Filter)
    end

    Srnm = Srnm[:, convert(Int,Nta/2+1):convert(Int,Na-Nta/2)]
end


end
