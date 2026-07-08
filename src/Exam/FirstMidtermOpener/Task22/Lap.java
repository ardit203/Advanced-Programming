package Exam.FirstMidtermOpener.Task22;


public class Lap implements Comparable<Lap> {
    private int minutes;
    private int seconds;
    private int milliseconds;

    public Lap(int minutes, int seconds, int milliseconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public long getTimeInMs(){
        long minsInMs = minutes * 60 * 1000L;
        long secsInMs = seconds * 1000L;

        return minsInMs + secsInMs + milliseconds;
    }

    public static Lap createLap(String line) {
        String[] tokens = line.split(":");
        int min = Integer.parseInt(tokens[0]);
        int sec = Integer.parseInt(tokens[1]);
        int ms = Integer.parseInt(tokens[2]);
        return new Lap(min, sec, ms);
    }

    @Override
    public int compareTo(Lap other) {
        return Long.compare(this.getTimeInMs(), other.getTimeInMs());
    }

    @Override
    public String toString() {
        return String.format("%d:%02d:%03d",minutes,seconds,milliseconds);
    }
}
