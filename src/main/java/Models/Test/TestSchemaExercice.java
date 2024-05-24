package Models.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestSchemaExercice extends TestSchema implements Serializable {

    private List<Exercice> exercices;

    public TestSchemaExercice(String nom, List<Exercice> exercices) {
        this.nom = nom;
        this.exercices = exercices;
        this.score = 0;
    }
    public TestSchemaExercice(String nom){
        this.nom = nom;
        exercices = new ArrayList<>();
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }
}
