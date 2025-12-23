package utils;

public class InputOutput {
    private String input;
    private String expectedOutput;
    private String actualOutput;

    public InputOutput() {
        this.input = "Not initialized";
        this.expectedOutput = "/";
        this.actualOutput = "//";
    }

    public InputOutput(String input, String output) {
        this.input = input;
        this.expectedOutput = output;
        this.actualOutput = "Not initialized";
    }

    public String getInput() {
        return input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public String getActualOutput() {
        return actualOutput;
    }

    public void setActualOutput(String actualOutput) {
        this.actualOutput = actualOutput;
    }
}
