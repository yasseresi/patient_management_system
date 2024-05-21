package Models.Question;

import java.util.ArrayList;
import java.util.List;

public class QCU extends QuestionEpreuve {
    private List<String> propositions;
    private String reponse;

    public QCU(String question, List<String> propostions) {
        this.propositions = propostions;
        super.question = question;
    }
    public QCU() {
        this.propositions = new ArrayList<>();
        this.reponse = "";
    }

    public String getQuestion() {
        return super.question;
    }

    public void setQuestion(String question) {
        super.question = question;
    }

    public List<String> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<String> propositions) {
        this.propositions = propositions;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
