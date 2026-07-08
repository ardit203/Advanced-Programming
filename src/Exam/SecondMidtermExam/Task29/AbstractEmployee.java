package Exam.SecondMidtermExam.Task29;

public abstract class AbstractEmployee implements Employee {
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