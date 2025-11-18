package Lab.Lab3.Task1.Tester;

import Lab.Lab1.Task1.BankTester;
import Lab.Lab3.Task1.MovieTheaterTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private MovieTheaterTester tester;

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab3/Task1/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }


    @Test
    void test2() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab3/Task1/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }


    @Test
    void test3() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab3/Task1/Tester/3.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test4() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab3/Task1/Tester/4.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }



}
