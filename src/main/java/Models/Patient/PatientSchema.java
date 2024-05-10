package Models.Patient;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class PatientSchema implements Serializable, Comparable<PatientSchema>{
    private String nom;
    private String prenom;
    private int age;
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private String adresse;
    private  boolean nouveau;


    public PatientSchema(String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse, boolean nouveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.adresse = adresse;
        this.nouveau = nouveau;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean isNouveau() {
        return nouveau;
    }

    public void setNouveau(boolean nouveau) {
        this.nouveau = nouveau;
    }

    @Override
    public int compareTo(PatientSchema o) {
        return Integer.compare(this.age,o.getAge());
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.nom,this.prenom,this.age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientSchema that = (PatientSchema) o;
        return age == that.age && nouveau == that.nouveau && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(dateNaissance, that.dateNaissance) && Objects.equals(lieuNaissance, that.lieuNaissance) && Objects.equals(adresse, that.adresse);
    }


}
