package Exam.FirstMidterm.Task3;

public class FileNameExistsException extends RuntimeException {
    public FileNameExistsException(String file, String folder) {
        super(String.format("There is already a file named %s in the folder %s", file, folder));
    }
}
