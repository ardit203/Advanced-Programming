package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Car;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CarsTest {
    static void main() {
        CarCollection carCollection = new CarCollection();
        TreeSet<String> manufacturers =  read(carCollection, System.in);
        carCollection.sortByPrice(true);
        System.out.println("=== Sorted By Price ASC ===");
        print(carCollection.getList());
        carCollection.sortByPrice(false);
        System.out.println("=== Sorted By Price DESC ===");
        print(carCollection.getList());
        System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturers.getFirst());
        List<Car> result = carCollection.filterByManufacturer(manufacturers.getFirst());
        print(result);
    }

    static void print(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    public static TreeSet<String> read(CarCollection collection, InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        TreeSet<String> treeSet = new TreeSet<>();
        br.lines().filter(Objects::nonNull)
                .forEach(l -> {
                    String[] tokens = l.split("\\s++");
                    String manufacturer = tokens[0];
                    String model = tokens[1];
                    int price = Integer.parseInt(tokens[2]);
                    float power = Float.parseFloat(tokens[3]);

                    collection.addCar(new Car(manufacturer, model, price, power));
                    treeSet.add(manufacturer);
                });
        return treeSet;
    }
}
