package Exam.FirstMidtermOpener.Task1;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CanvasFactory {
    public static Canvas createCanvas(String line){
        String [] tokens = line.split("\\s++");
        String id =  tokens[0];

        List<Integer> squares = Arrays.stream(tokens)
                .skip(1)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new Canvas(id, squares);
    }
}
