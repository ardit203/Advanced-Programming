package Lab.Lab3.Task2;

class AdFactory {
    public static Ad createAd(String line) {
        // AD001 tech 2.5 0.12 Amazing new phone
        String[] tokens = line.split("\\s++", 5);
        String id = tokens[0];
        String category = tokens[1];
        double bidVal = Double.parseDouble(tokens[2]);
        double ctr = Double.parseDouble(tokens[3]);
        String content = tokens[4];
        return new Ad(id, category, bidVal, ctr, content);
    }
}
