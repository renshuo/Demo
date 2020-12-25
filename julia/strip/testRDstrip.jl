
include("RDStrip.jl")
import .Strip

using MAT
using Images, Plots


function testRDStrip(times)

    C = 300000000
    fc = 9.6e9
    Fs = 480e6
    Vr = 120.0
    delta_a = 1.0
    Br = 400e6
    tp = 15e-6
    H = 5e3
    Dr = 0.2
    Incidence = 20 * pi / 180
    SquintAngle = 3*pi/180

    Da = delta_a*2
    Ba = 2 * Vr / Da
    PRF = round(Ba*2/2)*1.5
    Kr = Br/tp
    Lambda = C/fc
    Theta_rag = 0.886*Lambda/Dr
    R_near = H / cos(Incidence - Theta_rag/2)
    R_near = 1326.2
    fdc = 2*Vr/Lambda*sin(SquintAngle)
    rRef = H / cos(Incidence)

    csa0 = matread("/home/work/csa_0.mat")
    Echo = get(csa0, "Srnm", -1)
    srnm = 0
    for i=1:times
        @time srnm = Strip.RDStrip(Echo, C, fc, Fs, PRF, Kr, tp, Da, R_near, Vr, fdc, rRef)
        matwrite("/home/work/srnmj.mat", Dict("srnm"=>srnm))
    end

    max, pq = findmax(abs.(srnm))
    p, q = pq.I
    z = srnm[p-32:p+31,q-32:q+31]
    zx = [-32:31]*C/2/Fs
    zy = [-32:31]*Vr/PRF
    Plots.plot(Images.Gray.(abs.(z)/max))

end
