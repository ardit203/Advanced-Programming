package Lab.Lab9.Task2.Tester;
import Lab.Lab9.Task2.XMLTest;
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
    private XMLTest tester;
    private static String FILE_PATH;
    private static Scraper SCRAPER;

    @BeforeAll
    static void test() throws IOException, InterruptedException {
        FILE_PATH = "src/Lab/Lab9/Task2/Tester/";
        //https://github.com/stefanandonov/NP_code_export/tree/master/XML
        SCRAPER = new ScrapeGithub("https://raw.githubusercontent.com/stefanandonov/NP_code_export/master/XML/test_cases");
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