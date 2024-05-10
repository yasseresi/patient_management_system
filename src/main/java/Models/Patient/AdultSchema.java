package Models.Patient;

public class AdultSchema extends PatientSchema {


    public AdultSchema(String nom, String prenom, int age, String dateNaissance, String lieuNaissance, String adresse, boolean nouveau) {
        super(nom, prenom, age, dateNaissance, lieuNaissance, adresse, nouveau);
    }
}
