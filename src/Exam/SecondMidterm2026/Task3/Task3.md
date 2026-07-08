<div class="clearfix" id="yui_3_18_1_1_1771366841298_85">
<p data-start="42" data-end="207">For the needs of a news management system, it is required to implement a class <strong data-start="121" data-end="137"><code data-start="123" data-end="135">NewsSystem</code></strong> that represents a central access point to the system’s functionality.</p>
<p data-start="209" data-end="290">Within the <strong data-start="220" data-end="236"><code data-start="222" data-end="234">NewsSystem</code></strong> class, the following methods <strong data-start="266" data-end="274">must</strong> be implemented:</p>
<ul data-start="292" data-end="1560" data-is-only-node="" data-is-last-node="">
<li data-start="292" data-end="518">
<p data-start="294" data-end="458"><strong data-start="294" data-end="371"><code data-start="296" data-end="369">public NewsSystem(List&lt;String&gt; categoryNames, List&lt;String&gt; authorNames)</code></strong><br data-start="371" data-end="374">Constructor that initializes the system with the available categories and authors.</p>
<ul data-start="461" data-end="518">
<li data-start="461" data-end="489">
<p data-start="463" data-end="489">a list of category names</p>
</li>
<li data-start="492" data-end="518">
<p data-start="494" data-end="518">a list of author names</p>
</li>
</ul>
</li>
<li data-start="520" data-end="612">
<p data-start="522" data-end="612"><strong data-start="522" data-end="564"><code data-start="524" data-end="562">public void addUser(String username)</code></strong><br data-start="564" data-end="567">Method for adding a new user to the system.</p>
</li>
<li data-start="614" data-end="775">
<p data-start="616" data-end="775"><strong data-start="616" data-end="695"><code data-start="618" data-end="693">public void subscribeUserToCategory(String username, String categoryName)</code></strong><br data-start="695" data-end="698">Method by which a user subscribes to receive news from a specific category.</p>
</li>
<li data-start="777" data-end="948">
<p data-start="779" data-end="948"><strong data-start="779" data-end="862"><code data-start="781" data-end="860">public void unsubscribeUserFromCategory(String username, String categoryName)</code></strong><br data-start="862" data-end="865">Method by which a user unsubscribes from receiving news from a specific category.</p>
</li>
<li data-start="950" data-end="1105">
<p data-start="952" data-end="1105"><strong data-start="952" data-end="1027"><code data-start="954" data-end="1025">public void subscribeUserToAuthor(String username, String authorName)</code></strong><br data-start="1027" data-end="1030">Method by which a user subscribes to receive news from a specific author.</p>
</li>
<li data-start="1107" data-end="1272">
<p data-start="1109" data-end="1272"><strong data-start="1109" data-end="1188"><code data-start="1111" data-end="1186">public void unsubscribeUserFromAuthor(String username, String authorName)</code></strong><br data-start="1188" data-end="1191">Method by which a user unsubscribes from receiving news from a specific author.</p>
</li>
<li data-start="1274" data-end="1380">
<p data-start="1276" data-end="1380"><strong data-start="1276" data-end="1325"><code data-start="1278" data-end="1323">public void publishArticle(Article article)</code></strong><br data-start="1325" data-end="1328">Method for publishing a new article in the system.</p>
</li>
<li data-start="1382" data-end="1560" data-is-last-node="">
<p data-start="1384" data-end="1560" data-is-last-node=""><strong data-start="1384" data-end="1435"><code data-start="1386" data-end="1433">public void printNewsForUser(String username)</code></strong><br data-start="1435" data-end="1438">Method that prints all news received by the user with the given username, sorted by publication time in ascending order.</p>
</li>
</ul></div>

### Starter code
```java
import java.time.LocalDateTime;
import java.util.*;

class Article {

    private final String category;
    private final String author;
    private final String content;
    private final LocalDateTime timestamp;

    public Article(String category, String author, String content, LocalDateTime timestamp) {
        this.category = category;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}


public class NewsSystemTest {

    public static void main(String[] args) {

        // Hardcoded categories and authors
        List<String> categories = List.of(
                "Technology", "Sports", "Politics", "Health", "Science",
                "Business", "Education", "Culture", "Travel", "Entertainment"
        );

        List<String> authors = List.of(
                "MartinFowler", "JohnDoe", "AliceSmith", "BobBrown", "JaneMiller"
        );

        NewsSystem system = new NewsSystem(categories, authors);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+", 2);
            String command = parts[0];

            switch (command) {

                case "ADD_USER":
                    system.addUser(parts[1]);
                    break;

                case "SUBSCRIBE_CATEGORY": {
                    String[] p = parts[1].split("\\s+");
                    system.subscribeUserToCategory(p[0], p[1]);
                    break;
                }

                case "UNSUBSCRIBE_CATEGORY": {
                    String[] p = parts[1].split("\\s+");
                    system.unsubscribeUserFromCategory(p[0], p[1]);
                    break;
                }

                case "SUBSCRIBE_AUTHOR": {
                    String[] p = parts[1].split("\\s+");
                    system.subscribeUserToAuthor(p[0], p[1]);
                    break;
                }

                case "UNSUBSCRIBE_AUTHOR": {
                    String[] p = parts[1].split("\\s+");
                    system.unsubscribeUserFromAuthor(p[0], p[1]);
                    break;
                }

                case "PUBLISH": {
                    // format:
                    // PUBLISH <category> <author> <timestamp> <content>
                    String[] p = parts[1].split("\\s+", 4);
                    Article article = new Article(
                            p[0],
                            p[1],
                            p[3],
                            LocalDateTime.parse(p[2])
                    );
                    system.publishArticle(article);
                    break;
                }

                case "PRINT":
                    system.printNewsForUser(parts[1]);
                    break;
            }
        }
    }
}
```