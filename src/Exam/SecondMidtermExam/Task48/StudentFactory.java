package Exam.SecondMidtermExam.Task48;

import java.util.ArrayList;
import java.util.List;

class StudentFactory {
    public static Student createStudent(String line) {
        String[] tokens = line.split("\\s++");
        String code = tokens[0];
        String program = tokens[1];

        List<Integer> grades = new ArrayList<>();
        for (int i = 2; i < tokens.length; i++) {
            grades.add(Integer.parseInt(tokens[i]));
        }
        return new Student(code, program, grades);
    }
}