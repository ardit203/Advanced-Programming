package Lab.Lab2.Task4;

import java.util.HashSet;
import java.util.Set;

public class IntegerArray extends ResizableArray<Integer> {
    public double sum() {
        double sum = 0.0;
        for (int i = 0; i < count(); i++) {
            sum += elementAt(i);
        }
        return sum;
    }

    public double mean() {
        return sum() / count();
    }

    public int countNonZero() {
        int count = 0;
        for (int i = 0; i < count(); i++) {
            if (elementAt(i) != 0) {
                count++;
            }
        }
        return count;
    }


//    public IntegerArray distinct() {
//        IntegerArray distinct = new IntegerArray();
//        for (int i = 0; i < count(); i++) {
//            int element = elementAt(i);
//            boolean exists = false;
//            for (int j = 0; j < distinct.count(); j++) {
//                if (distinct.elementAt(j) == element) {
//                    exists = true;
//                    break;
//                }
//            }
//            if (exists) {
//                continue;
//            }
//            distinct.addElement(element);
//        }
//        return distinct;
//    }

    public IntegerArray distinct() {
        IntegerArray distinct = new IntegerArray();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < count(); i++) {
            int element = elementAt(i);
            if (set.add(element)){
                distinct.addElement(element);
            }
        }
        return distinct;
    }

    public IntegerArray increment(int offset) {
        IntegerArray array = new IntegerArray();

        for (int i = 0; i < count(); i++) {
            array.addElement(elementAt(i) + offset);
        }
        return array;
    }
}