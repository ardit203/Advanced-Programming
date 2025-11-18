package Exam.FirstMidterm.Task4.Tester;



import Exam.FirstMidterm.Task4.LoggerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Again here you might see some mistakes on the Zone_Id, so the testcases might not be suitable everywhere
public class Tester {
    private TestCaseReader tc;
    private LoggerTest tester;

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task4/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test2() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task4/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }
}
