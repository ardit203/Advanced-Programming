# Lab Exercise 3 - Advanced Programming

## Task 2

**Implement a simulation of an Ad Network** that reads data for ads and for a single ad request, then selects the most relevant ads based on a combination of several factors.

### Class `Ad`

Represents a single advertisement. The class should contain the following attributes:

* `id: String` – identifier of the ad
* `category: String` – category (e.g., “tech”, “sports”, “food”)
* `bidValue: double` – bid for showing the ad (in dollars)
* `ctr: double` – average Click-Through Rate
* `content: String` – textual content (a sentence)

The class should have:

* `toString()` that prints the object in the following format:
  `ID CATEGORY (bid=…, ctr=…%) CONTENT`

Implement the `Comparable` interface so that the “natural order” is by `bidValue` in **descending** order, and if `bidValue` is the same, by `id` in **ascending** order.

### Class `AdRequest`

Represents a request to show an ad. It should contain:

* `id: String` – identifier of the request
* `category: String` – category of the request
* `floorBid: double` – minimum allowed bid to show an ad
* `keywords: String` – keywords related to the request (space-separated)

The class should have:

* `toString()` that prints the object in the following format:
  `ID [CATEGORY] (floor=…): KEYWORDS`

### Class `AdNetwork`

Represents the ad network and manages ad serving. It should have the attribute:

* `ads: ArrayList<Ad>` – list of all ads

And the following methods:

* `void readAds(InputStream in)` – reads ads from the input stream using a `BufferedReader`. For each line, create an `Ad` object and add it to `ads`. Each line is in the format:
  
* `ID CATEGORY BID_VALUE CTR CONTENT`
* 
  >Example: AD001 tech 2.5 0.12 Amazing new phone

* `List placeAds(InputStream inputStream, int k, OutputStream outputStream)` – this method:

  1. Reads a single ad request (`AdRequest`) from the given input stream in the format:
       
        `ID CATEGORY FLOOR_BID KEYWORD1 KEYWORD2 KEYWORD3…`
      
      >  Example:  AR001 tech 2.0 technology phone application inches
  2. Excludes all ads whose `bidValue` is less than the request’s `floorBid`.
  3. For each ad, computes a total score according to:
     `totalScore = relevanceScore(ad, request) + x * bidValue + y * ctr`
     where `relevanceScore(ad, request)` is a function that returns points based on category and keywords (this function is **already provided** and must not be changed), and `x = 5.0` and `y = 100.0` are constants that emphasize the influence of bid and CTR.
  4. Sorts the ads by `totalScore` in **descending** order, takes the top `k` ads, then sorts those by the class’s **natural order**.
  5. Prints the results with a `PrintWriter` to the given output stream in the format:

      >  Top ads for request AR001:  
        AD003 tech (bid=3.00, ctr=9.00%) Powerful gaming laptop  
        AD001 tech (bid=2.50, ctr=12.00%) Amazing new phone  
        ...

Starter code:
```java
import java.io.*;
import java.util.*;

// todo: complete the implementation of the Ad, AdRequest, and AdNetwork classes

class AdNetwork {
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

public class Main {
  public static void main(String[] args) throws IOException {
    AdNetwork network = new AdNetwork();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    int k = Integer.parseInt(br.readLine().trim());

    if (k == 0) {
      network.readAds(br);
      network.placeAds(br, 1, pw);
    } else if (k == 1) {
      network.readAds(br);
      network.placeAds(br, 3, pw);
    } else {
      network.readAds(br);
      network.placeAds(br, 8, pw);
    }

    pw.flush();
  }
}
```

Solution:
```java
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Ad implements Comparable<Ad> {
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

class AdNetwork {
    private static double x = 5.0;
    private static double y = 100.0;
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

public class Main {
  public static void main(String[] args) throws IOException {
    AdNetwork network = new AdNetwork();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    int k = Integer.parseInt(br.readLine().trim());

    if (k == 0) {
      network.readAds(br);
      network.placeAds(br, 1, pw);
    } else if (k == 1) {
      network.readAds(br);
      network.placeAds(br, 3, pw);
    } else {
      network.readAds(br);
      network.placeAds(br, 8, pw);
    }

    pw.flush();
  }
}
```