package Exam.FirstMidterm.Task42;

import java.io.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DailyTemperatures {
    private Set<DailyMeasurement> measurements;

    public DailyTemperatures() {

    }


    public void readTemperatures(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        measurements = br.lines()
                .map(DailyTempFactory::createDailyTemp)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DailyMeasurement::getDay))));
    }

    public void writeDailyStats(PrintStream os, char type) {
        PrintWriter pw = new PrintWriter(os);

        measurements.forEach(dm -> pw.println(dm.printStats(type)));
        pw.flush();
    }
}