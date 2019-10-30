#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>


void main(){
  int8_t int8=1;
  int16_t int16 = 2;
  int32_t i32= 3;
  int64_t i64 = 4;
  
  int64_t* p=&i64;

  printf("int8, size=%d", sizeof(int8));

  printf("int16, size=%d", sizeof(int16));
  printf("int32, size=%d", sizeof(i32));

  printf("int64, size=%d", sizeof(i64));

  printf("ptr: size=%d", sizeof(p));

}
