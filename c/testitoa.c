#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void main(){
  char s[20];
  //s =(char*) malloc(10);
  int i=0;
  sprintf(s, "%d", i);
  printf("%s\n", s);
}
