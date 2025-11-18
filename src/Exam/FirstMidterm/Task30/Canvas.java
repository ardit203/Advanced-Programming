package Exam.FirstMidterm.Task30;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Canvas {
    private Set<Shape> shapes;

    public Canvas() {
        this.shapes = new TreeSet<>(Comparator.comparingDouble(Shape::area));
    }

    public void readShapes(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            br.lines().forEach(l -> {
                try {
                    shapes.add(ShapeFactory.create(l));
                } catch (InvalidIDException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (InvalidDimensionException e) {
            System.out.println(e.getMessage());
        }

    }

    public void scaleShapes(String id, double coef) {
        shapes.stream()
                .filter(s -> s.getId().equals(id))
                .forEach(s -> s.scale(coef));
    }


    public void printAllShapes(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        shapes.forEach(pw::println);
        pw.flush();
    }

    public void printByUserId(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        Map<String, Set<Shape>> shapesMap = shapes.stream()
                .collect(Collectors.groupingBy(
                        Shape::getId,
                        Collectors.toCollection(TreeSet::new)
                ));

        shapesMap.entrySet().stream()
                .sorted(Comparator.comparingDouble((Map.Entry<String, Set<Shape>> e) -> e.getValue().size()).reversed()
                        .thenComparing(e -> e.getValue().stream().mapToDouble(Shape::area).sum()))
                .forEach(
                        e -> {
                            pw.printf("Shapes of user: %s\n", e.getKey());
                            e.getValue().forEach(pw::println);
                        }
                );
        pw.flush();

    }


    public void statistics(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        int count = shapes.size();
        double sum = shapes.stream().mapToDouble(Shape::area).sum();
        double min = shapes.stream().mapToDouble(Shape::area).min().orElse(0);
        double max = shapes.stream().mapToDouble(Shape::area).max().orElse(0);
        double avg = shapes.stream().mapToDouble(Shape::area).average().orElse(0);

        pw.printf("count: %d\n", count);
        pw.printf("sum: %.2f\n", sum);
        pw.printf("min: %.2f\n", min);
        pw.printf("average: %.2f\n", avg);
        pw.printf("max: %.2f\n", max);

        pw.flush();
    }


}
