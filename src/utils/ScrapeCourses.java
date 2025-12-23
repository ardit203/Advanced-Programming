package utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

import static utils.StringNormalizer.transformToFileFormat;

public class ScrapeCourses extends Scraper {
    private final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();
    private final String login_url = "https://cas.finki.ukim.mk/cas/login?service=https%3A%2F%2Fcourses.finki.ukim.mk%2Flogin%2Findex.php";
    private final String index;
    private final String password;


    public ScrapeCourses(String mainUrl) {
        super(mainUrl);
        this.index = dotenv.get("INDEX");
        this.password = dotenv.get("PASSWORD");
    }

    @Override
    public List<String> scrape() throws IOException {
        return scrape(login());
    }


    public Map<String, String> login() throws IOException {
        Connection.Response loginPage = Jsoup.connect(login_url)
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0")
                .execute();

        Document doc = loginPage.parse();

        String lt = doc.selectFirst("input[name=lt]").attr("value");
        String execution = doc.selectFirst("input[name=execution]").attr("value");
        String eventId = doc.selectFirst("input[name=_eventId]").attr("value");

        Map<String, String> data = new HashMap<>();
        data.put("username", index);
        data.put("password", password);
        data.put("lt", lt);
        data.put("execution", execution);
        data.put("_eventId", eventId);
        data.put("warn", "true");
        data.put("submit", "LOGIN");

        Connection.Response loginResp = Jsoup.connect(login_url)
                .cookies(loginPage.cookies())
                .data(data)
                .userAgent("Mozilla/5.0")
                .referrer(login_url)
                .followRedirects(true)
                .method(Connection.Method.POST)
                .execute();

        return loginResp.cookies();
    }

    public List<String> scrape(Map<String, String> cookies) throws IOException {
        Document doc = Jsoup.connect(url)
                .cookies(cookies)
                .userAgent("Mozilla/5.0")
                .get();

        Elements rows = doc.select("tbody tr");

        List<String> filesContent = new ArrayList<>();

        for (Element row : rows) {
            Element c1El = row.selectFirst("td.c1");
            Element c2El = row.selectFirst("td.c2");

            if (c1El == null || c2El == null) {
                continue;
            }

            String c1 = c1El.text().trim();
            String c2 = c2El.text().trim();

            filesContent.add(transformToFileFormat(c1, c2));
        }

        return filesContent;
    }


}

