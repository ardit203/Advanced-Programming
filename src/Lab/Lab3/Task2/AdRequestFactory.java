package Lab.Lab3.Task2;

class AdRequestFactory {
    public static AdRequest createAdRequest(String line) {
        String[] tokens = line.split("\\s++", 4);
        String id = tokens[0];
        String category = tokens[1];
        double floorBid = Double.parseDouble(tokens[2]);
        String keywords = tokens[3];
        return new AdRequest(id, category, floorBid, keywords);
    }
}
