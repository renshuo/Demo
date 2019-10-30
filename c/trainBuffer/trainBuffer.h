
#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>





typedef struct TrainBox{
  uint8_t* buf;
  struct TrainBox* next;
  uint8_t filled;
}TrainBox;

typedef struct Train{
  int trainLen;

  struct TrainBox* writer;
  struct TrainBox* reader;
}Train;

initTrain(Train* t, int trainLen);



printTrain(Train* t);
