package Exceptions;

public class QuestionAlreadyExistException extends Exception {
    @Override
    public String getMessage() {
        return "Question déjà existante";
    }
}
