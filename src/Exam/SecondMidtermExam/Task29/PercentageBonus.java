package Exam.SecondMidtermExam.Task29;

public class PercentageBonus extends EmployeeBonusDecorator {
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