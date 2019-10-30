#!/usr/bin/python3

import socket 
import time



ip = '192.169.1.21';
port = 8000;

socket=socket.socket(socket.AF_INET, socket.SOCK_STREAM);
print("socket %d",socket);
try:
    socket.bind((ip, port));
except OSError:
    printf("get error", OSError);
else:
    print("socket bind: ", ip, port);
    
socket.listen(10);
while True:
    print("wait connection");
    connection,address=socket.accept();
    connection.settimeout(1);
    while True:
        try:
            connection.send(b'\x01\x00\x00\x10\x00\x10\x00\x10abc');
        except BrokenPipeError:
            print('connection closed? pipe error.');
            break;
        buf = connection.recv(128);
        print("buf %s" , buf);
        time.sleep(1);

    
connection.close();
socket.close();

