//package Lab.Lab2.Task5.Tester;
//
//
//
//import Lab.Lab2.Task5.SchedulerTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import utils.TestCaseReader;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
////u will get some error in the time, but its good
//public class Tester {
//    private TestCaseReader tc;
//    private SchedulerTest tester;
//    private final String filePath = "src/Lab/Lab2/Task5/Tester/";
//
//    @BeforeEach
//    void setUp(){
//        tc = new TestCaseReader();
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {1,2,3,4,5,6,7,8,9})
//    void test(int n) throws IOException {
//        tc.test(() -> tester.main(new String[0]), filePath + n + ".txt");
//        assertEquals(tc.expectedOutput, tc.actualOutput);
//    }
//}