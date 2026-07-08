package Exam.FirstMidterm.Task18;

import java.util.Date;

public abstract class NewsItem {
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
