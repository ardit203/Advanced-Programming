package Exam.FirstMidterm.Task13.Tester;
import Exam.FirstMidterm.Task13.ApplicantEvaluationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;



public class Tester {
    private TestCaseReader tc;
    private ApplicantEvaluationTest tester;
    String path = "src/Exam/FirstMidterm/Task13/Tester/";

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

    @Test
    void test3() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "3.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test4() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "4.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test5() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "5.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test6() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "6.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test7() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "7.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test8() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "8.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test9() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "9.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test10() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "10.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test11() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "11.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test12() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "12.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test13() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "13.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }
    @Test
    void test14() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "14.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test15() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "15.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test16() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "16.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test17() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                path + "17.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

}
