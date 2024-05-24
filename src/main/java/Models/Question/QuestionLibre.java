package Models.Question;

import java.io.Serializable;

public class QuestionLibre extends QuestionEpreuve implements Serializable {


    private String reponse;

    public QuestionLibre(String question, String reponse) {
        this.reponse = reponse;
        super.question = question;
    }
    public QuestionLibre() {
    }

    public String getQuestion() {
        return super.question;
    }

    public void setQuestion(String question) {
        super.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
