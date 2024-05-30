package Exceptions;

public class QuestionNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Question n'existe pas";
    }
}
