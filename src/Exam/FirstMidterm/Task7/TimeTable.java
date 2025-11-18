package Exam.FirstMidterm.Task7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TimeTable {
    List<MyTime> times;

    public TimeTable(){
        this.times = new ArrayList<>();
    }

    public void readTimes(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        br.lines().forEach(l -> {
            String [] tokens = l.split(" ");
            for (int i = 0; i < tokens.length; i++) {
                String [] parts = tokens[i].split(":");
                if(parts.length == 1){
                    parts = tokens[i].split("\\.");
                    if(parts.length == 1){
                        throw new UnsupportedFormatException(parts[0]);
                    }
                }
                times.add(new MyTime(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            }
        });
    }

    public void writeTimes(PrintStream out, TimeFormat timeFormat) {
        PrintWriter pw = new PrintWriter(out);
        if(timeFormat == TimeFormat.FORMAT_24){
            times.stream().sorted().forEach(pw::println);
        }else {
            times.stream().sorted().forEach(t -> pw.println(t.convertToAMPM()));
        }
        pw.flush();
    }
}
