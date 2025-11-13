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
