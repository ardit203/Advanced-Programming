# Lab Exercise 1 - Advanced Programming
## Task 3

### **Class**`Doctor`

- Represents a doctor with basic information about him: license number, his name, expertise level (1-10), number of patients
- If the expertise level is 10, the doctor is considered to be a Chief.
- The toString method has been implemented, which prints the doctor in a readable format (name, license number, specialization, number of patients, and if he has the highest level of expertise, [Chief] is also printed.)
- When changing the level of expertise, the value must be in the range of 1-10 and must not be lower than the previous one

### Class `EmergencyRoom`

- represents an emergency room in a hospital and contains information about: the name of the hospital, medical staff (an array of Doctor objects), capacity
- The following methods have been implemented: treat, forEach, count, findFirst, filter, mapToLabels, mutate, conditionalMutate, countForEvaluation, evaluate


1. `treat(Supplier<Doctor> supplier)` - adds a doctor to the emergency room, if there is a free place
2. `forEach(Consumer<Doctor> action)` - applies a given action (Consumer) to each doctor in the array (example: print)
3. `count(Predicate<Doctor> condition)` - returns the number of doctors who meet the given condition
4. `findFirst(Predicate<Doctor> condition)` - returns the first doctor who meets a given condition
5. `filter(Predicate<Doctor> condition)` - Returns a new array containing only the doctors who meet the condition.
6. `mapToLabels(Function<Doctor, String> mapper)` - Returns an array of text descriptions, obtained by transforming each doctor with the given function.
7. `mutate(Consumer<Student> mutator)` - Applies a change to all doctors (for example, increasing the level of expertise)
8. `conditionalMutate(Predicate<Doctor> condition, Consumer<Doctor> mutator)` - Applies the change only to doctors who meet the given condition.
9. `countForEvaluation(DoctorEvaluator evaluator)` - Uses DoctorEvaluator to count how many doctors meet a condition
10. `evaluate(DoctorEvaluator evaluator)` - Returns a new array containing all doctors who meet the condition set by DoctorEvaluator
11. `toString()` - Returns a text description of the emergency center, containing the name of the hospital, the number of doctors currently working in it and a list of them.

On your part, you need to:

- Create a functional interface `DoctorEvaluator` that will have one method: `boolean evaluate(Doctor doctor)`;
- Create a class `HighExpertiseEvaluator` that will return TRUE only if the doctor has an expertise level greater than or equal to 7.
- Resolve the requirements in the main section:
  - Open a `Scanner` and read an **integer n** that indicates the number of doctors to be entered.
  - Create a `Supplier<Doctor>` that reads data about a doctor from the console (license number, name, expertise level and number of patients) and returns a `new Doctor` object.
  - Add n doctors to using the `treat` method.
  - Use `Consumer<Doctor>` together with `forEach` to print all doctors currently working in the emergency center.
  - Use the created functional interfaces to determine which doctors:
    - have more than 20 patients
    - have a higher level of expertise (7+)
    - Combine the two states from the functional interfaces and use the evaluate method of the `EmergencyRoom` class to display only those doctors.
  - Use `findFirst` to find and display the Chief doctor in the emergency room.
  - Use `mutate` to increase the expertise level of all doctors by 1.
  - Use `conditionalMutate` to increase the expertise level of only doctors with more than 30 patients by 1.
  - Use `mapToLabels` to transform all doctors into text descriptions and print them.
  - Finally, print all the information about the emergency room using the `toString` method.

Starter code:

```java
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;


// TODO: Create a functional interface to evaluate the doctors

// TODO: Create a HighExpertiseEvaluator class that will implement the functional interface and override the evaluate method


class Doctor {
    private final int licenseNumber;    // e.g., 1234
    private String name;
    private int level;                  // 1..10 (10 = chief)

    private int patients;

    public Doctor(int licenseNumber, String name, int level, int patients) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.level = level;
        this.patients = patients;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level > 10) {
            this.level = 10;
            return;
        }
        if (level < 1) {
            this.level = 1;
            return;
        }
        this.level = level;
    }

    public int getPatients() {
        return patients;
    }

    public void setPatients(int patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) %d %d %s", name, licenseNumber, level, patients, level == 10 ? "[Chief]" : "");
    }
}

class EmergencyRoom {
    private final String hospitalName;
    private final Doctor[] doctors;
    private int size = 0;

    public EmergencyRoom(String title, int doctorCapacity) {
        this.hospitalName = title;
        this.doctors = new Doctor[doctorCapacity];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return doctors.length;
    }

    /**
     * Add a doctor provided by a Supplier. Demonstrates Supplier<T>.
     */
    public boolean treat(Supplier<Doctor> supplier) {
        if (size >= doctors.length) {
            return false;
        }
        doctors[size++] = supplier.get();
        return true;
    }

    /**
     * Apply a Consumer to each doctor (side effects allowed, e.g., print or mutate).
     */
    public void forEach(Consumer<Doctor> action) {
        for (int i = 0; i < size; i++) {
            action.accept(doctors[i]);
        }
    }

    /**
     * Count doctors satisfying a Predicate.
     */
    public int count(Predicate<Doctor> predicate) {
        int c = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(doctors[i])) {
                c++;
            }
        }
        return c;
    }

    /**
     * Find first doctor that matches; returns null if none.
     */
    public Doctor findFirst(Predicate<Doctor> predicate) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(doctors[i])) {
                return doctors[i];
            }
        }
        return null;
    }

    /**
     * Filter doctors into a NEW array (still no collections).
     */
    public Doctor[] filter(Predicate<Doctor> predicate) {
        // 1st pass: count matches to size array exactly
        int matches = count(predicate);
        Doctor[] out = new Doctor[matches];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(doctors[i])) {
                out[j++] = doctors[i];
            }
        }
        return out;
    }

    /**
     * Map doctors to Strings (labels) with a Function.
     * (We return String[] to avoid generics + array creation complexity.)
     */
    public String[] mapToLabels(Function<Doctor, String> mapper) {
        String[] out = new String[size];
        for (int i = 0; i < size; i++) {
            out[i] = mapper.apply(doctors[i]);
        }
        return out;
    }

    /**
     * In-place update using a Consumer (mutation allowed).
     * Example: increase level +1, cap at 10.
     */
    public void mutate(Consumer<Doctor> mutator) {
        for (int i = 0; i < size; i++) {
            mutator.accept(doctors[i]);
        }
    }

    public void conditionalMutate(Predicate<Doctor> condition, Consumer<Doctor> mutator) {
        for (int i = 0; i < size; i++) {
            if (condition.test(doctors[i])) {
                mutator.accept(doctors[i]);
            }
        }

    }

    public int countForEvaluation(DoctorEvaluator evaluator) {
        int c = 0;
        for (int i = 0; i < size; i++) {
            if (evaluator.evaluate(doctors[i])) {
                c++;
            }
        }
        return c;
    }

    public Doctor[] evaluate(DoctorEvaluator evaluator) {
        int outSize = countForEvaluation(evaluator);
        Doctor[] out = new Doctor[outSize];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (evaluator.evaluate(doctors[i])) {
                out[j++] = doctors[i];
            }
        }
        return out;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hospital: " + hospitalName + " (" + size + "/" + doctors.length + " doctors)");
        for (Doctor doctor : doctors) {
            sb.append(doctor.toString()).append("\n");
        }
        return sb.toString();

    }
}

public class HospitalDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmergencyRoom er = new EmergencyRoom("University Clinic", 10);

        int n = sc.nextInt();

        //TODO: Supplier that reads line by line

        //TODO: Add n doctors to treat using the supplier

        sc.close(); // close scanner after done

        //TODO: Print all of the doctors with forEach
        System.out.println("\nDoctors that are treating:");

        //TODO: Print all doctors that treat using Consumer + forEach ---
        System.out.println("\n=== All Doctors ===");

        //TODO: Use Functional Interface to filter the good doctors ---

        System.out.println("\n=== Doctors with higher number of patients and a higher level of expertise ===");
        for (Doctor d : passing) System.out.println(d);

        //TODO: Print the chief of the department (level = 10)
        System.out.println("\n=== Chief doctor (level = 10) ===");
        
        System.out.println(chief != null ? chief : "No chief found");

        //TODO: Increase the level of expertise for every doctor by 1
        System.out.println("\n=== Increase all expertise levels by 1 (max 10) ===");


        //TODO: Conditional mutation: increase the level of expertise of every doctor that has more than 30 patients
        System.out.println("\n=== Increase the level of expertise of every doctor by 1 ===");

        //TODO: Map doctors to labels in the format: Name: name, Level: level
        System.out.println("\n=== Map doctors to labels ===");
    }
}
```