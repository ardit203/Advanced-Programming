package Exam.FirstMidtermOpener.Task7;

public class Time implements Comparable<Time> {
    private int hours;
    private int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public String formatMinutes(){
        if(minutes>=0 && minutes<=9){
            return "0" + minutes;
        }
        return String.valueOf(minutes);
    }

    private String getAM_PM_Format() {
        String amPm = (hours < 12) ? "AM" : "PM";
        int newHour = (hours % 12 == 0) ? 12 : (hours % 12);

        return String.format("%2d:%s %s", newHour, formatMinutes(), amPm);
    }

    private String get24hFormat(){
        return String.format("%2d:%s", hours, formatMinutes());
    }

    public String getTimeFormat(TimeFormat timeFormat){
        return timeFormat == TimeFormat.FORMAT_24 ? get24hFormat() : getAM_PM_Format();
    }

    @Override
    public int compareTo(Time other) {
        int compare1 = Integer.compare(this.hours, other.hours);
        return compare1 != 0 ? compare1 : Integer.compare(this.minutes, other.minutes);
    }
}
