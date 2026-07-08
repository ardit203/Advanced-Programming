package Exam.FirstMidterm.Task28;


import java.util.Comparator;

public abstract class Employee implements Comparable<Employee> {
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
