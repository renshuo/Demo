#!/usr/bin/python

import socket
import time
import sys
import threading

import config

conns = [0,0,0,0, 0,0,0,0, 0];




def getSocket(channel):
    con = conns[channel];
    if con==0 :
        (ip,port) = config.getIport(channel);
        con = initSocket(ip, port);
        conns[channel] = con;
    print "get socket:", con;
    return con;


def closeSock(channel):
    con = conns[channel];
    if con!=0:
        con.close();
        print "close socket: ", con
        conns[channel]=0;

def sendData(channel, data):
    try:
        conn = getSocket(channel);
        conn.send(buf);
    except:
        print "get error"
        closeSock(channel);
    

def initSocket(ip, port):
    sock=socket.socket(socket.AF_INET, socket.SOCK_STREAM);
    print "connec to ",ip,port," success, socket conn: ", sock
    sock.connect((ip, port));
    return sock;




if __name__=='__main__':
    txt = open("/home/star/in.dat");
    channel = 2;
    try:
        while True:
            conn = getSocket(channel);
            time.sleep(1);
            conn.send(txt.read(1024*1024));
    except:
        print "get error"
    finally:
        closeSock(channel);

