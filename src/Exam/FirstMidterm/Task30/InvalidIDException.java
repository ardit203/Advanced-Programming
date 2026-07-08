package Exam.FirstMidterm.Task30;

class InvalidIDException extends RuntimeException {
    public InvalidIDException(String id) {
        super(String.format("ID %s is not valid", id));
    }
}