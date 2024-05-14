package Models.Question;

public class QuestionAdult extends QuestionAnamnese{

    private TypeAdult  typeQuestion;

    public QuestionAdult(String question ,TypeAdult typeQuestion) {
        super.question = question;
        this.typeQuestion = typeQuestion;
    }

    public TypeAdult getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(TypeAdult typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public String getQuestion() {
        return super.question;
    }

    public void setQuestion(String question) {
        super.question = question;
    }

    public void setResponse(String response) {
        super.reponse = response;
    }

    public String getResponse() {
        return super.reponse;
    }
}
