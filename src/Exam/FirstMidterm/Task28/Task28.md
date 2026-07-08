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
    }
}
```

### Solution
```java
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
    }
}

class EmployeeFactory {
    public static Employee createEmployee(String line, Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel){
        String [] tokens = line.split(";");

        String type = tokens[0];
        String id = tokens[1];
        String level = tokens[2];

        if (type.equalsIgnoreCase("h")){
            double hours = Double.parseDouble(tokens[3]);
            return new HourlyEmployee(id, level, hourlyRateByLevel.get(level), hours);
        }else {
            List<Integer> ticketPoints = new ArrayList<>();
            for (int i = 3; i < tokens.length; i++) {
                ticketPoints.add(Integer.parseInt(tokens[i]));
            }
            return new FreelanceEmployee(id, level, ticketRateByLevel.get(level), ticketPoints);
        }
    }
}



abstract class Employee implements Comparable<Employee> {
    protected String id;
    protected String level;
    protected double rate;
    protected double salary;

    public Employee(String id, String level, double rate) {
        this.id = id;
        this.level = level;
        this.rate = rate;
        this.salary = 0;
    }
    

    @Override
    public int compareTo(Employee other) {
        int compare1 = Double.compare(other.salary, this.salary);
        if(compare1 != 0) return compare1;

        return this.level.compareTo(other.level);
    }

    public String getLevel() {
        return level;
    }

    protected String getCommonData(){
        return String.format("Employee ID: %s Level: %s Salary: %.2f",id, level, salary);
    }
}

class HourlyEmployee extends Employee {
    private double hours;
    private double regularHours;
    private double overtime;


    public HourlyEmployee(String id, String level, double rate, double hours) {
        super(id, level, rate);
        this.hours = hours;
        calculateSalary();
    }


    protected void calculateSalary() {
        regularHours = Math.min(hours, 40);
        overtime = Math.max(0, hours - 40);

        salary = regularHours * rate + overtime * rate * 1.5;
    }

    @Override
    public String toString() {
        return String.format("%s Regular hours: %.2f Overtime hours: %.2f", getCommonData(), regularHours, overtime);
    }
}

class FreelanceEmployee extends Employee {
    private List<Integer> ticketPoints;
    private int ticketPointsSum;

    public FreelanceEmployee(String id, String level, double rate, List<Integer> ticketPoints) {
        super(id, level, rate);
        this.ticketPoints = ticketPoints;
        calculateSalary();
    }


    private void calculateSalary() {
        ticketPointsSum = ticketPoints
                .stream()
                .mapToInt(t -> t)
                .sum();
        salary = ticketPointsSum * rate;
    }

    @Override
    public String toString() {
        return String.format("%s Tickets count: %d Tickets points: %d", getCommonData(), ticketPoints.size(), ticketPointsSum);
    }
}



class PayrollSystem {
    private Map<String, Double> hourlyRateByLevel;
    private Map<String, Double> ticketRateByLevel;
    private List<Employee> employees;

    public PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        this.hourlyRateByLevel = hourlyRateByLevel;
        this.ticketRateByLevel = ticketRateByLevel;
        this.employees = new ArrayList<>();
    }

    public void readEmployees(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        employees = br.lines()
                .map(l -> EmployeeFactory.createEmployee(l, hourlyRateByLevel, ticketRateByLevel))
                .collect(Collectors.toList());
    }

    public Map<String, Set<Employee>> printEmployeesByLevels(PrintStream os, Set<String> levels) {
        Map<String, Set<Employee>> grouped = employees.stream()
                .filter(e -> levels.contains(e.getLevel()))
                .collect(Collectors.groupingBy(
                        Employee::getLevel,
                        TreeMap::new,
                        Collectors.toCollection(TreeSet::new)
                ));

        PrintWriter pw = new PrintWriter(os);

        grouped.forEach((key, value) -> {
            pw.println("LEVEL: " + key);
            pw.println("Employees: ");
            value.forEach(pw::println);
            pw.println("------------");
        });

        pw.flush();

        return grouped;
    }
}
```