package Lab.Test;

import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    public TestCaseReader tc = new TestCaseReader();

    @Test
    void test1() throws IOException {
        tc.test(Main::main, "src/Lab/Test/2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
//        System.out.println(tc.expectedOutput);
//        System.out.println(tc.actualOutput);
    }
}
