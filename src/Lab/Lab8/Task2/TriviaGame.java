package Lab.Lab8.Task2;

import java.util.Scanner;

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
