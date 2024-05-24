package Models.Anamnese;

import DataBases.AnamneseDB;
import DataBases.PatientDB;
import Exceptions.QuestionAlreadyExistException;
import Exceptions.QuestionNotFoundException;
import Models.Question.*;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

import static com.example.patient_management_system.HelloApplication.patientsDBFileName;

public class AnamneseModel implements Serializable {


    AnamneseDB dataBase;

    public AnamneseModel(AnamneseDB dataBase){
        this.dataBase = dataBase;
    }


    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+HelloApplication.anamneseDBFileName))){
            objectOutputStream.writeObject(dataBase);
        }
    }

    public void load() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName+"/"+HelloApplication.currentUserName + "/" + HelloApplication.anamneseDBFileName))) {
            dataBase = (AnamneseDB) objectInputStream.readObject();
            System.out.println("loading the anamnese static  model");
        } catch (EOFException e) {
            System.out.println("Error while loading anamnese model: Unexpected end of file. The database file may be corrupted or incomplete.");
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    public void createQuestion(QuestionAnamnese question) throws QuestionAlreadyExistException {
        dataBase.createQuestion(question);
    }
    public void createChildQuestion(String nom, TypeEnfant type) throws QuestionAlreadyExistException {
        dataBase.createQuestion(new QuestionEnfant(nom, type));
    }

    public void createAdultQuestion(String nom, TypeAdult type) throws  QuestionAlreadyExistException {
        dataBase.createQuestion(new QuestionAdult(nom, type));
    }
    public void deleteQuestion(QuestionAnamnese question) throws QuestionNotFoundException {
        dataBase.deleteQuestion(question);
    }

    public void updateQuestion(QuestionAnamnese question) throws QuestionNotFoundException {
        dataBase.updateQuestion(question);
    }

    public boolean searchQuestion(String nom) {
        return dataBase.searchQuestion(nom);
    }

    public QuestionAnamnese searchQuestionByNom(String nom) {
        return dataBase.searchQuestionByNom(nom);
    }

    public ArrayList<QuestionEnfant> getQuestionsEnfant() {
        return dataBase.getQuestionsEnfant();
    }

    public ArrayList<QuestionAdult> getQuestionsAdulte() {
        return dataBase.getQuestionsAdulte();
    }

}
