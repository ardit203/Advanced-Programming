package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Car;

public class Car {
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
        return String.format("Manufacturer: %s - Model: %s - Price: %d - Power: %.2f", manufacturer, model, price, power);
    }
}
