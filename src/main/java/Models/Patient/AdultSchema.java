package Models.Patient;

import java.time.LocalDate;

public class AdultSchema extends PatientSchema {


    public AdultSchema(String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse, boolean nouveau) {
        super(nom, prenom, age, dateNaissance, lieuNaissance, adresse, nouveau);
    }

    public AdultSchema(String nom, String prenom, int age) {
        super(nom, prenom, age);
    }
}
