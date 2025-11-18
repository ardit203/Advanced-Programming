package Lab.Lab3.Task2.Tester;

import Lab.Lab3.Task2.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private Main tester;

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> {
                    try {
                        tester.main(new String[0]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                "src/Lab/Lab3/Task2/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }


    @Test
    void test2() throws IOException {
        tc.test(() -> {
                    try {
                        tester.main(new String[0]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                "src/Lab/Lab3/Task2/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }


    @Test
    void test3() throws IOException {
        tc.test(() -> {
                    try {
                        tester.main(new String[0]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                "src/Lab/Lab3/Task2/Tester/3.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }





}
