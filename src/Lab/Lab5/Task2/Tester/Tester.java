package Lab.Lab5.Task2.Tester;
import Lab.Lab5.Task2.Anagrams;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.ScrapeCourses;
import utils.Scraper;
import utils.TestCaseReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private static TestCaseReader TC;
    private Anagrams tester;
    private static String FILE_PATH;
    private static Scraper SCRAPER;

    @BeforeAll
    static void test() throws IOException, InterruptedException {
        FILE_PATH = "src/Lab/Lab5/Task2/Tester/";
        SCRAPER = new ScrapeCourses("https://courses.finki.ukim.mk/mod/quiz/attempt.php?attempt=757589&cmid=166982&page=1&mdlscrollto=6278.88916015625");
        TC = new TestCaseReader(FILE_PATH, SCRAPER);
    }


    @ParameterizedTest
    @MethodSource("testFileProvider")
    void test(int n) throws IOException {
        TC.test(() -> tester.main(new String[0]), n);

        assertEquals(TC.getIo().getExpectedOutput(), TC.getIo().getActualOutput());
    }

    static Stream<Integer> testFileProvider(){
        int n = TC.getNumFiles();
        return IntStream.rangeClosed(1, n).boxed();
    }

}