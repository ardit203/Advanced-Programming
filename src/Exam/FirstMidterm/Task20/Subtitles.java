package Exam.FirstMidterm.Task20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Subtitles {
    private List<Subtitle> subtitles;

    public Subtitles() {
        this.subtitles = new ArrayList<>();
    }

    public int loadSubtitles(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        List<String> parts = new ArrayList<>();
        int count = 0;
        while ((line = br.readLine()) != null){
            if(line.isEmpty()){
                count++;
                subtitles.add(Subtitle.createSubtitle(parts));
                parts = new ArrayList<>();
                continue;
            }
            parts.add(line);
        }
        subtitles.add(Subtitle.createSubtitle(parts));
        return ++count;
    }

    public void print() {
        subtitles.forEach(System.out::println);
    }

    public void shift(int shift) {
        subtitles.forEach(s -> s.shift(shift));
    }
}
