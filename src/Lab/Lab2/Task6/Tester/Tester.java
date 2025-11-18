package Lab.Lab2.Task6.Tester;



import Lab.Lab2.Task6.QueueTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private QueueTest tester;

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/2.txt");
        System.out.println(tc.input);
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test2() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test3() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/3.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test4() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/4.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test5() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/5.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test6() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/6.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test7() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/7.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test8() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/8.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test9() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/9.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test10() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task6/Tester/10.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

}
