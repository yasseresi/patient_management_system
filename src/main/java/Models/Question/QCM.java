package Models.Question;

import java.util.ArrayList;
import java.util.List;

public class QCM extends QuestionEpreuve{

    private List<String>  propositions;
    private List<String>  reponses;
    public QCM(String question , List<String> propositions){
        this.propositions = propositions;
        super.question = question;
    }
    public QCM(){
        this.propositions = new ArrayList<>();
        this.reponses = new ArrayList<>();
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

    public List<String> getReponses() {
        return reponses;
    }

    public void setReponses(List<String> reponses) {
        this.reponses = reponses;
    }
}
