package Exam.FirstMidterm.Task25.Tester;




import Exam.FirstMidterm.Task25.DiscountsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private DiscountsTest tester;

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task25/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test2() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task25/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test3() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task25/Tester/3.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test4() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task25/Tester/4.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test5() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task25/Tester/5.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test6() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Exam/FirstMidterm/Task25/Tester/6.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }
}
