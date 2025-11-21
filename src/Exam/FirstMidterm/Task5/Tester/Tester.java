package Exam.FirstMidterm.Task5.Tester;




import Exam.FirstMidterm.Task5.MinAndMax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tester {
    private TestCaseReader tc;
    private MinAndMax tester;

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> {
                    try {
                        tester.main(new String[0]);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                },
                "src/Exam/FirstMidterm/Task5/Tester/1.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test2() throws IOException {
        tc.test(() -> {
                    try {
                        tester.main(new String[0]);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                },
                "src/Exam/FirstMidterm/Task5/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }


    @Test
    void test3() throws IOException {
        tc.test(() -> {
                    try {
                        tester.main(new String[0]);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                },
                "src/Exam/FirstMidterm/Task5/Tester/3.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }
}
