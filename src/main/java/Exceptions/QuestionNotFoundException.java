package Exceptions;

public class QuestionNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Question not found or doesn't exist";
    }
}
