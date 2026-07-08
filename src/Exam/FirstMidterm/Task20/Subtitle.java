package Exam.FirstMidterm.Task20;

import java.time.LocalTime;
import java.util.List;

public class Subtitle {
    private int index;
    private long start;
    private long end;
    private String text;

    public Subtitle(int index, long start, long end, String text) {
        this.index = index;
        this.start = start;
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
