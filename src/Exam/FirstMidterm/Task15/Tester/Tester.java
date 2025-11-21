package Exam.FirstMidterm.Task15.Tester;

import Exam.FirstMidterm.Task15.WeatherStationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestCaseReader;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tester {
    private TestCaseReader tc;
    private WeatherStationTest tester;
    String path = "src/Exam/FirstMidterm/Task15/Tester/";

    @BeforeEach
    void setUp() {
        tc = new TestCaseReader();
    }

    @Test
    void test1() throws IOException {
        tc.test(() -> {
                    try {
                        tester.main(new String[0]);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                },
                path + "1.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test2() throws IOException {
        tc.test(() -> {
                    try {
                        tester.main(new String[0]);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                },
                path + "2.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }

    @Test
    void test3() throws IOException {
        tc.test(() -> {
                    try {
                        tester.main(new String[0]);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                },
                path + "3.txt");
        assertEquals(tc.expectedOutput, tc.actualOutput);
    }
}
