package Exam.FirstMidterm.Task18;

import java.util.Date;

public class MediaNewsItem extends NewsItem {
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
