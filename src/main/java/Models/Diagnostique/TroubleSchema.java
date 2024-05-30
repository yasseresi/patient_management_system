package Models.Diagnostique;

public class TroubleSchema {
    private String nom;
    private TypeTrouble categorie;

    public TroubleSchema(String nom, TypeTrouble categorie) {
        this.nom = nom;
        this.categorie = categorie;
    }

    public TroubleSchema() {
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

    public void setCategorie(String selectedType) {
        // Handle potential case-insensitivity
        selectedType = selectedType.toUpperCase();

        // Map string to corresponding TypeTrouble enum value
        TypeTrouble categorie = null;
        for (TypeTrouble type : TypeTrouble.values()) {
            if (type.name().equals(selectedType)) {
                categorie = type;
                break;
            }
        }

        if (categorie != null) {
            this.categorie = categorie;
        } else {
            // Handle invalid selection (optional: raise an exception or provide a warning)
            System.out.println("Invalid type selection: " + selectedType);
        }
    }
}
