package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Race;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class F1Race {
    private List<Driver> drivers;

    public F1Race() {
        this.drivers = new ArrayList<>();
    }

    public void readResults(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        drivers = br.lines().filter(Objects::nonNull)
                .map(Driver::create).collect(Collectors.toList());
    }

    public void printSorted(PrintStream out) {
        PrintWriter printWriter = new PrintWriter(out);
//        drivers.stream().sorted(Comparator.comparing(d -> Driver.getLapMillis(d.getBestLap()))).forEach(printWriter::println);
        drivers.stream().sorted().forEach(printWriter::println);
        printWriter.flush();
        printWriter.close();
    }
}
