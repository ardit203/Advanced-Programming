package Exam.FirstMidterm.Task37;

import java.util.Comparator;

class Flight implements Comparable<Flight> {
    private String from;
    private String to;
    private int time;
    private int duration;
    private String departureString;
    private String durationString;
    private String arrivalString;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
        compute();
    }

    private void compute() {
        departureString = String.format("%02d:%02d", time / 60, time % 60);

        int[] arrival = dur(time + duration);

        int days = arrival[0];
        int hours = arrival[1];
        int minutes = arrival[2];

        arrivalString = String.format("%02d:%02d", hours, minutes);

        int[] duration = dur(this.duration);
        int d = days + duration[0];
        int h = duration[1];
        int m = duration[2];

        String dStr = d == 0 ? "" : "+" + d + "d ";

        durationString = String.format("%s%dh%02dm", dStr, h, m);

    }

    private int[] dur(int time) {
        int d = time / (24 * 60);
        time %= (24 * 60);
        int h = time / 60;
        time %= 60;
        int m = time;

        return new int[]{d, h, m};
    }

    public String getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return String.format("%s-%s %s-%s %s", from, to, departureString, arrivalString, durationString);
    }

    @Override
    public int compareTo(Flight other) {
        return Comparator.comparing(Flight::getTo)
                .thenComparing(Flight::getTime)
                .thenComparing(Flight::getDuration).compare(this, other);
    }
}