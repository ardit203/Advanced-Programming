package Lab.Lab8.Task2;

public class FreeFormQuestion extends TriviaQuestion{
    public FreeFormQuestion(String question, String answer, int value) {
        super(question, answer, value);
    }

    @Override
    public void showQuestion() {
        System.out.println(question);
    }

}
