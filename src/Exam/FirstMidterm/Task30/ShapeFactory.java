package Exam.FirstMidterm.Task30;

public class ShapeFactory {
    public static Shape createShape(String line){
        String [] tokens = line.split("\\s++");

        String type = tokens[0];
        String id = tokens[1];

        if(!checkId(id)){
            throw new InvalidIDException(id);
        }

        double base = Double.parseDouble(tokens[2]);


        if(base == 0){
            throw new InvalidDimensionException();
        }
        if(type.equals("1")){
            return new Circle(id, base);
        } else if (type.equals("2")) {
            return new Square(id, base);
        }else {
            double height = Double.parseDouble(tokens[3]);
            if(height == 0){
                throw new InvalidDimensionException();
            }
            return new Rectangle(id, base, height);
        }
    }

    private static boolean checkId(String id){
        if (id.length() != 6){
            return false;
        }

        for (int i = 0; i < id.length(); i++) {
            if(!Character.isLetterOrDigit(id.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
