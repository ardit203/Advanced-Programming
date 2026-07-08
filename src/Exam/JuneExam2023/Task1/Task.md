To implement a class `DeliveryApp` that will model an application for ordering and delivering food from restaurants. In the class, implement the following methods:

* Constructor `DeliveryApp(String name)`

* Method `void registerDeliveryPerson(String id, String name, Location currentLocation)` – a method for registering an available delivery person who wants to work with the application.

* Method `void addRestaurant(String id, String name, Location location)` – a method for adding a restaurant that wants to enable delivery of items from its menu.

* Method `void addUser(String id, String name)` – a method for registering a user who wants to use the application for ordering and delivering food.

* Method `void addAddress(String id, String addressName, Location location)` – a method for adding an address to the user with ID `id`. One user can have multiple addresses (e.g. home, work, etc.).

* Method `void orderFood(String userId, String userAddressName, String restaurantId, float cost)` – a method for ordering food for the user with ID `userId` to their address `userAddressName` from the restaurant with ID `restaurantId`.

    * When processing the order, it is first necessary to find a delivery person who will deliver the order to the client. The order is assigned to the delivery person who is **closest to the restaurant**. In case there are multiple delivery persons equally close to the restaurant, choose the delivery person with the **smallest number of completed deliveries so far**.
    * After assigning the order to a specific delivery person, their **current location is changed** to the location of the client to whom the order is delivered.
    * The delivery person earns from the order such that they receive **90 denars per order**, and an **additional 10 denars for every 10 units of distance** from the restaurant to the client (e.g. if the distance is 35 units, then `90 + 3 × 10 = 120`).

* Method `void printUsers()` – a method that prints all users of the application **sorted in descending order** according to the **total amount spent** on food orders through the application.

* Method `void printRestaurants()` – a method that prints all registered restaurants in the application, **sorted in descending order** according to the **average price of orders** paid through the application.

* Method `void printDeliveryPeople()` – a method that prints all registered delivery people **sorted in descending order** according to the **total amount earned** from completed deliveries.

### Starter code
```java
import java.util.*;

/*
YOUR CODE HERE
DO NOT MODIFY THE interfaces and classes below!!!
*/

interface Location {
    int getX();

    int getY();

    default int distance(Location other) {
        int xDiff = Math.abs(getX() - other.getX());
        int yDiff = Math.abs(getY() - other.getY());
        return xDiff + yDiff;
    }
}

class LocationCreator {
    public static Location create(int x, int y) {

        return new Location() {
            @Override
            public int getX() {
                return x;
            }

            @Override
            public int getY() {
                return y;
            }
        };
    }
}

public class DeliveryAppTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String appName = sc.nextLine();
        DeliveryApp app = new DeliveryApp(appName);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            if (parts[0].equals("addUser")) {
                String id = parts[1];
                String name = parts[2];
                app.addUser(id, name);
            } else if (parts[0].equals("registerDeliveryPerson")) {
                String id = parts[1];
                String name = parts[2];
                int x = Integer.parseInt(parts[3]);
                int y = Integer.parseInt(parts[4]);
                app.registerDeliveryPerson(id, name, LocationCreator.create(x, y));
            } else if (parts[0].equals("addRestaurant")) {
                String id = parts[1];
                String name = parts[2];
                int x = Integer.parseInt(parts[3]);
                int y = Integer.parseInt(parts[4]);
                app.addRestaurant(id, name, LocationCreator.create(x, y));
            } else if (parts[0].equals("addAddress")) {
                String id = parts[1];
                String name = parts[2];
                int x = Integer.parseInt(parts[3]);
                int y = Integer.parseInt(parts[4]);
                app.addAddress(id, name, LocationCreator.create(x, y));
            } else if (parts[0].equals("orderFood")) {
                String userId = parts[1];
                String userAddressName = parts[2];
                String restaurantId = parts[3];
                float cost = Float.parseFloat(parts[4]);
                app.orderFood(userId, userAddressName, restaurantId, cost);
            } else if (parts[0].equals("printUsers")) {
                app.printUsers();
            } else if (parts[0].equals("printRestaurants")) {
                app.printRestaurants();
            } else {
                app.printDeliveryPeople();
            }

        }
    }
}
```