package Lab.Lab3.Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class AdNetwork {
    private static double x = 5.0;
    private static double y = 100.0;

    private List<Ad> ads;

    public AdNetwork() {
        this.ads = new ArrayList<>();
    }

    public void readAds(BufferedReader br) throws IOException {
        String line;
        while (!(line = br.readLine()).isEmpty()) {
            ads.add(AdFactory.createAd(line));
        }
    }

    public void placeAds(BufferedReader br, int k, PrintWriter pw) throws IOException {
        AdRequest adRequest = AdRequestFactory.createAdRequest(br.readLine());

        Comparator<Ad> comparator = Comparator.comparingDouble(
                (Ad ad) -> relevanceScore(ad, adRequest) + x * ad.getBidValue() + y * ad.getCtr()
        ).reversed();

        List<Ad> sorted = ads.stream()
                .filter(ad -> ad.getBidValue() >= adRequest.getFloorBid())
                .sorted(comparator)
                .limit(k)
                .sorted()
                .collect(Collectors.toList());

        pw.println(String.format("Top ads for request %s:", adRequest.getId()));

        sorted.forEach(pw::println);

    }

    private int relevanceScore(Ad ad, AdRequest req) {
        int score = 0;
        if (ad.getCategory().equalsIgnoreCase(req.getCategory())) score += 10;
        String[] adWords = ad.getContent().toLowerCase().split("\\s+");
        String[] keywords = req.getKeywords().toLowerCase().split("\\s+");
        for (String kw : keywords) {
            for (String aw : adWords) {
                if (kw.equals(aw)) score++;
            }
        }
        return score;
    }
}
