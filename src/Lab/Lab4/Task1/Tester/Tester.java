//package Lab.Lab4.Task1.Tester;
//
//import Lab.Lab4.Task1.SchedulerTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import utils.TestCaseReader;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
////U can get some errors in the timezone, instead of GMT get something eles
////and u can get +- one two hours but thats fine
//public class Tester {
//    private TestCaseReader tc;
//    private SchedulerTest tester;
//    private final String filePath = "src/Lab/Lab4/Task1/Tester/";
//
//    @BeforeEach
//    void setUp(){
//        tc = new TestCaseReader();
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {1,2,3})
//    void test(int n) throws IOException {
//        tc.test(() -> tester.main(new String[0]), filePath + n + ".txt");
//        assertEquals(tc.expectedOutput, tc.actualOutput);
//    }
//}