package Exam.FirstMidterm.Task45.Tester;




import Exam.FirstMidterm.Task45.LabExercisesTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private LabExercisesTest tester;

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task45/Tester/1.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test2() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task45/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }
}
