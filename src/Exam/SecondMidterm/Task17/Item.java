package Exam.SecondMidterm.Task17;

public class Item {
    private int price;
    private double taxReturn;

    public Item(int price, double taxReturn){
        this.price = price;
        this.taxReturn = taxReturn;
    }


    public int getPrice() {
        return price;
    }

    public double getTaxReturn() {
        return taxReturn;
    }
}
