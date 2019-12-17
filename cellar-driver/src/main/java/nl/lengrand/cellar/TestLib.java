package nl.lengrand.cellar;

import nl.lengrand.cellar.Dht11Driver;

import java.util.concurrent.ExecutionException;

public class TestLib {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Dht11Driver theDriver = new Dht11Driver();
        theDriver.sayHello();

        Thread.sleep(1000);

        for (int i = 0; i < 5; i++) {
            float [] res = theDriver.getTemperatureAndHumidity();
            if(res[0] != 0 && res[1] != 0){
                System.out.println("Receiving " + res[0] + " " + res[1]);
            }
            else{
                System.out.println("Incorrect data!" + res[0] + " " + res[1]);
            }
            Thread.sleep(10000);
        }
    }
}