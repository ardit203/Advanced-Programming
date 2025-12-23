package Exam.FirstMidtermOpener.Task1;

import java.util.ArrayList;
import java.util.List;

public class Canvas implements Comparable<Canvas> {
    private String canvasId;
    private List<Integer> squares;

    public Canvas(String canvasId) {
        this.canvasId = canvasId;
        this.squares = new ArrayList<>();
    }

    public Canvas(String canvasId, List<Integer> squares) {
        this.canvasId = canvasId;
        this.squares = squares;
    }

    public String getCanvasId() {
        return canvasId;
    }

    public List<Integer> getSquares() {
        return squares;
    }



    public int sumOfPerimeters() {
        int sum = 0;
        for (int square : squares) {
            sum += square;
        }
        return 4 * sum;
    }

//    Solution with streams
//    public int sumOfPerimeters() {
//        return squares.stream()
//                .mapToInt(s -> 4 * s)
//                .sum();
//    }

    public int squaresCount() {
        return squares.size();
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", canvasId, squaresCount(), sumOfPerimeters());
    }

    @Override
    public int compareTo(Canvas other) {
        return Integer.compare(other.sumOfPerimeters(), this.sumOfPerimeters());
    }
}
