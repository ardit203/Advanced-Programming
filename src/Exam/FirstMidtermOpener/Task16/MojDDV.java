package Exam.FirstMidtermOpener.Task16;

import java.io.*;
import java.util.ArrayList;
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
}
