package Models.Test;

import java.io.Serializable;
import java.util.TreeSet;

public class Exercice implements Serializable {

    private String nom;
    private TreeSet<Float> progress ;

    public Exercice(String nom) {
        this.nom = nom;
        this.progress = new TreeSet<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TreeSet<Float> getProgress() {
        return progress;
    }

    public void setProgress(TreeSet<Float> progress) {
        this.progress = progress;
    }



}
