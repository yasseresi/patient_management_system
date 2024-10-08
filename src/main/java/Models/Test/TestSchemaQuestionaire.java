package Models.Test;

import Models.Question.QuestionEpreuve;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestSchemaQuestionaire extends TestSchema implements Serializable {


    private List<QuestionEpreuve> questions;

    public TestSchemaQuestionaire(String nom, List<QuestionEpreuve> questions) {
        this.nom = nom;
        this.questions = questions;
        this.score = 0;
    }
    public TestSchemaQuestionaire (String nom){
        this.nom  =nom;
        this.questions = new ArrayList<>();
        this.score = 0;
    }

    public List<QuestionEpreuve> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEpreuve> questions) {
        this.questions = questions;
    }


}
