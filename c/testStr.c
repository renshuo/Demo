#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*
  test string option.

*/

void printAry(const char* a, int size){
  printf("%s: ", a);
  for (int i=0; i<size; i++){
	printf("%c:%d", *(a+i), *(a+i));
  }
  printf("\n");
}

void main(){
  char* s;
  char sa[10] = {'a','b','c'};
  char sb[10] = {'a','b','c','\0'};
  char* sc = "abc";
  printAry(s, 1);
  printAry(&sa[0], 10);
  printAry(&sb[0], 10);
  printAry(sc, 10);
  
  //printf("%s, %s, %s, %s", s, sa ,sb, sc);
}

