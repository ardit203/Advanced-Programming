package Exam.FirstMidtermOpener.Task22;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class F1Race {
    private List<Driver> drivers;

    public F1Race() {
        this.drivers = new ArrayList<>();
    }

    public void readResults(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        drivers = br.lines()
                .map(Driver::createDriver)
                .collect(Collectors.toList());
    }

    public void printSorted(PrintStream os) {
        drivers = drivers.stream()
                .sorted()
                .collect(Collectors.toList());

        PrintWriter pw = new PrintWriter(os);

        for (int i = 0; i < drivers.size(); i++) {
            pw.println(String.format("%d. %s", i + 1, drivers.get(i)));
        }
        pw.flush();
    }
}