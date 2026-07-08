package Exam.SecondMidtermExam.Task39;

public class CurrentConditionsDisplay implements Updatable, Displayable {
    private WeatherDispatcher dispatcher;
    private float temperature;
    private float humidity;


    public CurrentConditionsDisplay(WeatherDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.dispatcher.register(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.printf("Temperature: %.1fF\nHumidity: %.1f%%\n", temperature, humidity);
    }
}