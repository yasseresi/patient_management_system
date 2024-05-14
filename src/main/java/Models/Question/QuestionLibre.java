package Models.Question;

public class QuestionLibre extends QuestionEpreuve {


    private String reponse;

    public QuestionLibre(String question, String reponse) {
        this.reponse = reponse;
        super.question = question;
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
