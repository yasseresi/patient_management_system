package Models.Patient;

import java.time.LocalDate;
import java.util.Locale;

public class EnfantSchema extends PatientSchema{


    public EnfantSchema(String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse, boolean nouveau) {
        super(nom, prenom, age, dateNaissance, lieuNaissance, adresse, nouveau);
    }




}
