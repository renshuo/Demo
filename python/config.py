#!/usr/bin/python

import ConfigParser


global cf;
cf = ConfigParser.ConfigParser();
cf.read("hxJkbOut.conf");

    

def getIport(channel):
    iportStr = cf.get("iport", 'iport'+str(channel));
    iport = iportStr.split(':');
    return (iport[0], int(iport[1]))


if __name__=='__main__':
    print getIport(1);
