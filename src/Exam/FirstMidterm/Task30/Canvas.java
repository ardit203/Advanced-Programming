package Exam.FirstMidterm.Task30;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Canvas {
    private Set<Shape> shapes;

    public Canvas() {
        this.shapes = new TreeSet<>();
    }

    public void readShapes(InputStream is) {
        Scanner scanner = new Scanner(is);

        while (scanner.hasNextLine()) {
            try {
                shapes.add(ShapeFactory.createShape(scanner.nextLine()));
            } catch (InvalidIDException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDimensionException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }


    public void scaleShapes(String id, double coefficient) {
        shapes.stream()
                .filter(s -> s.getId().equals(id))
                .forEach(shape -> shape.scale(coefficient));
    }

    public void printAllShapes(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);

        shapes.forEach(pw::println);
        pw.flush();
    }

    public void printByUserId(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);

        Comparator<String> comparator = Comparator.comparing(
                        (String k) -> shapes.stream()
                                .filter(shape -> shape.getId().equals(k))
                                .count()
                ).reversed()
                .thenComparing(
                        k -> shapes.stream()
                                .filter(shape -> shape.getId().equals(k))
                                .mapToDouble(Shape::area)
                                .sum()
                );

        Map<String, Set<Shape>> grouped = shapes.stream()
                .collect(Collectors.groupingBy(
                        Shape::getId,
                        () -> new TreeMap<>(comparator),
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Shape::perimeter)))
                ));

        grouped.forEach((k, v) -> {
            pw.println("Shapes of user: " + k);
            v.forEach(pw::println);
        });
        pw.flush();
    }

    public void statistics(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);

        DoubleSummaryStatistics dss = shapes.stream()
                .mapToDouble(Shape::area)
                .summaryStatistics();

        pw.printf("count: %d\n", dss.getCount());
        pw.printf("sum: %.2f\n", dss.getSum());
        pw.printf("min: %.2f\n", dss.getMin());
        pw.printf("average: %.2f\n", dss.getAverage());
        pw.printf("max: %.2f\n", dss.getMax());
        pw.flush();
    }
}
