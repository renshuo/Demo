#!/usr/bin/python

import ctypes
import socket
import time
import sys
import threading 

import config

size = 1024*1024*2
byteType = ctypes.c_byte*size
so = ''


def work(channel, ip, port):
    print "worker on channel ",channel
    clib = initCFunc();
    sock = initSocket(ip, port);
    
    buf = byteType();
    readed = 1;
    try:
        while True:
            readed = clib.reader(channel, buf, size);
            if readed>0:
                socketManager.sendData(channel, buf);
    finally:
        sock.close()
        print "socket is closed",sock


def initCFunc():
    so.init();
    return so;



def init():
    config.init();
    global so;
    so = ctypes.CDLL("./srio.so");
    

    
if __name__=='__main__':
    init();
    work(1, "127.0.0.1", 9000);
