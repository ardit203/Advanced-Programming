package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Weather;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DailyTemperatures {
    private List<DailyMeasurement> measurements;

    public DailyTemperatures(){
        this.measurements = new ArrayList<>();
    }

    public void readTemperatures(InputStream inputStream){
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        measurements = br.lines().filter(Objects::nonNull).map(DailyMeasurement::create).collect(Collectors.toList());
    }

    public void writeDailyStats(OutputStream outputStream, char scale){
        PrintWriter writer = new PrintWriter(outputStream);

        measurements.stream().sorted(Comparator.comparingInt(DailyMeasurement::getDay)).forEach(m -> {
            writer.println(DailyMeasurement.getMeasurementStat(m, scale));
        });
        writer.flush();
    }
}
