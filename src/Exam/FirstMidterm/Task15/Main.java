package Exam.FirstMidterm.Task15;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    static void main() throws ParseException {
        String d1 = "10.12.2013 21:30:00";
        String d2 = "10.12.2013 21:32:31";
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date1 = df.parse(d1);
        Date date2 = df.parse(d2);

//        double diff = Math.abs(date1.getTime() - date2.getTime()) / (1000 * 60);
        Date date = new Date(date1.getTime() - 11 * 1440 * 1000 * 60);
        System.out.println(date);
    }
}
