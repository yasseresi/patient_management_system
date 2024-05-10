package Models.Orthophoniste;

import java.io.Serializable;
import java.util.Objects;

public class OrthophonisteSchema implements Serializable {
    private  String nom,prenom,adress,adress_mail;
    private  int  nb_phone;
    private String password;

    public OrthophonisteSchema(String nom ,String password){
        this.nom = nom;
        this.password = password;
        this.nb_phone = 0;
        this.prenom = "";
        this.adress = "";
        this.adress_mail = "";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrthophonisteSchema that = (OrthophonisteSchema) o;
        return Objects.equals(nom, that.nom) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, password);
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAdress_mail() {
        return adress_mail;
    }

    public void setAdress_mail(String adress_mail) {
        this.adress_mail = adress_mail;
    }

    public int getNb_phone() {
        return nb_phone;
    }

    public void setNb_phone(int nb_phone) {
        this.nb_phone = nb_phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
