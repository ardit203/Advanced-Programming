package Book.Ch1_OOP_in_Java.Problem1_1;

import java.io.InputStream;
import java.util.Scanner;

public class ArrayReader {

    public static IntegerArray readIntegerArray(InputStream input) {
        Scanner jin = new Scanner(input);
        int n = jin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = jin.nextInt();
        }
        jin.close();
        return new IntegerArray(a);
    }
}