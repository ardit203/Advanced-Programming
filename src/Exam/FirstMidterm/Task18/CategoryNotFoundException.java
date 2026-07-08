package Exam.FirstMidterm.Task18;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String category) {
        super(String.format("Category %s was not found", category));

    }
}
