/*
 * SocketServer.c
 *
 *  Created on: 2014-11-4
 *      Author: sren
 */


#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>

#define MAXLINE 4096

FILE * file;

void fstartServer(int, FILE*);
void startServer(int, FILE*);

int main(int argc, char* argv[]){
	if (argc<2){
		printf("useage: \n\t SocketServer <port>\n");
	}else{
		int port = atoi(argv[1]);
		printf("get port: %d \n", port);
		char* filename = strcat(argv[1], ".txt");
		if ((file = fopen(filename, "w+"))==NULL){
		  printf("fail to open file %s\n", filename);
		}
		
		printf("asbc\n");
		startServer(port, file);
		printf("defg\n");
		fstartServer(port, file);
	}
	return 0;
}
void startServer(int i, FILE* f){
  printf("startServer");
}


void fstartServer(int port, FILE* file){
  printf("start server %d\n", port);
	int socket_fd, connect_fd;
	struct sockaddr_in servaddr;
	int n;
	socket_fd = socket(AF_INET, SOCK_STREAM, 0);

	servaddr.sin_family = AF_INET;
	servaddr.sin_port = htons(port);
	servaddr.sin_addr.s_addr = htonl(INADDR_ANY);

	bind(socket_fd, (struct sockaddr*) &servaddr, sizeof(servaddr));
	listen(socket_fd, 10);
	char buff[4096];
	while(1){
		if ((connect_fd=accept(socket_fd, (struct sockaddr*)NULL, NULL))==-1){
			printf("continue\n");
			continue;
		}
		printf("recv...\n");
		while(n>0){
		  n = recv(connect_fd, buff, MAXLINE, 0);
		  buff[n] = 0;
		int fx = fputs(&(buff[0]), file);
		printf("receive %s %d \n", buff, fx);
		fflush(file);
		}
		
	}
}

