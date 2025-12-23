package Lab.Lab8.Task2;

import java.util.ArrayList;

public class TriviaData {

    private ArrayList<TriviaQuestion> data;

    public TriviaData() {
        data = new ArrayList<>();
    }

    public void addQuestion(String question, String answer, int value, QuestionType questionType) {
        TriviaQuestion triviaQuestion =
                questionType == QuestionType.TRUE_FALSE
                ? new TrueFalseQuestion(question, answer, value)
                : new FreeFormQuestion(question, answer, value);

        data.add(triviaQuestion);
    }

    public void showQuestion(int index) {
        TriviaQuestion triviaQuestion = data.get(index);
        System.out.println("Question " + (index + 1) + ".  " + triviaQuestion.getValue() + " points.");
        triviaQuestion.showQuestion();
    }

    public int numQuestions() {
        return data.size();
    }

    public TriviaQuestion getQuestion(int index) {
        return data.get(index);
    }
}
