package Lab.Lab3.Task2;

class Ad implements Comparable<Ad> {
    private String id;
    private String category;
    private double bidValue;
    private double ctr;
    private String content;

    public Ad(String id, String category, double bidValue, double ctr, String content) {
        this.id = id;
        this.category = category;
        this.bidValue = bidValue;
        this.ctr = ctr;
        this.content = content;
    }

    @Override
    public String toString() {
        //AD001 tech (bid=2.50, ctr=12.00%) Amazing new phone
        return String.format("%s %s (bid=%.2f, ctr=%.2f%%) %s", id, category, bidValue, ctr * 100.0, content);
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public double getBidValue() {
        return bidValue;
    }

    public double getCtr() {
        return ctr;
    }

    @Override
    public int compareTo(Ad o) {
        int byBid = Double.compare(o.bidValue, this.bidValue);
        return byBid != 0 ? byBid : this.id.compareTo(o.id);
    }
}
