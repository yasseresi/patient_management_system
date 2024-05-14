package Exceptions;

public class TestNomUniqueException extends Throwable {
    @Override
    public String getMessage() {
        return "Le nom du test est déjà utilisé.";
    }
}
