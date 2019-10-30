#include <stdio.h>
#include <stdlib.h>

#define QUEUE_LEN 1000000

typedef struct sQueue{
  uint8_t data[QUEUE_LEN];
  int head;
  int tail;
}sQueue;


void initSQueue(sQueue* q);

int pushc(sQueue* q, uint8_t d);
int popc(sQueue* q, uint8_t* buf);
int peekc(sQueue* q, uint8_t* buf);

int getQueueSize(sQueue* q);

int push(sQueue* q, uint8_t* data, int size);
int pop(sQueue* q, uint8_t* buf, int size);
int peek(sQueue* q, uint8_t* buf, int size);

int clean(sQueue* q);

void dumpQueue(char* text, sQueue* q);
