package Exam.SecondMidtermExam.Task47;

import java.time.LocalDateTime;

class Product {
    private String category;
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private double price;
    private int sales;

    public Product(String category, String id, String name, LocalDateTime createdAt, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        this.sales = 0;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getPrice() {
        return price;
    }

    public int getSales() {
        return sales;
    }

    public void incrementSales(int quantity) {
        sales += quantity;
    }

//    @Override
//    public String toString() {
//        //Product{id='050be27b', name='product0', createdAt=2019-01-14T23:17:46.715710, price=2913.14, quantitySold=14}
//        return String.format("Product{id='%s', name='%s', createdAt=%s, price=%f, quantitySold=%d}",
//                id, name, createdAt, price, sales);
//    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold=" + sales +
                '}';
    }
}