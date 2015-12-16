#!/usr/bin/python3

# project euler: p 3

# the prime factors of 13195 are 5,7,13,29. what is the largest prime factor of the number 600851475143

import math

if __name__=="__main__":
    n = 600851475143;
#   n=13195
    i=2;
    res = i;
    while i<=math.sqrt(n):
        if n%i==0:
            n = n/i;
            res = i;
            print(res);
            i=2;
        else:
            i+=1;
    print(n);
    

