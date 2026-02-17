package Lab.Lab4.Task1.Tester;

import Lab.Lab4.Task1.SchedulerTest;
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
    private SchedulerTest tester;
    private static String FILE_PATH;
    private static Scraper SCRAPER;

    @BeforeAll
    static void test() throws IOException, InterruptedException {
        FILE_PATH = "src/Lab/Lab4/Task1/Tester/";
        //https://github.com/stefanandonov/NP_code_export/tree/master/%D0%A0%D0%B0%D1%81%D0%BF%D0%BE%D1%80%D0%B5%D0%B4%D1%83%D0%B2%D0%B0%D1%87%20%D1%81%D0%BE%20Map
        SCRAPER = new ScrapeGithub("https://raw.githubusercontent.com/stefanandonov/NP_code_export/master/%D0%A0%D0%B0%D1%81%D0%BF%D0%BE%D1%80%D0%B5%D0%B4%D1%83%D0%B2%D0%B0%D1%87%20%D1%81%D0%BE%20Map/test_cases");
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