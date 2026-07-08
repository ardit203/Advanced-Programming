package Exam.SecondMidtermExam.Task47.Tester;
import Exam.SecondMidtermExam.Task47.OnlineShopTest;
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
    private OnlineShopTest tester;
    private static String FILE_PATH;
    private static Scraper SCRAPER;

    @BeforeAll
    static void test() throws IOException, InterruptedException {
        FILE_PATH = "src/Exam/SecondMidtermExam/Task47/Tester/";
        //https://github.com/stefanandonov/NP_code_export/tree/master/%D0%9E%D0%BD%D0%BB%D0%B0%D1%98%D0%BD%20%D0%BF%D1%80%D0%BE%D0%B4%D0%B0%D0%B2%D0%BD%D0%B8%D1%86%D0%B0
        SCRAPER = new ScrapeGithub("https://raw.githubusercontent.com/stefanandonov/NP_code_export/master/%D0%9E%D0%BD%D0%BB%D0%B0%D1%98%D0%BD%20%D0%BF%D1%80%D0%BE%D0%B4%D0%B0%D0%B2%D0%BD%D0%B8%D1%86%D0%B0/test_cases");
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