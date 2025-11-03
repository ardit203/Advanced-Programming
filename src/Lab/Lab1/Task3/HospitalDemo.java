package Lab.Lab1.Task3;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;


public class HospitalDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmergencyRoom er = new EmergencyRoom("University Clinic", 10);

        int n = sc.nextInt();
        sc.nextLine(); // Added to fix the original code, which failed because it didn't consume the newline after nextInt()

        //TODO: Supplier that reads line by line
        Supplier<Doctor> supplier = () -> {
            String line = sc.nextLine();
            String[] parts = line.split("\\s++");
            int license = Integer.parseInt(parts[0]);
            String name = parts[1];
            int level = Integer.parseInt(parts[2]);
            int patients = Integer.parseInt(parts[3]);
            return new Doctor(license, name, level, patients);
        };

        //TODO: Add n doctors to treat using the supplier
        for (int i = 0; i < n; i++) {
            er.treat(supplier);
        }

        sc.close(); // close scanner after done

        //TODO: Print all of the doctors with forEach
        System.out.println("Doctors that are treating:");
        Consumer<Doctor> doctorConsumer = d -> System.out.println(d);
        er.forEach(doctorConsumer);


        //TODO: Print all doctors that treat using Consumer + forEach ---
        System.out.println("\n=== All Doctors ===");
        er.forEach(doctorConsumer);

        //TODO: Use Functional Interface to filter the good doctors ---

        System.out.println("\n=== Doctors with higher number of patients and a higher level of expertise ===");
        DoctorEvaluator evaluator = d -> d.getPatients() > 20 && d.getLevel() >= 7;
        Doctor[] passing = er.evaluate(evaluator);

        for (Doctor d : passing) System.out.println(d);


        //TODO: Print the chief of the department (level = 10)
        System.out.println("\n=== Chief doctor (level = 10) ===");
        Predicate<Doctor> chiefPredicate = (d) -> d.getLevel() == 10;
        Doctor chief = er.findFirst(chiefPredicate);


        System.out.println(chief != null ? chief : "No chief found");

        //TODO: Increase the level of expertise for every doctor by 1
        System.out.println("\n=== Increase all expertise levels by 1 (max 10) ===");
        Consumer<Doctor> printIncrease = d -> {
            d.setLevel(d.getLevel() + 1);
            System.out.println(d);
        };
        er.forEach(printIncrease);

        //TODO: Conditional mutation: increase the level of expertise of every doctor that has more than 30 patients
        System.out.println("\n=== Increase the level of expertise of every doctor by 1 ===");
        Predicate<Doctor> patients = d -> d.getPatients() >= 30;
        Consumer<Doctor> increase = d -> d.setLevel(d.getLevel() + 1);
        er.conditionalMutate(patients, increase);

        //TODO: Map doctors to labels in the format: Name: name, Level: level
        System.out.println("\n=== Map doctors to labels ===");
        Function<Doctor, String> map = (d) -> {
            String out = String.format("Name: %s, Level: %d", d.getName(), d.getLevel());
            System.out.println(out);
            return out;
        };
        er.mapToLabels(map);
    }
}