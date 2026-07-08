Implement a solution that enables displaying real-time weather conditions measured from a weather station.
For the needs of this solution, it is necessary to implement a class `WeatherDispatcher` whose purpose is to collect data for temperature, humidity, and pressure from the measuring station and distribute them to all entities that want to display them.

In this case, two such entities should be implemented: `CurrentConditionsDisplay` and `ForecastDisplay`.
These classes receive in their constructor the dispatcher whose data they want to display, and they display the data that the dispatcher has collected through the method
`public void setMeasurements(float temperature, float humidity, float pressure)`.

For `ForecastDisplay` the format is:

`Forecast: [Improving, Same, Cooler]`

`Improving` is printed if the current pressure is greater than the previously displayed one.
`Same` is printed if the current pressure is equal to the previously displayed one.
`Cooler` is printed if the current pressure is lower than the previously displayed one.
The initial pressure is set to the value `0.0`.

For `CurrentConditionsDisplay` the format is:

`Temperature: [current temperature]F`

`Humidity: [current humidity]%`

Additionally, the dispatcher implements methods for adding `register` and removing `remove` entities that will display its data.


Note: <span style="color:red;">Worst exercise of all it has to be solved only one way for it to pass the test cases.<spanp/>
### Starter code
```java
public class WeatherApplication {

    public static void main(String[] args) {
        WeatherDispatcher weatherDispatcher = new WeatherDispatcher();

        CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherDispatcher);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDispatcher);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            weatherDispatcher.setMeasurements(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
            if(parts.length > 3) {
                int operation = Integer.parseInt(parts[3]);
                if(operation==1) {
                    weatherDispatcher.remove(forecastDisplay);
                }
                if(operation==2) {
                    weatherDispatcher.remove(currentConditions);
                }
                if(operation==3) {
                    weatherDispatcher.register(forecastDisplay);
                }
                if(operation==4) {
                    weatherDispatcher.register(currentConditions);
                }

            }
        }
    }
}
```

### Solution
```java
// package Exam.SecondMidtermExam.Task39;

import java.util.*;

public class WeatherApplication {

    public static void main(String[] args) {
        WeatherDispatcher weatherDispatcher = new WeatherDispatcher();

        CurrentConditionsDisplay currentConditions = new CurrentConditionsDisplay(weatherDispatcher);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherDispatcher);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            weatherDispatcher.setMeasurements(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
            if (parts.length > 3) {
                int operation = Integer.parseInt(parts[3]);
                if (operation == 1) {
                    weatherDispatcher.remove(forecastDisplay);
                }
                if (operation == 2) {
                    weatherDispatcher.remove(currentConditions);
                }
                if (operation == 3) {
                    weatherDispatcher.register(forecastDisplay);
                }
                if (operation == 4) {
                    weatherDispatcher.register(currentConditions);
                }
            }
            System.out.println();
        }
    }
}

interface Updatable {
    void update(float temperature, float humidity, float pressure);
}

interface Subject {
    void register(Updatable o);

    void remove(Updatable o);

    void notifyUpdatable();
}

interface Displayable {
    void display();
}


class CurrentConditionsDisplay implements Updatable, Displayable {
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


class ForecastDisplay implements Updatable, Displayable {
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


class WeatherDispatcher implements Subject {
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
```