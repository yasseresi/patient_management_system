package Models.Patient;

import java.time.LocalDate;

public class AdultSchema extends PatientSchema {


    public AdultSchema(String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse,int nbTelephone, boolean nouveau) {
        super(nom, prenom, age, dateNaissance, lieuNaissance, adresse,nbTelephone, nouveau);
    }

    public AdultSchema(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }
}
