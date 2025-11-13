package Exam.FirstMidterm.Task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShapesApplication {
    private double maxArea;
    private List<Canvas> canvases;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
        this.canvases = new ArrayList<>();
    }

    public void readCanvases(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        canvases = br.lines().map(l -> {
                    try {
                        return ShapeFactory.create(l, maxArea);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void printCanvases(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);

        canvases.stream().sorted(Comparator.comparingDouble(Canvas::sumArea).reversed()).forEach(pw::println);
        pw.flush();
    }
}
