package Exam.FirstMidterm.Task18;

import java.util.Date;

public class TextNewsItem extends NewsItem {
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
