package Lab.Lab3.Task2;

public class AdRequest {
    private String id;
    private String category;
    private double floorBid;
    private String keywords;

    public AdRequest(String id, String category, double floorBid, String keywords) {
        this.id = id;
        this.category = category;
        this.floorBid = floorBid;
        this.keywords = keywords;
    }

    public static AdRequest createReq(String line){
        String[] parts = line.trim().split("\\s+", 4);
        String id = parts[0];
        String category = parts[1];
        double floorBid = Double.parseDouble(parts[2]);
        String keywords = (parts.length == 4) ? parts[3] : "";

        return new AdRequest(id, category, floorBid, keywords);
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getFloorBid() {
        return floorBid;
    }

    public String getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] (%.2f): %s", id, category, floorBid, keywords);
    }
}