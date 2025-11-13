package Lab.Lab3.Task2;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdNetwork {
    private static double x = 5.0;
    private static double y=100.0;
    private List<Ad> ads;
    private AdRequest req;

    public void readAds(BufferedReader in) throws IOException {
        ads = in.lines()
                .takeWhile(s -> !s.isEmpty())
                .map(Ad::createAd)
                .collect(Collectors.toList());


        req = AdRequest.createReq(in.readLine());
    }

    public void placeAds(BufferedReader inputStream, int k, PrintWriter outputStream) {
        Comparator<Ad> comparator = Comparator.comparingDouble(
                (Ad ad) -> relevanceScore(ad, req) + x * ad.getBidValue() + y * ad.getCtr()
        ).reversed();

        ads = ads.stream()
                .filter(a -> a.getBidValue() >= req.getFloorBid())
                .sorted(comparator)
                .limit(k)
                .sorted()
                .collect(Collectors.toList());
        outputStream.printf("Top ads for request %s:\n", req.getId());
        ads.forEach(outputStream::println);
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