package Models.Question;

import java.io.Serializable;

public abstract class QuestionAnamnese extends Question implements Serializable {

    protected String reponse;

    public String getQuestion(){
        return super.question;
    }


}
