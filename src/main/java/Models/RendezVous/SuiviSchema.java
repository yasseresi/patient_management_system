package Models.RendezVous;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class SuiviSchema extends RendezVousSchema {

    private DeroulementSuivi deroulementSuivi;
    private int nbDossierMedical;
    public static Duration dureeSeanceEnfant = Duration.ofHours(2).plusMinutes(30);
    public static Duration dureeSeanceAdulte = Duration.ofHours(1).plusMinutes(30);

    public SuiviSchema(LocalDate date, LocalTime heure, DeroulementSuivi deroulementSuivi, int nbDossierMedical) {
        this.deroulementSuivi = deroulementSuivi;
        this.nbDossierMedical = nbDossierMedical;
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

    public int getNbDossierMedical() {
        return nbDossierMedical;
    }

    public void setNbDossierMedical(int nbDossierMedical) {
        this.nbDossierMedical = nbDossierMedical;
    }
}
