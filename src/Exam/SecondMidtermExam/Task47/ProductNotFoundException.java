package Exam.SecondMidtermExam.Task47;

public class ProductNotFoundException extends Exception {
    ProductNotFoundException(String id) {
        super(String.format("Product with id %s does not exist in the online shop!", id));
    }
}