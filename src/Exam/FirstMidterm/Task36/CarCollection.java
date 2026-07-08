package Exam.FirstMidterm.Task36;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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