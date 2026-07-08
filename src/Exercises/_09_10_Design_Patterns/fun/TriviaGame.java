package Exercises._09_10_Design_Patterns.fun;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


enum QuestionType {
    TRUE_FALSE, FREEFORM
}

interface IQuestionFactory{
    TriviaQuestion createQuestion(String question, String answer, int value);
}

class TrueFalseQuestionFactory implements IQuestionFactory{
    @Override
    public TriviaQuestion createQuestion(String question, String answer, int value) {
        return new TrueFalseQuestion(question, answer, value);
    }
}


class FreeFormQuestionFactory implements IQuestionFactory{
    @Override
    public TriviaQuestion createQuestion(String question, String answer, int value) {
        return new FreeFormQuestion(question, answer, value);
    }
}

class QuestionFactoryProvider {
    static IQuestionFactory getFactory(QuestionType questionType) {
        switch (questionType) {
            case TRUE_FALSE:
                return new TrueFalseQuestionFactory();
            case FREEFORM:
                return new FreeFormQuestionFactory();
            default:
                throw new IllegalArgumentException("Unknown theme");
        }

    }
}



abstract class TriviaQuestion {
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

class TrueFalseQuestion extends TriviaQuestion{

    public TrueFalseQuestion(String question, String answer, int value) {
        super(question, answer, value);
    }

    @Override
    public void showQuestion() {
        System.out.println(question);
        System.out.println("Enter 'T' for true or 'F' for false.");
    }
}

class FreeFormQuestion extends TriviaQuestion{
    public FreeFormQuestion(String question, String answer, int value) {
        super(question, answer, value);
    }

    @Override
    public void showQuestion() {
        System.out.println(question);
    }

}

class TriviaData {

    private ArrayList<TriviaQuestion> data;

    public TriviaData() {
        data = new ArrayList<>();
    }

    public void addQuestion(String question, String answer, int value, QuestionType questionType) {
        IQuestionFactory factory = QuestionFactoryProvider.getFactory(questionType);
        TriviaQuestion triviaQuestion = factory.createQuestion(question, answer, value);

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

public class TriviaGame {

    private TriviaData triviaData;	// Questions

    public TriviaGame() {
        // Load questions
        triviaData = new TriviaData();
        triviaData.addQuestion("The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3, QuestionType.FREEFORM);
        triviaData.addQuestion("Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1, QuestionType.TRUE_FALSE);
        triviaData.addQuestion("1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2, QuestionType.FREEFORM);
        triviaData.addQuestion("I am the geometric figure most like a lost parrot",
                "polygon", 2, QuestionType.FREEFORM);
        triviaData.addQuestion("Generics were introducted to Java starting at version 5.0.",
                "T", 1, QuestionType.TRUE_FALSE);
    }


    // Main game loop

    public static void main(String[] args) {
        int score = 0;			// Overall score
        int questionNum = 0;	// Which question we're asking
        TriviaGame game = new TriviaGame();
        Scanner keyboard = new Scanner(System.in);
        // Ask a question as long as we haven't asked them all
        while (questionNum < game.triviaData.numQuestions()) {
            // Show question
            game.triviaData.showQuestion(questionNum);
            // Get answer
            String answer = keyboard.nextLine();
            // Validate answer
            TriviaQuestion question = game.triviaData.getQuestion(questionNum);
            boolean isCorrect = question.checkCorrectness(answer);

            if(isCorrect){
                System.out.println("That is correct!  You get " + question.getValue() + " points.");
                score += question.getValue();
            }else {
                System.out.println("Wrong, the correct answer is " + question.getAnswer());
            }

            System.out.println("Your score is " + score);
            questionNum++;
        }
        System.out.println("Game over!  Thanks for playing!");
    }
}