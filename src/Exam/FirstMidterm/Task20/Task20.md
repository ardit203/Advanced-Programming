You need to implement a class `Subtitles` which reads a translation from an input stream (standard input, file, ...) in the standard `srt` format.
Each element of the subtitles consists of an index number, start time, end time, and the text, and is in the following format (example):

```
2
00:00:48,321 --> 00:00:50,837
Let's see a real bet.
```

The text part may contain multiple lines.
All subtitle elements are separated by one empty line.

Your task is to implement the following methods:

* `Subtitles()` – default constructor
* `int loadSubtitles(InputStream inputStream)` – method for reading the subtitles (returns how many elements were read)
* `void print()` – prints the loaded subtitles in the same format as when reading
* `void shift(int ms)` – shifts the timestamps of all subtitle elements by the number of milliseconds given as an argument (can be negative, which shifts the timestamps backwards)

### Starter code
```java
import java.util.Scanner;

public class SubtitlesTest {
	public static void main(String[] args) {
		Subtitles subtitles = new Subtitles();
		int n = subtitles.loadSubtitles(System.in);
		System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
		subtitles.print();
		int shift = n * 37;
        shift = (shift % 2 == 1) ? -shift : shift;
		System.out.println(String.format("SHIFT FOR %d ms", shift));
		subtitles.shift(shift);
		System.out.println("+++++ SHIFTED SUBTITLES +++++");
		subtitles.print();
	}
}
```


### Solution
```java
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SubtitlesTest {
	public static void main(String[] args) throws IOException {
		Subtitles subtitles = new Subtitles();
		int n = subtitles.loadSubtitles(System.in);
		System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
		subtitles.print();
		int shift = n * 37;
        shift = (shift % 2 == 1) ? -shift : shift;
		System.out.println(String.format("SHIFT FOR %d ms", shift));
		subtitles.shift(shift);
		System.out.println("+++++ SHIFTED SUBTITLES +++++");
		subtitles.print();
	}
}


class Subtitle {
    private int index;
    private long start;
    private long end;
    private String text;

    public Subtitle(int index, long start, long end, String text) {
        this.index = index;
        this.satart = start;
        this.end = end;
        this.text = text;
    }

    public static Subtitle createSubtitle(List<String> parts) {
        int index = Integer.parseInt(parts.get(0));
        String[] timeParts = parts.get(1).split(" --> ");
        long startTime = parseStringToTime(timeParts[0]);
        long endTime = parseStringToTime(timeParts[1]);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < parts.size(); i++) {
            sb.append(parts.get(i)).append("\n");
        }
        return new Subtitle(index, startTime, endTime, sb.toString());
    }

    private static long parseStringToTime(String time) {
        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        String[] secsAndMillis = timeParts[2].split(",");
        int seconds = Integer.parseInt(secsAndMillis[0]);
        int millis = Integer.parseInt(secsAndMillis[1]);

        long hoursInMillis = hour * 60 * 60 * 1000L;
        long minutesInMillis = minute * 60 * 1000L;
        long secondsInMillis = seconds * 1000L;


        return hoursInMillis + minutesInMillis + secondsInMillis + millis;
    }

    private String parseTimeToString(long time) {
        long hours = time / (60 * 60 * 1000L);
        time %= (60 * 60 * 1000L);
        long minutes = time / (60 * 1000L);
        time %= (60 * 1000L);
        long seconds = time / 1000L;
//        time %= 1000L;
        long ms = time % 1000;
        return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, ms);
    }

    public void shift(int shift) {
        start += shift;
        end += shift;
    }

    @Override
    public String toString() {
        return String.format("%d\n%s --> %s\n%s", index, parseTimeToString(start), parseTimeToString(end), text);
    }
}


class Subtitles {
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
```