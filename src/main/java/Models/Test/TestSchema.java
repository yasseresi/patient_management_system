package Models.Test;

import java.io.Serializable;

public abstract class TestSchema implements Serializable {
    protected String nom ;
    protected float score ;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
