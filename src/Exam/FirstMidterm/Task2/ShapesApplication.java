package Exam.FirstMidterm.Task2;

import java.io.*;
import java.util.*;

public class ShapesApplication {

    private double maxArea;
    private List<Canvas> canvases;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
        this.canvases = new ArrayList<>();
    }

    public void readCanvases(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = br.readLine()) != null) {
            try {
                canvases.add(ShapeFactory.createShape(line, maxArea));
            } catch (InvalidCanvasException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printCanvases(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);
        Collections.sort(canvases); //sort using the Comparable method

        for (Canvas canvas : canvases) {
            pw.println(canvas);
        }
//        canvases.stream()
//                //if u implement the comparable interface for the class Canvas u don't need the comparator here!
//                .sorted(Comparator.comparingDouble(Canvas::sumOfAreas).reversed())
//                .forEach(pw::println);


        pw.flush();


    }
}
