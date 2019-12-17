package nl.lengrand;

public class SensorValue {

    public enum READING{
        OK, ERROR
    }

    private READING reading;
    private float temperature;
    private float humidity;

    public SensorValue(float[] values){
        if(values[0] == 0 && values[1] == 0){
            this.reading = READING.ERROR;
            this.temperature = 0;
            this.humidity = 0;
        }
        else{
            this.reading = READING.OK;
            this.temperature = values[0];
            this.humidity = values[1];
        }
    }

    public READING getReading() {
        return reading;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }
}
