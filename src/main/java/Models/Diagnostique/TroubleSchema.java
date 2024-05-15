package Models.Diagnostique;

public class TroubleSchema {
    private String nom;
    private TypeTrouble categorie;

    public TroubleSchema(String nom, TypeTrouble categorie) {
        this.nom = nom;
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeTrouble getCategorie() {
        return categorie;
    }

    public void setCategorie(TypeTrouble categorie) {
        this.categorie = categorie;
    }
}
