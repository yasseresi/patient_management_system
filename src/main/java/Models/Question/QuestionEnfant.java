package Models.Question;

import java.io.Serializable;

public class QuestionEnfant extends QuestionAnamnese implements Serializable {
    private TypeEnfant typeQuestion;

    public QuestionEnfant(String question, TypeEnfant typeQuestion) {
        super.question = question;
        this.typeQuestion = typeQuestion;
    }


    public TypeEnfant getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(TypeEnfant typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public String getQuestion() {
        return super.question;
    }

    public void setQuestion(String question) {
        super.question = question;
    }


    public String getReponse() {
        return super.reponse;
    }

    public void setReponse(String reponse) {
        super.reponse = reponse;
    }
}
