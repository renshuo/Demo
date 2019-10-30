//( test c code

#include <stdlib.h>
#include <stdio.h>

int main(int argc, char* args[]){
  printf("get %d args: ", argc);
  int i;
  for(i=0; i<argc; i++){
	printf("%d:%s,",i, args[i]);
  }

  
  return 0;
}


/* test result:
# ./test1
get 1 args: ./test1,%                                                                                                         # ./test1 a
get 2 args: ./test1,a,%                                                                                                       # ./test1 a ab
get 3 args: ./test1,a,ab,%                                                                                                    # 

*/
