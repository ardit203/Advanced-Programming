The newspaper **FINKI Onion** decided to develop a mobile application for its news.
All news items `NewsItem` consist of a title, a publication date (an object of class `java.util.Date`), and a category.
The category of a news item is an object of the class `Category`, which stores only the name of the category.
Two categories are equal if their names are equal.

There are currently two types of news published in the newspaper:

* Text news (`TextNewsItem`) which additionally store the text of the news,
* Multimedia news (`MediaNewsItem`) which store the URL to the multimedia content (`String`) and the number of views.

All news items are added into the class `FrontPage`, which stores a list of news items and an array of all existing news categories.
For this class, the following methods need to be implemented:

* constructor: `FrontPage(Category[] categories)`
* `void addNewsItem(NewsItem newsItem)` – adds a new news item to the list
* `List<NewsItem> listByCategory(Category category)` – takes a reference to a `Category` object and returns a list of all news from that category
* `List<NewsItem> listByCategoryName(String category)` – takes as argument a `String` representing the category name and returns a list of all news with that category name.
  If a category with that name does not exist in the category array, an exception of type `CategoryNotFoundException` must be thrown, carrying the name of the category that was not found.
* overriding the `toString()` method, which returns a String composed of all news teasers (calling the `getTeaser()` method).

In the news item classes, the method for returning a teaser `getTeaser()` must be implemented as follows:

* `TextNewsItem::getTeaser()` – returns a String composed of the news title, how many minutes ago it was published (integer minutes), and a maximum of 80 characters of the news text, all separated by a new line.
* `MediaNewsItem::getTeaser()` – returns a String composed of the news title, how many minutes ago it was published (integer minutes), the URL of the news, and the number of views, all separated by a new line.

### Starter code
```java
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class FrontPageTest {
	public static void main(String[] args) {
        // Reading
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] parts = line.split(" ");
		Category[] categories = new Category[parts.length];
		for (int i = 0; i < categories.length; ++i) {
			categories[i] = new Category(parts[i]);
		}
		int n = scanner.nextInt();
		scanner.nextLine();
		FrontPage frontPage = new FrontPage(categories);
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < n; ++i) {
			String title = scanner.nextLine();
			cal = Calendar.getInstance();
            int min = scanner.nextInt();
			cal.add(Calendar.MINUTE, -min);
			Date date = cal.getTime();
			scanner.nextLine();
			String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
			TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
			frontPage.addNewsItem(tni);
		}
        
		n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -min);
			scanner.nextLine();
			Date date = cal.getTime();
			String url = scanner.nextLine();
			int views = scanner.nextInt();
			scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
			MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
			frontPage.addNewsItem(mni);
		}
        // Execution
        String category = scanner.nextLine();
		System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
        	System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
	}
}
```

### Solution
```java
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class FrontPageTest {
	public static void main(String[] args) {
        // Reading
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] parts = line.split(" ");
		Category[] categories = new Category[parts.length];
		for (int i = 0; i < categories.length; ++i) {
			categories[i] = new Category(parts[i]);
		}
		int n = scanner.nextInt();
		scanner.nextLine();
		FrontPage frontPage = new FrontPage(categories);
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < n; ++i) {
			String title = scanner.nextLine();
			cal = Calendar.getInstance();
            int min = scanner.nextInt();
			cal.add(Calendar.MINUTE, -min);
			Date date = cal.getTime();
			scanner.nextLine();
			String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
			TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
			frontPage.addNewsItem(tni);
		}
        
		n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -min);
			scanner.nextLine();
			Date date = cal.getTime();
			String url = scanner.nextLine();
			int views = scanner.nextInt();
			scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
			MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
			frontPage.addNewsItem(mni);
		}
        // Execution
        String category = scanner.nextLine();
		System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
        	System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
	}
}

class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String category) {
        super(String.format("Category %s was not found", category));
    }
}

class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        Category other = (Category) obj;
        return this.name.equals(other.name);
    }
}

abstract class NewsItem {
    protected String title;
    protected Date date;
    protected Category category;


    public NewsItem(String title, Date date, Category category) {
        this.title = title;
        this.date = date;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    protected int getHowManyMinutesAgo() {
        Date now = new Date();
        long difference = now.getTime() - date.getTime();

        return (int) (difference / (1000 * 60));
    }

    protected String getCommonData() {
        return String.format("%s\n%d", title, getHowManyMinutesAgo());
    }

    public abstract String getTeaser();
}

class TextNewsItem extends NewsItem {
    private String text;

    public TextNewsItem(String title, Date date, Category category, String text) {
        super(title, date, category);
        this.text = text;
    }


    @Override
    public String getTeaser() {
        int index = Math.min(text.length(), 80);
        return String.format("%s\n%s\n", getCommonData(), text.substring(0, index));
    }
}

class MediaNewsItem extends NewsItem {
    private String url;
    private int views;
    public MediaNewsItem(String title, Date date, Category category, String url, int views) {
        super(title, date, category);
        this.url = url;
        this.views = views;
    }

    @Override
    public String getTeaser() {
        return String.format("%s\n%s\n%d\n", getCommonData(), url, views);
    }
}

class FrontPage {
    private List<NewsItem> newsItems;
    private final Category[] categories;

    public FrontPage(Category[] categories) {
        this.categories = categories;
        this.newsItems = new ArrayList<>();
    }


    public void addNewsItem(NewsItem newsItem) {
        newsItems.add(newsItem);
    }


    public List<NewsItem> listByCategory(Category category) {
        return newsItems.stream()
                .filter(n -> n.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<NewsItem> listByCategoryName(String category) {
        Category categoryObject = Arrays.stream(categories)
                .filter(c -> c.getName().equals(category))
                .findFirst()
                .orElseThrow(() -> new CategoryNotFoundException(category));

        return listByCategory(categoryObject);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        newsItems.forEach(n -> sb.append(n.getTeaser()));
        return sb.toString();
    }
}
```