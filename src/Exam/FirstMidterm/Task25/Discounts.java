package Exam.FirstMidterm.Task25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Discounts {
    private List<Store> stores;

    public Discounts() {
        this.stores = new ArrayList<>();
    }

    public int readStores(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        int count = 0;

        while ((line = br.readLine()) != null){
            stores.add(Store.createStore(line));
            count++;
        }
        return count;
    }

    public List<Store> byAverageDiscount() {
        return stores.stream()
                .sorted(Comparator.comparingDouble(Store::getAverageDiscount).reversed().thenComparing(Store::getName))
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Store> byTotalDiscount() {
        return stores.stream()
                .sorted(Comparator.comparingInt(Store::getAbsoluteDiscount).thenComparing(Store::getName))
                .limit(3)
                .collect(Collectors.toList());
    }
}
