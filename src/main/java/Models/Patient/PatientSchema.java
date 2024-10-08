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
    private int nbTelephones;


    public PatientSchema(String nom, String prenom, int age, LocalDate dateNaissance, String lieuNaissance, String adresse,int nbTelephones, boolean nouveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.adresse = adresse;
        this.nouveau = nouveau;
        this.nbTelephones = nbTelephones;
    }

    public PatientSchema(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
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
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientSchema that)) return false;

        return nom.equals(that.nom) && prenom.equals(that.prenom);
    }

    @Override
    public String toString() {
        return "PatientSchema{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", dateNaissance=" + dateNaissance +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                ", adresse='" + adresse + '\'' +
                ", nouveau=" + nouveau +
                ", nbTelephones=" + nbTelephones +
                '}';
    }
}
