package Exceptions;

public class ConsultationAlreadyCreatedExecption extends Exception{

    @Override
    public String getMessage() {
        return "Consultation already created you should choose a Suivi rendez-vous";
    }
}
