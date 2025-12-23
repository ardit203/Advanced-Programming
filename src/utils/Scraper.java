package utils;

import java.io.IOException;
import java.util.List;

public abstract class Scraper {
    protected final String url;

    protected Scraper(String url) {
        this.url = url;
    }

    public abstract List<String> scrape() throws IOException, InterruptedException;
}
