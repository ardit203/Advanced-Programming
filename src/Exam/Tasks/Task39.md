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
