#!/bin/sh

while ! /usr/bin/iwctl device list | grep wlan0; do
    /bin/true
done

/usr/bin/iwctl device wlan0 set-property Mode ap
/usr/bin/iwctl ap wlan0 start bla 12345678
