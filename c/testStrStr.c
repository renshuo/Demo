#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void main(){

  char * a = "abcdef";
  char * b = "def";
  char* p = strstr(a ,b);
  printf("%c", *p);
}

