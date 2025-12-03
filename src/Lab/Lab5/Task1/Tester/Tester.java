package Lab.Lab5.Task1.Tester;

import Lab.Lab5.Task1.ChatSystemTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.TestCaseReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private TestCaseReader tc;
    private ChatSystemTest tester;
    private final String filePath = "src/Lab/Lab5/Task1/Tester/";

    @BeforeEach
    void setUp(){
        tc = new TestCaseReader();
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
    void test(int n) throws IOException {
        tc.test(() -> {
            try {
                tester.main(new String[0]);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }, filePath + n + ".txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }
}