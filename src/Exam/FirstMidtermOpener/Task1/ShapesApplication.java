package Exam.FirstMidtermOpener.Task1;

import java.io.*;
import java.util.*;

public class ShapesApplication {
    TreeSet<Canvas> canvases;

    public ShapesApplication() {
        this.canvases = new TreeSet<>();
    }

    public int readCanvases(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int count = 0;
        String line;
        while ((line = br.readLine()) != null){
            String [] tokens = line.split("\\s++");
            String id = tokens[0];
            List<Integer> squares = new ArrayList<>();
            for (int i = 1; i < tokens.length; i++) {
                squares.add(Integer.parseInt(tokens[i]));
                count++;
            }
            canvases.add(new Canvas(id, squares));
        }
        return count;
    }



    public void printLargestCanvasTo(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        pw.println(canvases.first());
        pw.flush();
    }



//    Solution using streams, but one thing also the canvases set should be a list for this to work
//    public int readCanvases(InputStream in) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
//
//        canvases = br.lines().filter(Objects::nonNull)
//                .map(CanvasFactory::createCanvas)
//                .collect(Collectors.toList());
//
//        return canvases.stream()
//                .mapToInt(Canvas::squaresCount)
//                .sum();
//    }
//
//    public void printLargestCanvasTo(PrintStream out) {
//        PrintWriter pw = new PrintWriter(out);
//        Canvas maxCanvas = canvases.stream()
//                .max(Comparator.comparingInt(Canvas::sumOfPerimeters))
//                .orElse(canvases.getLast());
//
//        pw.println(maxCanvas);
//        pw.flush();
//    }
}
