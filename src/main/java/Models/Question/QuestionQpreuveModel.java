package Models.Question;

import DataBases.OrthophonistheDataBase;
import com.example.patient_management_system.HelloApplication;

import java.io.*;
import java.util.ArrayList;

public class QuestionQpreuveModel {

    ArrayList<QuestionEpreuve> questions = new ArrayList<QuestionEpreuve>();

    public QuestionQpreuveModel() {
    }

    public void save() throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+"listTestQuestions.dt"))){
            objectOutputStream.writeObject(questions);
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+"listTestQuestions.dt"))) {
            questions = (ArrayList<QuestionEpreuve>) objectInputStream.readObject();
            System.out.println("loading the orthophiste model");
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
