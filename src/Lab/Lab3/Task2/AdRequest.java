package Lab.Lab3.Task2;

class AdRequest {
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

    public double getFloorBid() {
        return floorBid;
    }

    public String getCategory() {
        return category;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        //ID [CATEGORY] (floor=…): KEYWORDS
        return String.format("%s [%s] (floor=%.2f): %s", id, category, floorBid, keywords);
    }
}
