package Exam.FirstMidterm.Task1;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ShapesApplication {
    private List<Window> windows;


    public int readCanvases(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        windows = br.lines().map(Window::create).collect(Collectors.toList());
        return windows.stream().mapToInt(w -> w.getSquares().size()).sum();
    }

    public void printLargestCanvasTo(PrintStream out) {
        int max = windows.stream().mapToInt(Window::perimeter).max().orElse(0);
        Window window = windows.stream().filter(w -> w.perimeter() == max).findFirst().orElse(windows.get(windows.size() - 1));

        PrintWriter pw = new PrintWriter(out);
        pw.printf("%s %d %d\n", window.getWindowId(), window.getSquares().size(), max);
        pw.flush();
    }
}
