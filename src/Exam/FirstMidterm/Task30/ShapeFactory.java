package Exam.FirstMidterm.Task30;

public class ShapeFactory {
    public static Shape create(String line) {
        String[] tokens = line.split("\\s++");

        String type = tokens[0];
        String id = tokens[1];
        if (idFailed(id)) {
            throw new InvalidIDException(id);
        }

        if (type.equals("1")) {
            double radius = Double.parseDouble(tokens[2]);
            checkLengths(radius);
            return new Circle(id, radius);
        } else if (type.equals("2")) {
            double side = Double.parseDouble(tokens[2]);
            checkLengths(side);
            return new Square(id, side);
        } else {
            double width = Double.parseDouble(tokens[2]);
            double height = Double.parseDouble(tokens[3]);
            checkLengths(width);
            checkLengths(height);
            return new Rectangle(id, width, height);
        }


    }

    public static boolean idFailed(String id) {
        return !id.matches("[a-zA-Z0-9]{6}") || id.length() != 6;
    }

    public static void checkLengths(double length) {
        if (length <= 0) {
            throw new InvalidDimensionException((int) length);
        }
    }
}

