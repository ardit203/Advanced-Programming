package Lab.Lab1.Task3;

public class HighExpertiseEvaluator implements DoctorEvaluator {
    @Override
    public boolean evaluate(Doctor doctor) {
        return doctor.getLevel() >= 7;
    }
}