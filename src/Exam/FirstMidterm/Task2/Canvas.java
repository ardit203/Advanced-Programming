package Exam.FirstMidterm.Task2;

import java.util.List;

public class Canvas implements Comparable<Canvas> {
    String canvasId;
    List<Shape> shapes;

    public Canvas(String canvasId, List<Shape> shapes) {
        this.canvasId = canvasId;
        this.shapes = shapes;
    }

    public String getCanvasId() {
        return canvasId;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public double sumOfAreas(){
        double sum = 0;
        for (Shape shape : shapes){
            sum += shape.area();
        }
        return sum;
//        return shapes.stream()
//                .mapToDouble(Shape::area)
//                .sum();
    }

    public double minArea(){
        Shape min = shapes.get(0);
        for (int i=1 ; i<shapes.size() ; i++){
            if(shapes.get(i).area() < min.area()){
                min = shapes.get(i);
            }
        }
        return min.area();
//        return shapes.stream()
//                .mapToDouble(Shape::area)
//                .min()
//                .orElse(0);
    }

    public double maxArea(){
        Shape max = shapes.get(0);
        for (int i=1 ; i<shapes.size() ; i++){
            if(shapes.get(i).area() > max.area()){
                max = shapes.get(i);
            }
        }
        return max.area();

//        return shapes.stream()
//                .mapToDouble(Shape::area)
//                .max()
//                .orElse(0);
    }

    public double avgArea(){
        return sumOfAreas() / shapes.size();

//        return shapes.stream()
//                .mapToDouble(Shape::area)
//                .average()
//                .orElse(0);
    }

    public int countShapesOfType(ShapeType type){
        return (int) shapes.stream()
                .filter(s -> s.getType() == type)
                .count();
    }

    @Override
    public String toString() {
        int circlesCount = countShapesOfType(ShapeType.CIRCLE);
        int squaresCount = countShapesOfType(ShapeType.SQUARE);
        double min = minArea();
        double max = maxArea();
        double avg = avgArea();

        return String.format("%s %d %d %d %.2f %.2f %.2f", canvasId, shapes.size(), circlesCount, squaresCount, min, max, avg);
    }

    @Override
    public int compareTo(Canvas other) {
        return Double.compare(other.sumOfAreas(), this.sumOfAreas()); //descending
    }
}
