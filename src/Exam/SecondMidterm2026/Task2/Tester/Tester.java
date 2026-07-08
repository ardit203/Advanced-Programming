package Exam.SecondMidterm2026.Task2.Tester;
import Exam.SecondMidterm2026.Task2.StatisticsExam;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.ScrapeCourses;
import utils.Scraper;
import utils.TestCaseReader;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private static TestCaseReader TC;
    private StatisticsExam tester;
    private static String FILE_PATH;
    private static Scraper SCRAPER;

    @BeforeAll
    static void test() throws IOException, InterruptedException {
        FILE_PATH = "src/Exam/SecondMidterm2026/Task2/Tester/";
        SCRAPER = new ScrapeCourses("https://courses.finki.ukim.mk/mod/quiz/attempt.php?attempt=786760&cmid=168531&page=1&mdlscrollto=2297.333251953125");
        TC = new TestCaseReader(FILE_PATH, SCRAPER);
    }


    @ParameterizedTest
    @MethodSource("testFileProvider")
    void test(int n) throws IOException {
        TC.test(() -> {
            try {
                tester.main(new String[0]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, n);

        assertEquals(TC.getIo().getExpectedOutput(), TC.getIo().getActualOutput());
    }

    static Stream<Integer> testFileProvider(){
        int n = TC.getNumFiles();
        return IntStream.rangeClosed(1, n).boxed();
    }

}