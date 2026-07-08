package Exam.SecondMidtermExam.Task29;

public class FixedBonus extends EmployeeBonusDecorator {
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