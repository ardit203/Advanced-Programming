package Exam.FirstMidterm.Task7;

import java.util.Comparator;
import java.util.List;

public class MyTime implements Comparable<MyTime>{
    int hour;
    int minute;

    public MyTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String formatMinute(){
        if(minute>=0 && minute<=9){
            return "0" + minute;
        }
        return String.valueOf(minute);
    }

    public String convertToAMPM() {
        int newHour = hour;
        String end = "AM";
        if (hour == 0) {
            newHour = 12;
            end = "AM";
        }

        if (hour == 12) {
            end = "PM";
        }

        if (hour > 12 && hour < 24) {
            newHour -= 12;
            end = "PM";
        }

        return String.format("%2d:%s %s", newHour, formatMinute(), end);
    }

    @Override
    public String toString() {
        return String.format("%2d:%s", hour, formatMinute());
    }

    @Override
    public int compareTo(MyTime o) {
        return Comparator.comparing(MyTime::getHour).thenComparing(MyTime::getMinute).compare(this, o);
    }
}
