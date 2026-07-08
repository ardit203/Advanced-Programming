package Exam.FirstMidterm.Task11.Tester;

import Exam.FirstMidterm.Task11.EvaluatorTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.ScrapeGithub;
import utils.Scraper;
import utils.TestCaseReader;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    private static TestCaseReader TC;
    private EvaluatorTest tester;
    private static String FILE_PATH;
    private static Scraper SCRAPER;

    @BeforeAll
    static void test() throws IOException, InterruptedException {
        FILE_PATH = "src/Exam/FirstMidterm/Task11/Tester/";
        //https://github.com/stefanandonov/NP_code_export/tree/master/%D0%93%D0%B5%D0%BD%D0%B5%D1%80%D0%B8%D1%87%D0%BA%D0%B8%20%D0%B5%D0%B2%D0%B0%D0%BB%D1%83%D0%B0%D1%82%D0%BE%D1%80%20(30)
        SCRAPER = new ScrapeGithub("https://raw.githubusercontent.com/stefanandonov/NP_code_export/master/%D0%93%D0%B5%D0%BD%D0%B5%D1%80%D0%B8%D1%87%D0%BA%D0%B8%20%D0%B5%D0%B2%D0%B0%D0%BB%D1%83%D0%B0%D1%82%D0%BE%D1%80%20(30)/test_cases");
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