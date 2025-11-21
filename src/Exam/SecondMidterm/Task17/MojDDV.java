package Exam.SecondMidterm.Task17;

import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MojDDV {
    private List<Receipt> receipts;

    public MojDDV() {
        this.receipts = new ArrayList<>();
    }

    public void readRecords(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        receipts = br.lines().filter(Objects::nonNull)
                .map(l -> {
                    try {
                        return ReceiptFactory.create(l);
                    }catch (AmountNotAllowedException e){
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void printTaxReturns(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);

        receipts.forEach(pw::println);
        pw.flush();
    }

    public void printStatistics(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);
//        double min = receipts.stream().mapToDouble(Receipt::taxReturn).min().orElse(0);
//        double max = receipts.stream().mapToDouble(Receipt::taxReturn).max().orElse(0);
//        double average = receipts.stream().mapToDouble(Receipt::taxReturn).average().orElse(0);
//        double sum = receipts.stream().mapToDouble(Receipt::taxReturn).sum();
//        int count = receipts.size();

        DoubleSummaryStatistics summaryStatistics = receipts.stream()
                .mapToDouble(Receipt::taxReturn)
                .summaryStatistics();

        double min = summaryStatistics.getMin();
        double max = summaryStatistics.getMax();
        double average = summaryStatistics.getAverage();
        double sum = summaryStatistics.getSum();
        int count = (int) summaryStatistics.getCount();


        pw.printf("min:\t%5.3f\n", min);
        pw.printf("max:\t%5.3f\n", max);
        pw.printf("sum:\t%5.3f\n", sum);
        pw.printf("count:\t%-5d\n", count);
        pw.printf("avg:\t%5.3f\n", average);

        pw.flush();
    }
}
