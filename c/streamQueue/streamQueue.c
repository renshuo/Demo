

#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>

#include "streamQueue.h"

void initSQueue(sQueue* q){
  q->head=0;
  q->tail=0;
}

int pushc(sQueue* q, uint8_t d){
  int next = q->head+1;
  if (next>QUEUE_LEN){
	next = 0;
  }
  if (next==q->tail){
	return 0;
  }

  q->data[q->head] = d;
  q->head++;
  if (q->head>QUEUE_LEN){
	q->head = 0;
  }
  return 1;
}


int popc(sQueue* q, uint8_t* buf){
  if (q->tail  == q->head){
	return 0;
  }
  *buf = q->data[q->tail];
  q->tail++;
  return 1;
}
  
int peekc(sQueue* q, uint8_t* buf){
  int cur = q->tail + 1;
  if (cur == q->head){
	return 0;
  }
  *buf = q->data[cur];
  return 1;
}

int getQueueSize(sQueue* q){
  return q->head - q->tail;
}


int push(sQueue* q, uint8_t* data, int size){
  int i;
  for(i=0; i< size; i++){
	int s = pushc(q, data[i]);
	if (s==0){
	  break;
	}
  }
  return i;
}

int pop(sQueue* q, uint8_t* buf, int size){
  
  return 0;
}

int peek(sQueue* q, uint8_t* buf, int size){
  return 0;
}

int clean(sQueue* q){
  return 0;
}

void dumpQueue(char* text, sQueue* q){
  int head = q->head;
  int tail = q->tail;
  printf("Queue:size=%d, head(%d), tail(%d): %s\n", getQueueSize(q), q->head, q->tail, text);
  int line = 0;
  if (head>tail){
	for(int i=tail; i<head; i++){
	  printf("%2X ", q->data[i]);
	  line ++;
	  if (line%16==0){
		printf("\n");
	  }
	}
  }else if (head<tail){
	for (int i=tail; i<QUEUE_LEN; i++){
	  printf("%2X ", q->data[i]);
	  line ++;
	  if (line%16==0){
		printf("\n");
	  }
	}
	for (int i=0; i<head; i++){
	  printf("%2X ", q->data[i]);
	  line ++;
	  if (line%16==0){
		printf("\n");
	  }
	}
  }else{
	printf("%2X ", q->data[head]);
	  line ++;
	  if (line%16==0){
		printf("\n");
	  }
  }
  
  printf("\n" );
  
}

int main(){
  sQueue q;
  initSQueue(&q);
  int i = pushc(&q, 64);
  i+=pushc(&q, 2);
  dumpQueue("pushc tiwce", &q);

  i=0;
  uint8_t d;
  i  += popc(&q, &d);
  dumpQueue("pop 1", &q);
  printf("popd %d %2X\n", i, d);
  

  i  += popc(&q, &d);
  dumpQueue("pop 1", &q);
  printf("popd %d %2X\n", i, d);

}
