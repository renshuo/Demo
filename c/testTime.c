
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
  struct timeval tv;
  int i = gettimeofday(&tv,NULL);
  printf("time: %d: \n", i);
  
}
