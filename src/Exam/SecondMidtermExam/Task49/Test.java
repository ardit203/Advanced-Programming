package Exam.SecondMidtermExam.Task49;

import java.util.List;

public class Test {
    static void main(String[] args) {
        List<String> res = List.of("A", "B", "C");
        int x = 6;


        int index = 0;
        int count = 0;
        while (true){
            if(index == res.size()){
                index = 0;
            }
            if(count == x){
                System.out.println(res.get(index));
                break;
            }
            index++;
            count++;
        }

    }
}
