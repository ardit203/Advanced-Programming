package Lab.Lab2.Task1.Tester;

import Lab.Lab2.Task1.LocalDateTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private LocalDateTest tester;

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> tester.main(new String[0]),
                "src/Lab/Lab2/Task1/Tester/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

}
