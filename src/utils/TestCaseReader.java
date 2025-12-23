package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import static utils.StringNormalizer.normalizeLines;


public class TestCaseReader {
    Scraper scraper;
    FileManager fileManager;
    InputOutput io;

    public TestCaseReader(String path, Scraper scraper) throws IOException, InterruptedException {
        this.scraper = scraper;
        this.fileManager = new FileManager(path);
        this.io = new InputOutput();
        init();
    }

    public void init() throws IOException, InterruptedException {
        if(!fileManager.exists()){
            fileManager.createFiles(scraper.scrape());
        }
    }

    public void test(Runnable mainMethod, int i) throws IOException {
        io = fileManager.readFile(i);

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            ByteArrayInputStream testIn =
                    new ByteArrayInputStream(io.getInput().getBytes(StandardCharsets.UTF_8));
            System.setIn(testIn);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream testOut = new PrintStream(baos, true, StandardCharsets.UTF_8);
            System.setOut(testOut);

            mainMethod.run();

            testOut.flush();
            io.setActualOutput(normalizeLines(baos.toString(StandardCharsets.UTF_8)));


        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    public InputOutput getIo(){
        return io;
    }

    public int getNumFiles(){
        return fileManager.getNumFiles();
    }

}
