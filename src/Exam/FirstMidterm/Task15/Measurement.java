package Exam.FirstMidterm.Task15;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Measurement implements Comparable<Measurement>{
    private float temp;
    private float humidity;
    private float wind;
    private float visibility;
    private Date date;

    public Measurement(float temp, float humidity, float wind, float visibility, Date date) {
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
        this.visibility = visibility;
        this.date = date;
    }

    public float getTemp() {
        return temp;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(Measurement o) {
        return date.compareTo(o.date);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s",temp, humidity, wind, visibility, sdf.format(date));
    }
}
