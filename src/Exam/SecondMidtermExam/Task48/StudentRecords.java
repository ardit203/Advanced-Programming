package Exam.SecondMidtermExam.Task48;

import java.io.*;
import java.util.*;


public class StudentRecords {
    private Map<String, Set<Student>> students;

    public StudentRecords() {
        this.students = new TreeMap<>();
    }

    public int readRecords(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            Student student = StudentFactory.createStudent(line);
            students.computeIfAbsent(student.getProgram(), k -> new TreeSet<>()).add(student);
            count++;
        }
        return count;
    }

    public void writeTable(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        students.forEach((k, v) -> {
            pw.println(k);
            v.forEach(pw::println);
        });
        pw.flush();
    }

    public void writeDistribution(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        students.entrySet()
                .stream()
                .map(Distribution::new)
                .sorted()
                .forEach(pw::print);
        pw.flush();
    }
}