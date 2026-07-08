package Exam.SecondMidtermExam.Task29;

public abstract class EmployeeBonusDecorator implements Employee {
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