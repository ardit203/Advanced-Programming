package Exam.FirstMidterm.Task1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Window {
    private String windowId;
    private List<Integer> squares;

    public Window(String windowId, List<Integer> squares) {
        this.windowId = windowId;
        this.squares = squares;
    }

    public static Window create(String line) {
        String[] tokens = line.split("\\s++");
        String id = tokens[0];

        List<Integer> sq = Arrays.stream(tokens).skip(1).map(Integer::parseInt).collect(Collectors.toList());

        return new Window(id, sq);
    }

    public String getWindowId() {
        return windowId;
    }

    public List<Integer> getSquares() {
        return squares;
    }

    public int perimeter() {
        return squares.stream().mapToInt(s -> 4 * s).sum();
    }
}
