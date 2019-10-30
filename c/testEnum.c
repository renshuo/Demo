#include <stdlib.h>
#include <stdio.h>

enum Cor{
  mon =1,
  tue =2,
  wen =3,
thr=4,
  fri=5,
  sat = 6,
  sun=7};

void main(){

  enum Cor today = 1;
  
  printf("%d", today);

  switch(today){
  case mon: printf("monday\n");
  case tue: printf("tuesday\n");
  case wen: printf("wensday\n");
  default: printf("noday");
  }

}
  
