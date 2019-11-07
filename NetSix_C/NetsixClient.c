#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netdb.h>

#define PORT 39999
#define MAXBUF 1024

int main(int argc, char** argv){

    if(argc != 3){
        printf("Usage %s: ip-server [title,episode]\n", argv[0]);
        exit(-1);
    }

    int sock, retcode = 0;
    struct sockaddr_in remoteHost;
    char* msg = argv[2];
    char answer[100];

    if((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("socket()\n");
        exit(-1);
    }

    memset(&remoteHost, 0, sizeof(remoteHost));
    remoteHost.sin_family = AF_INET;
    remoteHost.sin_addr.s_addr = inet_addr(argv[1]);
    remoteHost.sin_port = htons(PORT);

    if((connect(sock, (struct sockaddr *)&remoteHost, sizeof(remoteHost))) < 0){
        perror("connect()\n");
        exit(-1);
    }

    if((retcode = write(sock, msg, strlen(msg)+1)) < 0){
        perror("write()\n");
        exit(-1);
    }

    printf("---Sent %d bytes---\n", retcode);

    if((retcode = read(sock, answer, 100)) < 0){
        perror("read()\n");
        exit(-1);
    }

    printf("---Received %d bytes---\nAnswer: %s\n", retcode, answer);

    close(sock);
}