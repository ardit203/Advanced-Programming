package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Race;

public class F1Test {
    static void main() {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }
}
