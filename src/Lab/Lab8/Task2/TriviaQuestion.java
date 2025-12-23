package Lab.Lab8.Task2;

public abstract class TriviaQuestion {
    protected String question;        // Actual question
    protected String answer;        // Answer to question
    protected int value;            // Point value of question

    public TriviaQuestion() {
        question = "";
        answer = "";
        value = 0;
    }

    public TriviaQuestion(String question, String answer, int value) {
        this.question = question;
        this.answer = answer;
        this.value = value;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getValue() {
        return value;
    }

    public boolean checkCorrectness(String answer){
        return this.answer.equalsIgnoreCase(answer);
    }

    public abstract void showQuestion();
}
