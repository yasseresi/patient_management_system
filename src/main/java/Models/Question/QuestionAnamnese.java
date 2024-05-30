package Models.Question;

import java.io.Serializable;

public abstract class QuestionAnamnese extends Question implements Serializable {

    protected String reponse;

    public String getQuestion(){
        return super.question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

}
