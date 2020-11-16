# Cellar

WIP: Don't expect any structure in there. As of now it's a collection of tryouts without particular shape

A raspberry based sensor to keep my wine cellar under control

## Hardware : 

* Raspberry (starting with a Raspberry 1)
* Maybe Piface
* DHT11 4 pins

## Readings


http://www.circuitbasics.com/how-to-set-up-the-dht11-humidity-sensor-on-the-raspberry-pi/
http://sebi.io/general/2018/08/13/Developing-for-Raspberry-Pi.html
https://medium.com/@menchukanton/setup-intellij-idea-for-remote-debugging-java-code-on-a-raspberry-pi-6e9df09dfb95
http://wiringpi.com/download-and-install/

https://github.com/cory-johannsen/gradle-jni-example

## Author 

[Julien Lengrand-Lambert](https://github.com/jlengrand)


pi/robottle
raspberrypi

ssh pi@raspberrypi.local


http://hirt.se/blog/?p=1116
https://github.com/fauna/faunadb-jvm/blob/master/docs/java.md

Stack Overflow Question : https://iot.stackexchange.com/questions/4662/in-what-form-should-i-store-data-in-the-cloud-for-a-single-device

# To run : 

$ mvn clean package

Location of service 

```
$ cat /etc/systemd/system/cellar.service
$ cp cellar.service /etc/systemd/system/cellar.service
$ systemctl daemon-reload # if modified
$ chmod 777 /etc/systemd/system/cellar.service
$ journalctl -u cellar.service -f
```