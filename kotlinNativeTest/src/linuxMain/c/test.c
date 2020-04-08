    #include "test.h"
    #include "stdio.h"
    　
     void printHello(){
       printf("hello in c code.");
     }
     　
     int factorial(int n){
         printf("[C]calc factorial: %d\n", n);
         if(n == 0) return 1;
         return n * factorial(n - 1);
     }
