package utils;

public class StringNormalizer {
    public static String normalizeLines(String s) {
        if (s == null) return "";

        String result = s
                .replace("\r\n", "\n")
                .replace("\r", "\n");

        // remove literal "\t"
        result = result.replaceAll("\\\\t", "\t");

        // remove trailing spaces / real tabs at end of each line
        result = result.replaceAll("(?m)[ \\t]+$", "");
        // remove trailing newlines at the END of the string
        result = result.replaceAll("\n+$", "");

        return result;
    }

    public static String transformToFileFormat(String input, String output){
        return  "INPUT:\n" + input + "\n\nOUTPUT:\n" + output;
    }
}
