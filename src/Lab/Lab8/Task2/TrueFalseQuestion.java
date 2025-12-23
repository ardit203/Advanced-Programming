package Lab.Lab8.Task2;

public class TrueFalseQuestion extends TriviaQuestion{

    public TrueFalseQuestion(String question, String answer, int value) {
        super(question, answer, value);
    }

    @Override
    public void showQuestion() {
        System.out.println(question);
        System.out.println("Enter 'T' for true or 'F' for false.");
    }
}
