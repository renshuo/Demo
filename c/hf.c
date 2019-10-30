#include <stdio.h>
#include <stdlib.h>

//struct atom;

typedef struct atom{
  struct atom* prv;
  void* data;
  struct atom* nex;
}atom;


void printAtom(atom* d){
  printf("%d ", d->data);
} 

void main(){
  int a = 1;
  atom a1 = {
	.data = &a
  };

  int b = 2;
  atom b1 = {
	.data = &b
  };

  a1.nex = &b1;
  b1.prv = &a1;

  
}
