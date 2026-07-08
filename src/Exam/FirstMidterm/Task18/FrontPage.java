package Exam.FirstMidterm.Task18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FrontPage {
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
