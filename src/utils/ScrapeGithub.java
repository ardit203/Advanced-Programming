package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ScrapeGithub extends Scraper {
    private HttpClient client;


    public ScrapeGithub(String baseUrl) {
        super(baseUrl);
        client = HttpClient.newHttpClient();
    }

    private String scrape(int i) throws IOException, InterruptedException {
        String newUrl = url + "/" + i +".txt";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(newUrl))
                .header("User-Agent", "Java HttpClient")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed: HTTP code " + response.statusCode());
        }

        return response.body();
    }

    public List<String> scrape() throws IOException, InterruptedException {
        List<String> filesContents = new ArrayList<>();
        int i=0;
        while (true){
            try {
                filesContents.add(scrape(i));
            }catch (RuntimeException e){
                return filesContents;
            }
            i++;
        }
    }


}
