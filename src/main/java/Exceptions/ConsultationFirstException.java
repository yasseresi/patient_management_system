package Exceptions;

public class ConsultationFirstException extends Exception{

    @Override
    public String getMessage() {
        return "Consultation must be the first rendez-vous before Suivi";
    }
}
