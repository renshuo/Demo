"""
    taylorWin()

- Julia version: 
- Author: sren
- Date: 2020-12-09

# Arguments

# Examples

```jldoctest
julia>
```
"""
function taylorwin(n0, nbar, sll)
    n = convert(Int, n0)
    A = (acosh(10^(sll/20)) / pi)^2

    sf = zeros(nbar-1)
    ret = zeros(n)

    alpha =   (prod(collect(1:nbar-1)))^2
    beta = nbar / sqrt(A + (nbar - 0.5)^2)
    for i = 1:nbar-1
        sf[i] =  (prod(1 : nbar -1 + i)) * ( prod( 1 : nbar -1 - i))
        theta = 1.0
        for j = 1: nbar-1
          theta *= 1 - (i * i) / (beta * beta * (A + (j - 0.5) * (j - 0.5)))
        end
        sf[i] = alpha * theta/sf[i]
    end

    if n%2 == 1
        for i= 0 : n-1
            alpha = 0.0
            for j = 1 : nbar -1
                alpha += sf[j] * cos(2*pi*j*((i-((n-1)/2))/n))
            end
            ret[i+1] = 1+2*alpha
        end
    else 
        for i = 0:n-1
            alpha = 0
            for j = 1: nbar-1
                alpha += sf[j]*cos(pi*j*(2*(i-(n/2))+1)/n)
            end
            ret[i+1] = 1 + 2*alpha
        end
    end
    ret
end


#= ret = taylorwin(19, 4, 25)
println(ret) =#