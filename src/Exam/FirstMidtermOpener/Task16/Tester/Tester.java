package Exam.FirstMidtermOpener.Task16.Tester;

import Exam.FirstMidtermOpener.Task16.MojDDVTest;
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
    private MojDDVTest tester;
    private static String FILE_PATH;
    private static Scraper SCRAPER;

    @BeforeAll
    static void test() throws IOException, InterruptedException {
        FILE_PATH = "src/Exam/FirstMidtermOpener/Task16/Tester/";
        //https://github.com/stefanandonov/NP_code_export/tree/master/%D0%9C%D0%BE%D1%98%20%D0%94%D0%94%D0%92%201
        SCRAPER = new ScrapeGithub("https://raw.githubusercontent.com/stefanandonov/NP_code_export/master/%D0%9C%D0%BE%D1%98%20%D0%94%D0%94%D0%92%201/test_cases");
        TC = new TestCaseReader(FILE_PATH, SCRAPER);
    }


    @ParameterizedTest
    @MethodSource("testFileProvider")
    void test(int n) throws IOException {
        TC.test(() -> tester.main(new String[0]), n);

        assertEquals(TC.getIo().getExpectedOutput(), TC.getIo().getActualOutput());
    }

    static Stream<Integer> testFileProvider() {
        int n = TC.getNumFiles();
        return IntStream.rangeClosed(1, n).boxed();
    }

}