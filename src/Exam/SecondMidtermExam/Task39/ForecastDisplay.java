package Exam.SecondMidtermExam.Task39;

public class ForecastDisplay implements Updatable, Displayable {
    private float currentPressure = 0.0F;
    private float lastPressure;
    private WeatherDispatcher dispatcher;

    public ForecastDisplay(WeatherDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.dispatcher.register(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.lastPressure = this.currentPressure;
        this.currentPressure = pressure;
        display();
    }

    @Override
    public void display() {
        String forecast;
        int compare = Float.compare(currentPressure, lastPressure);
        if (compare > 0) {
            forecast = "Improving";
        } else if (compare < 0) {
            forecast = "Cooler";
        } else {
            forecast = "Same";
        }

        System.out.printf("Forecast: %s\n", forecast);
    }
}