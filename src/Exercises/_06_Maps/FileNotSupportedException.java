package Exercises._06_Maps;

import java.util.List;

public class FileNotSupportedException extends Exception{
    public FileNotSupportedException(List<String> notSupportedFiles) {
        StringBuilder sb = new StringBuilder();
        sb.append("Files with extension: ");
        notSupportedFiles.forEach(sb::append);
        sb.append(" are not supported.");
        super(sb.toString());
    }
}
