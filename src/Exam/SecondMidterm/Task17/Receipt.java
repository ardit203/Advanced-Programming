package Exam.SecondMidterm.Task17;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private int id;
    private List<Item> items;

    public Receipt(int id){
        this.id = id;
        this.items = new ArrayList<>();
    }


    public void addItem(int price, double taxReturn){
        items.add(new Item(price, taxReturn));
    }

    public int getId() {
        return id;
    }

    public int sum(){
        return items.stream().mapToInt(Item::getPrice).sum();
    }

    public double taxReturn(){
        return items.stream().mapToDouble(Item::getTaxReturn).sum();
    }

    @Override
    public String toString() {
        return String.format("%10d\t%10d\t%10.5f", id, sum(), taxReturn());
    }
}
