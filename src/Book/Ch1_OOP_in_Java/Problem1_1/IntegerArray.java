package Book.Ch1_OOP_in_Java.Problem1_1;

import java.util.Arrays;

public final class IntegerArray {
    private int[] a;

    public IntegerArray(int[] a) {
        this.a = Arrays.copyOf(a, a.length);
    }

    private IntegerArray(int[] a, boolean byReference) {
        if (byReference) {
            this.a = a;
        } else {
            this.a = Arrays.copyOf(a, a.length);
        }
    }

    public int length() {
        return a.length;
    }

    public int getElementAt(int i) {
        return a[i];
    }

    public int sum() {
        int sum = 0;
        for (int i : a){
            sum += i;
        }
        return sum;
    }

    public double average() {
        return sum() * 1.0 / length();
    }

    public IntegerArray getSorted() {
        int[] sorted = Arrays.copyOf(a, length());
        Arrays.sort(sorted);
        return new IntegerArray(sorted);
    }

    public IntegerArray concat(IntegerArray ia) {
        int[] res_a = new int[a.length + ia.a.length];
        System.arraycopy(a, 0, res_a, 0, a.length);
        System.arraycopy(ia.a, 0, res_a, a.length, ia.a.length);
        return new IntegerArray(res_a, true);
    }

    public String toString() {
        return Arrays.toString(a);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(a);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IntegerArray other = (IntegerArray) obj;
        return Arrays.equals(a, other.a);
    }
}
