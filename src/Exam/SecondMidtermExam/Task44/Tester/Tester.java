package Exam.SecondMidtermExam.Task44.Tester;
import Exam.SecondMidtermExam.Task44.EventCalendarTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.ScrapeGithub;
import utils.Scraper;
import utils.TestCaseReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private static TestCaseReader TC;
    private EventCalendarTest tester;
    private static String FILE_PATH;
    private static Scraper SCRAPER;

    @BeforeAll
    static void test() throws IOException, InterruptedException {
        FILE_PATH = "src/Exam/SecondMidtermExam/Task44/Tester/";
        //https://github.com/stefanandonov/NP_code_export/tree/master/%D0%9A%D0%B0%D0%BB%D0%B5%D0%BD%D0%B4%D0%B0%D1%80%20%D0%BD%D0%B0%20%D0%BD%D0%B0%D1%81%D1%82%D0%B0%D0%BD%D0%B8%20(35%20%D0%BF%D0%BE%D0%B5%D0%BD%D0%B8)
        SCRAPER = new ScrapeGithub("https://raw.githubusercontent.com/stefanandonov/NP_code_export/master/%D0%9A%D0%B0%D0%BB%D0%B5%D0%BD%D0%B4%D0%B0%D1%80%20%D0%BD%D0%B0%20%D0%BD%D0%B0%D1%81%D1%82%D0%B0%D0%BD%D0%B8%20(35%20%D0%BF%D0%BE%D0%B5%D0%BD%D0%B8)/test_cases");
        TC = new TestCaseReader(FILE_PATH, SCRAPER);
    }


    @ParameterizedTest
    @MethodSource("testFileProvider")
    void test(int n) throws IOException {
        TC.test(() -> {
            try {
                tester.main(new String[0]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }, n);

        assertEquals(TC.getIo().getExpectedOutput(), TC.getIo().getActualOutput());
    }

    static Stream<Integer> testFileProvider() {
        int n = TC.getNumFiles();
        return IntStream.rangeClosed(1, n).boxed();
    }

}