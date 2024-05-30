package Exceptions;

public class ConsultationFirstException extends Exception{

    @Override
    public String getMessage() {
        return "La consultation doit être le premier rendez-vous avant le suivi.";
    }
}
