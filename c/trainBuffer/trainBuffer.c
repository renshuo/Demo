#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>

#include "trainBuffer.h"


initTrain(Train* t, int trainLen){
  t->trainLen = trainLen;
  int i;
  TrainBox* tb;
  for(i =0; i<trainLen; i++){
	tb =(TrainBox*) malloc(sizeof(TrainBox));
	if (t->writer==NULL){
	  t->writer = tb;
	  t->reader = tb;
	}else{
	  t->reader->next = tb;
	  t->reader =tb;
	}
  }
  t->reader->next = t->writer;
  t->reader = t->writer;
  printTrain(t);
}


printTrain(Train* train){
  Train t = *train;
  printf("Train buffer: len=%d, writer=%p, reader=%p\n", t.trainLen, t.writer, t.reader);
  int i=0;
  TrainBox* tb = t.writer;
  for (i=0; i<t.trainLen; i++){
	if (tb->filled){
	  printf("%p(%c..)->%p\t", tb, *(tb->buf), tb->next);
	}else{
	  printf("%p(_)->%p\t", tb, tb->next);
	}
	tb = tb->next;
  }
  printf("\n");
}


int main(){
  Train* t = malloc(sizeof(Train));
  initTrain(t, 3);
  t->writer->buf = "abc";
  t->writer->filled = 'd';
  t->writer = t->writer->next;
  printTrain(t);
  
}
