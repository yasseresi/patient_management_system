package Models.Question;

import java.io.Serializable;

public abstract class Question implements Serializable {

    protected String question;


    public Question(String question) {
        this.question = question;
    }
    protected Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
