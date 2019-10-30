#include <stdlib.h>
#include <stdio.h>

#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>


int port = 20000;
char* ip = "192.169.1.239";

void main(){
  int sockfd, n ,connfd;
  struct sockaddr_in servadr;
  servadr.sin_family = AF_INET;
  servadr.sin_port = htons(port);
  servadr.sin_addr.s_addr = inet_addr(ip);

  char* buf = "abcv123";
  int len = 3;
  int ret = sendto(sockfd, buf, len, 0,(struct sockaddr*) (&servadr), sizeof(servadr));
  printf("sended %d", ret);
}
