package Exam.FirstMidterm.Task15;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Measurement implements Comparable<Measurement>{
    private float temp;
    private float wind;
    private float hum;
    private float vis;
    private Date date;

    public Measurement(float temp, float wind, float hum, float vis, Date date) {
        this.temp = temp;
        this.wind = wind;
        this.hum = hum;
        this.vis = vis;
        this.date = date;
    }

    @Override
    public int compareTo(Measurement other) {
        return this.date.compareTo(other.date);
    }

    public float getTemp() {
        return temp;
    }

    public float getWind() {
        return wind;
    }

    public float getHum() {
        return hum;
    }

    public Date getDate() {
        return date;
    }

    public float getVis() {
        return vis;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s",temp, wind, hum, vis, sdf.format(date));
    }
}
