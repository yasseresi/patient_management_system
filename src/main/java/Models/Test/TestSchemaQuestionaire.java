package Models.Test;

import Models.Question.QuestionEpreuve;

import java.util.List;

public class TestSchemaQuestionaire extends TestSchema {


    private List<QuestionEpreuve> questions;

    public TestSchemaQuestionaire(String nom, List<QuestionEpreuve> questions) {
        this.nom = nom;
        this.questions = questions;
        this.score = 0;
    }

    public List<QuestionEpreuve> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEpreuve> questions) {
        this.questions = questions;
    }


}
