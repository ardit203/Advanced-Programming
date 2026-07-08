package Exercises._05_Intro_to_Maps.Airport;

public class Flight implements Comparable<Flight> {
    private String from;
    private String to;
    private int time;
    private int duration;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
    }

    public String getTo() {
        return to;
    }

    @Override
    public int compareTo(Flight other) {
        int cmp = to.compareTo(other.to);
        if (cmp == 0) {
            return Integer.compare(time, other.time);
        }
        return cmp;
    }

    @Override
    public String toString() {
        int end = time + duration;
        int plus = end / (24 * 60);
        end %= (24 * 60);
        return String.format(
                "%s-%s %02d:%02d-%02d:%02d%s %dh%02dm", from, to, time / 60, time % 60,
                end / 60, end % 60, plus > 0 ? " +1d" : "", duration / 60, duration % 60
        );
    }
}
