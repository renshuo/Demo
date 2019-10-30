#include <stdio.h>
#include <stdlib.h>


void main(int argc, char* argv[]){

  fprintf(stdout, "%d__%d\n", 1, 2);
  //as same as:
  printf("%d__%d\n", 1, 2);
  
  
  char a[256];
  sprintf(a, "%d-%d=%d\n", 3,2,1);
  printf("%s", a);

  
}
