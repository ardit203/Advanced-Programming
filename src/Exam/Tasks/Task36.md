Write a class for a car **`Car`** in which the following are stored:

* manufacturer
* model
* price
* power.

Implement a constructor with the following arguments:
`Car(String manufacturer, String model, int price, float power)`.

Then write a class **`CarCollection`** in which a collection of cars is stored. In this class, the following methods should be implemented:

* `public void addCar(Car car)` – adds a car to the collection
* `public void sortByPrice(boolean ascending)` – sorts the collection by the price of the car (in ascending order if the argument `ascending` is `true`, otherwise in descending order). If the price of the cars is the same, the sorting should be done according to their power.
* `public List<Car> filterByManufacturer(String manufacturer)` – returns a list of cars from a given manufacturer (the comparison is by the name of the manufacturer, ignoring upper and lower case). The cars in this list should be sorted by model in ascending order.
* `public List<Car> getList()` – returns the list of cars from the collection.
