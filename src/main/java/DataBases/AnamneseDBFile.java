package DataBases;

import Exceptions.QuestionAlreadyExistException;
import Exceptions.QuestionNotFoundException;
import Models.Question.QuestionAdult;
import Models.Question.QuestionAnamnese;
import Models.Question.QuestionEnfant;

import java.io.Serializable;
import java.util.ArrayList;

public class AnamneseDBFile implements AnamneseDB, Serializable {

    ArrayList<QuestionAnamnese> questions ;


    public AnamneseDBFile(){
        questions = new ArrayList<QuestionAnamnese>();
    }

    public AnamneseDBFile(ArrayList<QuestionAnamnese> questions){
        this.questions = questions;
    }


    public void  createQuestion(QuestionAnamnese question)throws QuestionAlreadyExistException {
        //this condition means that the question already exist
        if(!questions.add(question)) {
            throw new QuestionAlreadyExistException();
        }
    }
    public void  deleteQuestion(QuestionAnamnese question)throws QuestionNotFoundException {
        if(!questions.remove(question)) throw new QuestionNotFoundException();
    }

    public void updateQuestion(QuestionAnamnese question) throws QuestionNotFoundException {
        if(!questions.contains(question)) throw new QuestionNotFoundException();
        int index = questions.indexOf(question);
        questions.set(index,question);
    }

    public boolean searchQuestion(String nom){
        for(QuestionAnamnese question : this.questions){
            //todo: redifine the equals method in one class of quesiton to use it instead of the Object one
            if(question.getQuestion().equals(nom)){
                return true;
            }
        }
        return false;
    }
    public QuestionAnamnese searchQuestionByNom(String nom) {
        for (QuestionAnamnese question : this.questions) {
                if(question.getQuestion().equals(nom)) return question;
        }
        return null;
    }

    public ArrayList<QuestionEnfant> getQuestionsEnfant(){
        ArrayList<QuestionEnfant> questionsEnfant = new ArrayList<>();
        for(QuestionAnamnese question : this.questions){
            if(question instanceof QuestionEnfant ){
                questionsEnfant.add((QuestionEnfant)question);
            }
        }
        return questionsEnfant;
    }

    public ArrayList<QuestionAdult>  getQuestionsAdulte(){
        ArrayList<QuestionAdult> questionsAdulte = new ArrayList<QuestionAdult>();
        for(QuestionAnamnese question : this.questions){
            if(!(question instanceof QuestionEnfant) ){
                questionsAdulte.add((QuestionAdult) question);
            }
        }
        return questionsAdulte;
    }

}
