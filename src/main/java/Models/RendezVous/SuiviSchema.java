package Models.RendezVous;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class SuiviSchema extends RendezVousSchema {

    private DeroulementSuivi deroulementSuivi;

    public SuiviSchema(LocalDate date, LocalTime heure, DeroulementSuivi deroulementSuivi) {
        this.deroulementSuivi = deroulementSuivi;
        this.date = date;
        this.heure = heure;
        //TODO: SET THE DURATION ACCORDING TO THE AGE OF THE PATIENT AFTER ADDING DOSSIERS MEDICAUX

    }

    public DeroulementSuivi getDeroulementSuivi() {
        return deroulementSuivi;
    }

    public void setDeroulementSuivi(DeroulementSuivi deroulementSuivi) {
        this.deroulementSuivi = deroulementSuivi;
    }

    @Override
    public String toString() {
        return "SuiviSchema{" +
                "deroulementSuivi=" + deroulementSuivi.toString() +
                ", date=" + date +
                ", heure=" + heure +
                ", duree='" + duree + '\'' +
                ", observation='" + observation + '\'' +
                '}';
    }
}
