package DataBases;

import Exceptions.QuestionAlreadyExistException;
import Exceptions.QuestionNotFoundException;
import Models.Question.QuestionAnamnese;

import java.util.ArrayList;

public interface AnamneseDB {


    void createQuestion(QuestionAnamnese question) throws QuestionAlreadyExistException;
    void deleteQuestion(QuestionAnamnese question) throws QuestionNotFoundException;
    void updateQuestion(QuestionAnamnese question) throws QuestionNotFoundException;
    boolean searchQuestion(String nom);
    QuestionAnamnese searchQuestionByNom(String nom);
    ArrayList<QuestionAnamnese> getQuestionsEnfant();
    ArrayList<QuestionAnamnese> getQuestionsAdulte();

}
