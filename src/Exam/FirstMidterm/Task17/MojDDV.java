package Exam.FirstMidterm.Task17;

import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class MojDDV {
    private List<Receipt> receipts;

    public MojDDV() {
        this.receipts = new ArrayList<>();
    }

    public void readRecords(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        receipts = br.lines()
                .map(line -> {
                    try {
                        return Receipt.createReceipt(line);
                    } catch (AmountNotAllowedException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    void printTaxReturns(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        receipts.forEach(pw::println);
        pw.flush();
    }

//    private DoubleStream getDoubleStream(){
//        return receipts.stream().mapToDouble(Receipt::totalTaxReturn);
//    }
//
//    public void printStatistics(PrintStream os) {
//        PrintWriter pw = new PrintWriter(os);
//
//        double min = getDoubleStream().min().orElse(0);
//        double max = getDoubleStream().max().orElse(0);
//        double sum = getDoubleStream().sum();
//        int count = receipts.size();
//        double avg = getDoubleStream().average().orElse(0);
//
//        pw.println(String.format("min:\t%5.3f", min));
//        pw.println(String.format("max:\t%5.3f", max));
//        pw.println(String.format("sum:\t%5.3f", sum));
//        pw.println(String.format("count:\t%-5d", count));
//        pw.println(String.format("avg:\t%5.3f", avg));
//
//        pw.flush();
//    }

    public void printStatistics(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);

        DoubleSummaryStatistics summaryStatistics = receipts.stream()
                .mapToDouble(Receipt::totalTaxReturn)
                .summaryStatistics();

        pw.println(String.format("min:\t%5.3f", summaryStatistics.getMin()));
        pw.println(String.format("max:\t%5.3f", summaryStatistics.getMax()));
        pw.println(String.format("sum:\t%5.3f", summaryStatistics.getSum()));
        pw.println(String.format("count:\t%-5d", summaryStatistics.getCount()));
        pw.println(String.format("avg:\t%5.3f", summaryStatistics.getAverage()));

        pw.flush();
    }

}
