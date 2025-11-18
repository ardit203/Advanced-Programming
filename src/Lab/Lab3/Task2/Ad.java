package Lab.Lab3.Task2;


public class Ad implements Comparable<Ad> {
    private String id;
    private String category;
    private double bidValue;
    private double ctr;
    private String content;
    private double totalScore;

    public Ad(String id, String category, double bidValue, double ctr, String content) {
        this.id = id;
        this.category = category;
        this.bidValue = bidValue;
        this.ctr = ctr;
        this.content = content;
        totalScore = 0.0;
    }

    public static Ad createAd(String line) {
        String[] p = line.trim().split("\\s+", 5);
        String id = p[0];
        String category = p[1];
        double bid = Double.parseDouble(p[2]);
        double ctr = Double.parseDouble(p[3]);
        String content = (p.length == 5) ? p[4] : "";
        return new Ad(id, category, bid, ctr, content);
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getBidValue() {
        return bidValue;
    }

    public double getCtr() {
        return ctr;
    }

    public String getContent() {
        return content;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return String.format("%s %s (bid=%.2f, ctr=%.2f%%) %s", id, category, bidValue, ctr*100, content);
    }

    @Override
    public int compareTo(Ad other) {
        int byBid = Double.compare(other.bidValue, this.bidValue);
        return byBid != 0 ? byBid : this.id.compareTo(other.id);
//        return Comparator.comparingDouble(Ad::getBidValue).reversed().thenComparing(Ad::getId).compare(this, ad);
    }
}
