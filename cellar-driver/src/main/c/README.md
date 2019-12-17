The code contained in the adafruit folder comes from https://github.com/adafruit/Adafruit_Python_DHT and will be used as driver

To run right now : 

* Create h file from Java folder : javac -h . nl.lengrand.cellar.Dht11Driver.java
* Make (check converted to tabs)
* cp .so file in java folder
* pi@raspberrypi:~/projects/cellar/cellar-driver/src/main/java $ javac nl.lengrand.cellar.Dht11Driver.java nl.lengrand.cellar.TestLib.java
* pi@raspberrypi:~/projects/cellar/cellar-driver/src/main/java $ java -Djava.library.path=. nl.lengrand.cellar.TestLib
* java -Djava.library.path=../src/main/c -jar cellar-driver-1.0-SNAPSHOT-jar-with-dependencies.jar