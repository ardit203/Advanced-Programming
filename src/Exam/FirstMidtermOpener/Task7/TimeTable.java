package Exam.FirstMidtermOpener.Task7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TimeTable {
    private List<Time> times;

    public TimeTable() {
        this.times = new ArrayList<>();
    }


    public void readTimes(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        br.lines().filter(Objects::nonNull)
                .forEach(line -> {
                    String[] tokens = line.split("\\s++");
                    for (int i = 0; i < tokens.length; i++) {
                        String[] time = tokens[i].split("[:.]");
                        if (time.length == 1) {
                            throw new UnsupportedFormatException(time[0]);
                        }
                        int hour = Integer.parseInt(time[0]);
                        int minutes = Integer.parseInt(time[1]);

                        if(hour < 0 || hour > 23 || minutes < 0 || minutes > 59){
                            throw new InvalidTimeException(tokens[i]);
                        }
                        times.add(new Time(hour, minutes));
                    }
                });

//        String line;
//        while ((line = br.readLine()) != null){
//            String[] tokens = line.split("\\s++");
//            for (int i = 0; i < tokens.length; i++) {
//                String[] time = tokens[i].split("[:.]");
//                if (time.length == 1) {
//                    throw new UnsupportedFormatException(time[0]);
//                }
//                int hour = Integer.parseInt(time[0]);
//                int minutes = Integer.parseInt(time[1]);
//
//                if(hour < 0 || hour > 23 || minutes < 0 || minutes > 59){
//                    throw new InvalidTimeException(tokens[i]);
//                }
//                times.add(new Time(hour, minutes));
//            }
//        }
    }

    public void writeTimes(PrintStream os, TimeFormat timeFormat) {
        PrintWriter pw = new PrintWriter(os);

        times.stream()
                .sorted()
                .forEach(time -> pw.println(time.getTimeFormat(timeFormat)));
        pw.flush();
    }
}
