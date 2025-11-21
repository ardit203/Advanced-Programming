package Exam.SecondMidterm.Task17.Tester;
import Exam.SecondMidterm.Task17.MojDDVTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private MojDDVTest tester;
    private String path = "src/Exam/SecondMidterm/Task17/Tester/";

    @BeforeEach
    void setUp() {
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

    @Test
    void test3() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "3.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }
}
