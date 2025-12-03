package Lab.Lab3.Task1.Tester;

import Lab.Lab3.Task1.MovieTheaterTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.TestCaseReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private MovieTheaterTester tester;
    private final String filePath = "src/Lab/Lab3/Task1/Tester/";

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    void test(int n) throws IOException {
        tc.test(() -> tester.main(new String[0]), filePath + n + ".txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }
}