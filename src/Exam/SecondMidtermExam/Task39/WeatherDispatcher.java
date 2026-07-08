package Exam.SecondMidtermExam.Task39;

import java.util.HashSet;
import java.util.Set;

public class WeatherDispatcher implements Subject {
    private Set<Updatable> updatables;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherDispatcher() {
        this.updatables = new HashSet<>();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        notifyUpdatable();
    }


    public void register(Updatable o) {
        updatables.add(o);
    }


    public void remove(Updatable o) {
        updatables.remove(o);
    }


    public void notifyUpdatable() {
        for (Updatable updatable : updatables) {
            updatable.update(temperature, humidity, pressure);
        }
    }
}