You need to implement an application for tracking the work engagement of employees in an IT company.
For this purpose, you need to implement the class **`PayrollSystem`** in which the information about the employees of the company will be stored.
There are two types of employees **`HourlyEmployee`** and **`FreelanceEmployee`**.
`HourlyEmployee` receives a salary based on the total number of hours worked, while `FreelanceEmployee` receives a salary based on the points of the tickets they have solved.
For the class **`PayrollSystem`**, you need to implement:

* **`PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel)`** – constructor with two arguments – maps.
  The first map indicates how much the hourly wage is for a given level that the employee has at their job, and the second map indicates how much the pay per ticket point is for a given level for freelancers.
* **`void readEmployeesData (InputStream is)`** – method for reading the employee data of the company, where for each employee the data is given in the following format:

    * If the employee is an **`HourlyEmployee`**:
      **`H;ID;level;hours`**
    * If the employee is a **`FreelanceEmployee`**:
      **`F;ID;level;ticketPoints1;ticketPoints2;...;ticketPointsN`**
* **`Map<String, Collection<Employee>> printEmployeesByLevels (OutputStream os, Set<String> levels)`** – method which writes to the output stream a map of the employees into the levels `levels`, grouped by level.
  The employees must be sorted by salary in descending order within each level.
  (If two employees have the same salary, then they should be compared by level.)

Additional information:

* The salary of an **`HourlyEmployee`** is calculated by multiplying the working hours up to 40 hours with the hourly rate for the level, and all hours above 40 are multiplied with the hourly rate multiplied by coefficient 1.5.
* The salary of a **`FreelanceEmployee`** is calculated as the sum of the points for the tickets the programmer solved, multiplied by the ticket rate for the level.

### Starter code
```java
public class PayrollSystemTest {

    public static void main(String[] args) {

        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 10 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5 + i * 2.5);
        }

        PayrollSystem payrollSystem = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);

        System.out.println("READING OF THE EMPLOYEES DATA");
        payrollSystem.readEmployees(System.in);

        System.out.println("PRINTING EMPLOYEES BY LEVEL");
        Set<String> levels = new LinkedHashSet<>();
        for (int i=5;i<=10;i++) {
            levels.add("level"+i);
        }
        Map<String, Set<Employee>> result = payrollSystem.printEmployeesByLevels(System.out, levels);
        result.forEach((level, employees) -> {
            System.out.println("LEVEL: "+ level);
            System.out.println("Employees: ");
            employees.forEach(System.out::println);
        });


    }
}
```

### Solution
```java
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.function.Supplier;



abstract class Employee implements Comparable<Employee> {
    private String id;
    private double rate;
    private String level;

    public Employee(String id, String level, double rate) {
        this.id = id;
        this.level = level;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public double getRate() {
        return rate;
    }

    public String getLevel() {
        return level;
    }

    public abstract double salary();

    @Override
    public int compareTo(Employee other){
        return Comparator.comparingDouble(Employee::salary).compare(other, this);
    }
    
    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f", id, level, salary());
    }
}



class HourlyEmployee extends Employee {
    private double hours;
    private double overtime;
    private double regular;

    public HourlyEmployee(String id, String level, double rate, double hours) {
        super(id, level, rate);
        this.hours = hours;
        this.overtime = Math.max(0, hours - 40);
        this.regular = hours - overtime;
    }

    public double getHours() {
        return hours;
    }

    @Override
    public double salary() {
        return regular * getRate() + overtime * getRate() * 1.5;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Regular hours: %.2f Overtime hours: %.2f", regular, overtime);
    }
}




class FreelanceEmployee extends Employee {
    private List<Integer> points;

    public FreelanceEmployee(String id, String level, double rate, List<Integer> points) {
        super(id, level, rate);
        this.points = points;
    }

    public List<Integer> getPoints() {
        return points;
    }

    @Override
    public double salary() {
        return points.stream().mapToInt(tp -> tp).sum() * getRate();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " Tickets count: %d Tickets points: %d",
                points.size(),
                points.stream().mapToInt(i -> i).sum()
        );
    }
}




class EmployeeFactory {
    public static Employee create(String line, Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        String[] tokens = line.split(";");
        String type = tokens[0];
        String id = tokens[1];
        String level = tokens[2];

        if (type.equals("H")) {
            double hours = Double.parseDouble(tokens[3]);
            double rate = hourlyRateByLevel.get(level);
            return new HourlyEmployee(id, level, rate, hours);
        } else if (type.equals("F")) {
            double rate = ticketRateByLevel.get(level);
            List<Integer> points = Arrays.stream(tokens).skip(3).map(Integer::parseInt).collect(Collectors.toList());
            return new FreelanceEmployee(id, level, rate, points);
        } else {
            throw new RuntimeException();
        }
    }
}





class PayrollSystem {
    private List<Employee> employees;
    private Map<String, Double> hourlyRateByLevel;
    private Map<String, Double> ticketRateByLevel;

    PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        this.hourlyRateByLevel = hourlyRateByLevel;
        this.ticketRateByLevel = ticketRateByLevel;
        this.employees = new ArrayList<>();
    }


    public void readEmployees(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        employees = br.lines()
                .map(l -> EmployeeFactory.create(l, hourlyRateByLevel, ticketRateByLevel))
                .collect(Collectors.toList());
    }


    public Map<String, Set<Employee>> printEmployeesByLevels (OutputStream os, Set<String> levels){
        Map<String, Set<Employee>> map = new TreeMap<>();

        Map<String, Set<Employee>> grouped = employees.stream().collect(Collectors.groupingBy(
                Employee::getLevel,
                (Supplier<TreeMap<String, Set<Employee>>>) TreeMap::new,
                Collectors.toCollection(TreeSet::new)
        ));

        for (String level : levels){
            Set<Employee> set = grouped.get(level);
            if(set != null){
                map.put(level, set);
            }
        }
        return map;
    }
}



public class PayrollSystemTest {

    public static void main(String[] args) {

        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 10 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5 + i * 2.5);
        }

        PayrollSystem payrollSystem = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);

        System.out.println("READING OF THE EMPLOYEES DATA");
        payrollSystem.readEmployees(System.in);

        System.out.println("PRINTING EMPLOYEES BY LEVEL");
        Set<String> levels = new LinkedHashSet<>();
        for (int i=5;i<=10;i++) {
            levels.add("level"+i);
        }
        Map<String, Set<Employee>> result = payrollSystem.printEmployeesByLevels(System.out, levels);
        result.forEach((level, employees) -> {
            System.out.println("LEVEL: "+ level);
            System.out.println("Employees: ");
            employees.forEach(System.out::println);
            System.out.println("------------");
        });


    }
}
```