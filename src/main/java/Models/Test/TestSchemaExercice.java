package Models.Test;

import java.util.List;

public class TestSchemaExercice extends TestSchema {

    private List<Exercice> exercices;

    public TestSchemaExercice(String nom, List<Exercice> exercices) {
        this.nom = nom;
        this.exercices = exercices;
        this.score = 0;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }
}
