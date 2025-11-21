package Exam.FirstMidterm.Task11.Tester;





import Exam.FirstMidterm.Task11.EvaluatorTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private EvaluatorTest tester;
    String path = "src/Exam/FirstMidterm/Task11/Tester/";

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "1.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test2() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }


}
