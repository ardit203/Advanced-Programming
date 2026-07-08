You need to (finish) implementing the application for tracking the work engagement of employees in an IT company.
Recommendation: To avoid problems with identical class names, use separate packages for the two tasks if you want to solve them separately.

For that purpose, implement the class `PayrollSystem` in which information about employees in the company will be stored.
As in the previous task, there are two types of employees: `HourlyEmployee` and `FreelanceEmployee`.
The salary calculation for both types of employees is the same as in the previous task.
For the class `PayrollSystem`, implement:

* `PayrollSystem(Map<String,Double> hourlyRateByLevel, Map<String,Double> ticketRateByLevel)` – same as in the previous task
* `Employee createEmployee (String line)` – a method which, based on an input string containing the information for a given employee, will create and return an object of class Employee. Additionally, the method will store the employee in the payroll system.
  The employee information is in the same format as in the first task, except that in this case there may be a bonus for the employee, which is separated by a space from the employee’s information.
  There are two types of bonuses an employee can receive:

    * Fixed monetary bonus (written as a number). Example: `H;ID;level;hours; 100` (in this case the employee receives a fixed salary bonus of 100$)
    * Percentage bonus (written as a number with a percent sign). Example: `F;ID;level;ticketPoints1;ticketPoints2;...;ticketPointsN; 10%` (in this case the employee receives a percentage bonus of 10% of their salary).
* In the previous method, with an exception of type `BonusNotAllowedException`, prevent the creation of an employee who is assigned a fixed bonus greater than 1000$ or a percentage bonus greater than 20%.
* `Map<String, Double> getOvertimeSalaryForLevels ()` – a method which returns a map where the key is the employee level, and the value is the total amount the company has paid for overtime work for the employees of that level.
* `void printStatisticsForOvertimeSalary ()` – a method which prints statistics (minimum, maximum, sum, average) of the overtime payments for all employees in the company.
* `Map<String, Integer> ticketsDoneByLevel()` – a method which returns a map where the key is the employee level, and the value is the number of ticket points completed by the employees of the corresponding level.
* `Collection<Employee> getFirstNEmployeesByBonus (int n)` – a method which returns a sorted collection of the first n employees sorted in descending order by the bonus they received on their salary.

### Starter code
```java
import java.util.*;
import java.util.stream.Collectors;

public class PayrollSystemTest2 {

    public static void main(String[] args) {

        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 11 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5.5 + i * 2.5);
        }

        Scanner sc = new Scanner(System.in);

        int employeesCount = Integer.parseInt(sc.nextLine());

        PayrollSystem ps = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);
        Employee emp = null;
        for (int i = 0; i < employeesCount; i++) {
            try {
                emp = ps.createEmployee(sc.nextLine());
            } catch (BonusNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        }

        int testCase = Integer.parseInt(sc.nextLine());

        switch (testCase) {
            case 1: //Testing createEmployee
                if (emp != null)
                    System.out.println(emp);
                break;
            case 2: //Testing getOvertimeSalaryForLevels()
                ps.getOvertimeSalaryForLevels().forEach((level, overtimeSalary) -> {
                    System.out.printf("Level: %s Overtime salary: %.2f\n", level, overtimeSalary);
                });
                break;
            case 3: //Testing printStatisticsForOvertimeSalary()
                ps.printStatisticsForOvertimeSalary();
                break;
            case 4: //Testing ticketsDoneByLevel
                ps.ticketsDoneByLevel().forEach((level, overtimeSalary) -> {
                    System.out.printf("Level: %s Tickets by level: %d\n", level, overtimeSalary);
                });
                break;
            case 5: //Testing getFirstNEmployeesByBonus (int n)
                ps.getFirstNEmployeesByBonus(Integer.parseInt(sc.nextLine())).forEach(System.out::println);
                break;
        }

    }
}
```

### Solution
```java
package Exam.SecondMidtermExam.Task29;

import java.util.*;
import java.util.stream.Collectors;


public class PayrollSystemTest2 {
    public static void main(String[] args) {

        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 11 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5.5 + i * 2.5);
        }

        Scanner sc = new Scanner(System.in);

        int employeesCount = Integer.parseInt(sc.nextLine());

        PayrollSystem ps = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);
        Employee emp = null;
        for (int i = 0; i < employeesCount; i++) {
            try {
                emp = ps.createEmployee(sc.nextLine());
            } catch (BonusNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        }

        int testCase = Integer.parseInt(sc.nextLine());

        switch (testCase) {
            case 1: //Testing createEmployee
                if (emp != null)
                    System.out.println(emp);
                break;
            case 2: //Testing getOvertimeSalaryForLevels()
                ps.getOvertimeSalaryForLevels().forEach((level, overtimeSalary) -> {
                    System.out.printf("Level: %s Overtime salary: %.2f\n", level, overtimeSalary);
                });
                break;
            case 3: //Testing printStatisticsForOvertimeSalary()
                ps.printStatisticsForOvertimeSalary();
                break;
            case 4: //Testing ticketsDoneByLevel
                ps.ticketsDoneByLevel().forEach((level, overtimeSalary) -> {
                    System.out.printf("Level: %s Tickets by level: %d\n", level, overtimeSalary);
                });
                break;
            case 5: //Testing getFirstNEmployeesByBonus (int n)
                ps.getFirstNEmployeesByBonus(Integer.parseInt(sc.nextLine())).forEach(System.out::println);
                break;
        }

    }
}

class BonusNotAllowedException extends RuntimeException {
    public BonusNotAllowedException(String bonus) {
        super(String.format("Bonus of %s is not allowed", bonus));
    }
}


interface Employee {
    double getSalary();

    double getBonus();

    double getOvertime();

    int getTicketPoints();

    String getLevel();

    void setBonus(double bonus);
}


abstract class AbstractEmployee implements Employee {
    protected String id;
    protected String level;
    protected double rate;
    protected double salary;
    protected double bonus;

    public AbstractEmployee(String id, String level, double rate) {
        this.id = id;
        this.level = level;
        this.rate = rate;
        this.salary = 0;
        this.bonus = 0;
    }


    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String getLevel() {
        return level;
    }

    @Override
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f", id, level, (salary + bonus));
    }
}


class HourlyEmployee extends AbstractEmployee {
    private double hours;
    private double overtime;
    private double regularHours;

    public HourlyEmployee(String name, String level, double rate, double hours) {
        super(name, level, rate);
        this.hours = hours;
        calculateSalary();
    }

    private void calculateSalary() {
        this.regularHours = Math.min(hours, 40);
        this.overtime = Math.max(0, hours - 40);
        salary = regularHours * rate + overtime * rate * 1.5;
    }

    @Override
    public double getOvertime() {
        return overtime * rate * 1.5;
    }

    @Override
    public int getTicketPoints() {
        return -1;
    }

    @Override
    public String toString() {
        return String.format("%s Regular hours: %.2f Overtime hours: %.2f", super.toString(), regularHours, overtime);
    }
}


class FreelanceEmployee extends AbstractEmployee {
    private List<Integer> ticketPoints;
    private int ticketPointsSum;


    public FreelanceEmployee(String name, String level, double rate, List<Integer> ticketPoints) {
        super(name, level, rate);
        this.ticketPoints = ticketPoints;
        calculateSalary();
    }

    private void calculateSalary() {
        this.ticketPointsSum = ticketPoints.stream().mapToInt(t -> t).sum();
        salary = ticketPointsSum * rate;
    }

    @Override
    public double getOvertime() {
        return -1;
    }

    @Override
    public int getTicketPoints() {
        return ticketPoints.size();
    }

    @Override
    public String toString() {
        return String.format("%s Tickets count: %d Tickets points: %d", super.toString(), ticketPoints.size(), ticketPointsSum);
    }
}


abstract class EmployeeBonusDecorator implements Employee {
    protected Employee employee;

    public EmployeeBonusDecorator(Employee employee) {
        this.employee = employee;
    }

    @Override
    public double getOvertime() {
        return employee.getOvertime();
    }

    @Override
    public int getTicketPoints() {
        return employee.getTicketPoints();
    }

    @Override
    public String getLevel() {
        return employee.getLevel();
    }

    @Override
    public void setBonus(double bonus) {
        employee.setBonus(bonus);
    }

    @Override
    public String toString() {
        return String.format("%s Bonus: %.2f", employee.toString(), getBonus());
    }
}


class FixedBonus extends EmployeeBonusDecorator {
    private double bonus;

    public FixedBonus(Employee employee, double bonus) {
        super(employee);
        this.bonus = bonus;
        employee.setBonus(bonus);
    }

    @Override
    public double getSalary() {
        return employee.getSalary() + bonus;
    }

    @Override
    public double getBonus() {
        return bonus;
    }
}


class PercentageBonus extends EmployeeBonusDecorator {
    private double percent;
    private double bonus;

    public PercentageBonus(Employee employee, double percent) {
        super(employee);
        this.percent = percent;
        bonus = employee.getSalary() * percent / 100;
        setBonus(bonus);
    }

    @Override
    public double getSalary() {
        return employee.getSalary() + bonus;
    }

    @Override
    public double getBonus() {
        return bonus;
    }
}


class EmployeeFactory {
    public static Employee createEmployee(String line, Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        String[] parts = line.split("\\s++");

        Employee employee = createAbstractEmployee(parts[0], hourlyRateByLevel, ticketRateByLevel);

        if (parts.length != 1) {
            String bonusType = parts[1].contains("%") ? "percentage" : "fixed";
            double bonus = Double.parseDouble(parts[1].split("%")[0]);

            if (bonusType.equals("percentage")) {
                if (bonus > 20) {
                    throw new BonusNotAllowedException(String.format("%.2f%%", bonus));
                }
                employee = new PercentageBonus(employee, bonus);
            } else {
                if (bonus > 1000) {
                    throw new BonusNotAllowedException(String.format("%.0f$", bonus));
                }
                employee = new FixedBonus(employee, bonus);
            }
        }
        return employee;
    }

    public static Employee createAbstractEmployee(String line, Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        String[] tokens = line.split(";");
        String type = tokens[0];
        String name = tokens[1];
        String level = tokens[2];
        double rate;

        if (type.equalsIgnoreCase("h")) {
            rate = hourlyRateByLevel.get(level);
            double hours = Double.parseDouble(tokens[3]);
            return new HourlyEmployee(name, level, rate, hours);
        } else {
            rate = ticketRateByLevel.get(level);
            List<Integer> ticketPoints = new ArrayList<>();

            for (int i = 3; i < tokens.length; i++) {
                ticketPoints.add(Integer.parseInt(tokens[i]));
            }
            return new FreelanceEmployee(name, level, rate, ticketPoints);
        }
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

    public Employee createEmployee(String line) {
        Employee employee = EmployeeFactory.createEmployee(line, hourlyRateByLevel, ticketRateByLevel);
        employees.add(employee);
        return employee;
    }

    public Map<String, Double> getOvertimeSalaryForLevels() {
        Map<String, Double> grouped = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getLevel,
                        Collectors.summingDouble(Employee::getOvertime)
                ));

        List<String> groupWithOnlyOne = grouped.keySet()
                .stream()
                .filter(key -> grouped.get(key) == -1)
                .collect(Collectors.toList());

        groupWithOnlyOne.forEach(grouped::remove);

        return grouped;
    }

    public void printStatisticsForOvertimeSalary() {
        DoubleSummaryStatistics stats = employees.stream()
                .filter(e -> e.getOvertime() != -1)
                .mapToDouble(Employee::getOvertime)
                .summaryStatistics();

        System.out.printf("Statistics for overtime salary: Min: %.2f Average: %.2f Max: %.2f Sum: %.2f",
                stats.getMin(), stats.getAverage(), stats.getMax(), stats.getSum());
    }

    public Map<String, Integer> ticketsDoneByLevel() {
        return employees.stream()
                .filter(e -> e.getTicketPoints() != -1)
                .collect(Collectors.groupingBy(
                        Employee::getLevel,
                        Collectors.summingInt(Employee::getTicketPoints)
                ));
    }

    public List<Employee> getFirstNEmployeesByBonus(int n) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getBonus).reversed())
                .limit(n)
                .collect(Collectors.toList());

    }
}
```