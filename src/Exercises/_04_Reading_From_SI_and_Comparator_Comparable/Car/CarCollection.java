package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarCollection {
    private List<Car> cars;

    public CarCollection() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void sortByPrice(boolean ascending) {
        if (ascending) {
            cars = cars.stream()
                    .sorted(Comparator.comparingInt(Car::getPrice).thenComparingDouble(Car::getPower))
                    .collect(Collectors.toList());
        } else {
            cars = cars.stream()
                    .sorted(Comparator.comparingInt(Car::getPrice).thenComparingDouble(Car::getPower).reversed())
                    .collect(Collectors.toList());
        }
    }

    public List<Car> filterByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equals(manufacturer))
                .collect(Collectors.toList());
    }

    public List<Car> getList() {
        return cars;
    }
}
