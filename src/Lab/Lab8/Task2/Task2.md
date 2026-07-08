Refactor the code
```java
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


class TriviaQuestion {

    public static final int TRUEFALSE = 0;
    public static final int FREEFORM = 1;
    public String question;		// Actual question
    public String answer;		// Answer to question
    public int value;			// Point value of question
    public int type;			// Question type, TRUEFALSE or FREEFORM

    public TriviaQuestion() {
        question = "";
        answer = "";
        value = 0;
        type = FREEFORM;
    }

    public TriviaQuestion(String q, String a, int v, int t) {
        question = q;
        answer = a;
        value = v;
        type = t;
    }
}

class TriviaData {

    private ArrayList<TriviaQuestion> data;

    public TriviaData() {
        data = new ArrayList<TriviaQuestion>();
    }

    public void addQuestion(String q, String a, int v, int t) {
        TriviaQuestion question = new TriviaQuestion(q, a, v, t);
        data.add(question);
    }

    public void showQuestion(int index) {
        TriviaQuestion q = data.get(index);
        System.out.println("Question " + (index + 1) + ".  " + q.value + " points.");
        if (q.type == TriviaQuestion.TRUEFALSE) {
            System.out.println(q.question);
            System.out.println("Enter 'T' for true or 'F' for false.");
        } else if (q.type == TriviaQuestion.FREEFORM) {
            System.out.println(q.question);
        }
    }

    public int numQuestions() {
        return data.size();
    }

    public TriviaQuestion getQuestion(int index) {
        return data.get(index);
    }
}

public class TriviaGame {

    public TriviaData questions;	// Questions

    public TriviaGame() {
        // Load questions
        questions = new TriviaData();
        questions.addQuestion("The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3, TriviaQuestion.FREEFORM);
        questions.addQuestion("Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1, TriviaQuestion.TRUEFALSE);
        questions.addQuestion("1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2, TriviaQuestion.FREEFORM);
        questions.addQuestion("I am the geometric figure most like a lost parrot",
                "polygon", 2, TriviaQuestion.FREEFORM);
        questions.addQuestion("Generics were introducted to Java starting at version 5.0.",
                "T", 1, TriviaQuestion.TRUEFALSE);
    }
    // Main game loop

    public static void main(String[] args) {
        int score = 0;			// Overall score
        int questionNum = 0;	// Which question we're asking
        TriviaGame game = new TriviaGame();
        Scanner keyboard = new Scanner(System.in);
        // Ask a question as long as we haven't asked them all
        while (questionNum < game.questions.numQuestions()) {
            // Show question
            game.questions.showQuestion(questionNum);
            // Get answer
            String answer = keyboard.nextLine();
            // Validate answer
            TriviaQuestion q = game.questions.getQuestion(questionNum);
            if (q.type == TriviaQuestion.TRUEFALSE) {
                if (answer.charAt(0) == q.answer.charAt(0)) {
                    System.out.println("That is correct!  You get " + q.value + " points.");
                    score += q.value;
                } else {
                    System.out.println("Wrong, the correct answer is " + q.answer);
                }
            } else if (q.type == TriviaQuestion.FREEFORM) {
                if (answer.toLowerCase().equals(q.answer.toLowerCase())) {
                    System.out.println("That is correct!  You get " + q.value + " points.");
                    score += q.value;
                } else {
                    System.out.println("Wrong, the correct answer is " + q.answer);
                }
            }
            System.out.println("Your score is " + score);
            questionNum++;
        }
        System.out.println("Game over!  Thanks for playing!");
    }
}

```

### Solution
```java
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


enum QuestionType {
    TRUE_FALSE, FREEFORM
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
```