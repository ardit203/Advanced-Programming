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


### Starter code
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CarTest {
	public static void main(String[] args) {
		CarCollection carCollection = new CarCollection();
		String manufacturer = fillCollection(carCollection);
		carCollection.sortByPrice(true);
		System.out.println("=== Sorted By Price ASC ===");
		print(carCollection.getList());
		carCollection.sortByPrice(false);
		System.out.println("=== Sorted By Price DESC ===");
		print(carCollection.getList());
		System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturer);
		List<Car> result = carCollection.filterByManufacturer(manufacturer);
		print(result);
	}

	static void print(List<Car> cars) {
		for (Car c : cars) {
			System.out.println(c);
		}
	}

	static String fillCollection(CarCollection cc) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
            if(parts.length < 4) return parts[0];
			Car car = new Car(parts[0], parts[1], Integer.parseInt(parts[2]),
					Float.parseFloat(parts[3]));
			cc.addCar(car);
		}
        scanner.close();
		return "";
	}
}
```

### Solution
```java
import java.util.*;
import java.util.stream.Collectors;


class Car {
    private String manufacturer;
    private String model;
    private int price;
    private float power;


    public Car(String manufacturer, String model, int price, float power) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.power = power;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public float getPower() {
        return power;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%.0fKW) %d", manufacturer, model, power, price);
    }
}


class CarCollection {
    private List<Car> cars;

    public CarCollection() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void sortByPrice(boolean ascending) {
        Comparator<Car> comparator = Comparator.comparing(Car::getPrice).thenComparing(Car::getPower);
        comparator = ascending ? comparator : comparator.reversed();

        cars = cars.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }


    public List<Car> getList() {
        return cars;
    }

    public List<Car> filterByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(car -> car.getManufacturer().equalsIgnoreCase(manufacturer))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }
}

public class CarTest {
    public static void main(String[] args) {
        CarCollection carCollection = new CarCollection();
        String manufacturer = fillCollection(carCollection);
        carCollection.sortByPrice(true);
        System.out.println("=== Sorted By Price ASC ===");
        print(carCollection.getList());
        carCollection.sortByPrice(false);
        System.out.println("=== Sorted By Price DESC ===");
        print(carCollection.getList());
        System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturer);
        List<Car> result = carCollection.filterByManufacturer(manufacturer);
        print(result);
    }

    static void print(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    static String fillCollection(CarCollection cc) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts.length < 4) return parts[0];
            Car car = new Car(parts[0], parts[1], Integer.parseInt(parts[2]),
                    Float.parseFloat(parts[3]));
            cc.addCar(car);
        }
        scanner.close();
        return "";
    }
}
```