package Models.Question;

import DataBases.OrthophonistheDataBase;
import DataBases.PatientDB;
import com.example.patient_management_system.HelloApplication;

import java.io.*;
import java.util.ArrayList;

import static com.example.patient_management_system.HelloApplication.patientsDBFileName;

public class QuestionQpreuveModel implements Serializable{

    private ArrayList<QuestionEpreuve> questions = new ArrayList<>();

    public QuestionQpreuveModel() {
    }

    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+"listTestQuestions.dt"))){
            objectOutputStream.writeObject(questions);
            questions.clear();
        }
    }



    public void load() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName+"/"+HelloApplication.currentUserName + "/" + "listTestQuestions.dt"))) {
            questions = (ArrayList<QuestionEpreuve>) objectInputStream.readObject();
            System.out.println("loading the questionsteststatiques model");
        } catch (EOFException e) {
            System.out.println("Error while loading questionsteststatiques model: Unexpected end of file. The database file may be corrupted or incomplete.");
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }


    public void addQuestion(QuestionEpreuve question) {
        questions.add(question);
    }

    public void removeQuestion(QuestionEpreuve question) {
        questions.remove(question);
    }

    public void updateQuestion(QuestionEpreuve oldQuestion, QuestionEpreuve newQuestion) {
        questions.remove(oldQuestion);
        questions.add(newQuestion);
    }

    public ArrayList<QuestionEpreuve> getQuestions() {
        return questions;
    }

    public QuestionEpreuve getQuestionByText(String text) {
        for (QuestionEpreuve question : questions) {
            if (question.getQuestion().equals(text)) {
                return question;
            }
        }
        return null;
    }

    public String getQuestionTypeByText(String text) {
        for (QuestionEpreuve question : questions) {
            if (question.getQuestion().equals(text)) {
                return getQuestionType(question);
            }
        }
        return null;
    }

    private String getQuestionType(QuestionEpreuve question) {
        if (question instanceof QCU) {
            return "QCU";
        } else if (question instanceof QuestionLibre) {
            return "Libre";
        } else if (question instanceof QCM) {
            return "QCM";
        }
        return null;
    }
}
