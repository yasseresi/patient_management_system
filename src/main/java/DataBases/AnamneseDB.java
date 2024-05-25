package DataBases;

import Exceptions.QuestionAlreadyExistException;
import Exceptions.QuestionNotFoundException;
import Models.Question.QuestionAdult;
import Models.Question.QuestionAnamnese;
import Models.Question.QuestionEnfant;

import java.io.Serializable;
import java.util.ArrayList;

public interface AnamneseDB extends Serializable {

    void createQuestion(QuestionAnamnese question) throws QuestionAlreadyExistException;
    void deleteQuestion(QuestionAnamnese question) throws QuestionNotFoundException;
    void updateQuestion(QuestionAnamnese question) throws QuestionNotFoundException;
    boolean searchQuestion(String nom);
    QuestionAnamnese searchQuestionByNom(String nom);
    ArrayList<QuestionEnfant> getQuestionsEnfant();
    ArrayList<QuestionAdult> getQuestionsAdulte();

}
