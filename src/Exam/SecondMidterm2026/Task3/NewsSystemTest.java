package Exam.SecondMidterm2026.Task3;

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

class NewsSystem {

    private final Set<String> categories;
    private final Set<String> authors;

    private final Map<String, Set<String>> userCategories;
    private final Map<String, Set<String>> userAuthors;

    private final List<Article> articles;

    public NewsSystem(List<String> categories, List<String> authors) {
        this.categories = new HashSet<>(categories);
        this.authors = new HashSet<>(authors);
        this.userCategories = new HashMap<>();
        this.userAuthors = new HashMap<>();
        this.articles = new ArrayList<>();
    }

    public void addUser(String username) {
        userCategories.putIfAbsent(username, new HashSet<>());
        userAuthors.putIfAbsent(username, new HashSet<>());
    }

    public void subscribeUserToCategory(String username, String category) {
        addUser(username);

        if (categories.contains(category)) {
            userCategories.get(username).add(category);
        }
    }

    public void unsubscribeUserFromCategory(String username, String category) {
        addUser(username);
        userCategories.get(username).remove(category);
    }

    public void subscribeUserToAuthor(String username, String author) {
        addUser(username);

        if (authors.contains(author)) {
            userAuthors.get(username).add(author);
        }
    }

    public void unsubscribeUserFromAuthor(String username, String author) {
        addUser(username);
        userAuthors.get(username).remove(author);
    }

    public void publishArticle(Article article) {
        if (categories.contains(article.getCategory()) && authors.contains(article.getAuthor())) {
            articles.add(article);
        }
    }

    public void printNewsForUser(String username) {
        addUser(username);

        Set<String> subscribedCategories = userCategories.get(username);
        Set<String> subscribedAuthors = userAuthors.get(username);

        for (Article article : articles) {
            boolean matchesCategory = subscribedCategories.contains(article.getCategory());
            boolean matchesAuthor = subscribedAuthors.contains(article.getAuthor());

            if (matchesCategory || matchesAuthor) {
                System.out.println(article.getTimestamp() + " "
                        + article.getCategory() + " "
                        + article.getAuthor() + " "
                        + article.getContent());
            }
        }
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