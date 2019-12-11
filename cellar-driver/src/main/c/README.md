The code contained in the adafruit folder comes from https://github.com/adafruit/Adafruit_Python_DHT and will be used as driver

To run right now : 

* Create h file from Java folder : javac -h . Dht11Driver.java
* Make (check converted to tabs)
* cp .so file in java folder
* pi@raspberrypi:~/projects/cellar/cellar-driver/src/main/java $ javac Dht11Driver.java TestLib.java
* pi@raspberrypi:~/projects/cellar/cellar-driver/src/main/java $ java -Djava.library.path=. TestLib