package Exercises._05_Intro_to_Maps.Media;

import java.util.Comparator;

public class License implements Comparable<License>, Quantifiable {
    private String title;
    private String category;
    private String region;
    private int units;
    private double ratePerUnit;

    public License(String title, String category, String region, int units, double ratePerUnit) {
        this.title = title;
        this.category = category;
        this.region = region;
        this.units = Math.max(units, 0);
        this.ratePerUnit = Math.max(ratePerUnit, 0);
    }

    public String category(){
        return category;
    }

    public String title(){
        return title;
    }


    @Override
    public double amount() {
        return units * ratePerUnit;
    }

    @Override
    public int compareTo(License other) {
        return Comparator.comparingDouble(License::amount)
                .thenComparing(l -> l.category)
                .thenComparing(l -> l.title)
                .compare(this, other);
    }

    @Override
    public String toString() {
        return String.format("%s [%s|%s] units=%d rp=%.2f total=%.2f"
                , title, category, region, units, ratePerUnit, amount());
    }
}
