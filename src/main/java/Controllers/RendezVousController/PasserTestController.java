package Controllers.RendezVousController;

import DataBases.TestFileDB;
import Exceptions.TestNomUniqueException;
import Models.Anamnese.AnamneseModel;
import Models.Question.*;
import Models.Bilan.BilonSchema;
import Models.Test.*;
import com.example.patient_management_system.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class PasserTestController {

    @FXML
    private VBox libreQuestionsVBox;
    @FXML
    private VBox qcuQuestionsVBox;
    @FXML
    private VBox qcmQuestionsVBox;
    @FXML
    private VBox exerciceQuestionsVBox;

    private Map<TextField, QuestionLibre> libreResponseFields = new HashMap<>();
    private Map<ComboBox<String>, QCU> qcuResponseFields = new HashMap<>();
    private Map<CheckBox[], QCM> qcmResponseFields = new HashMap<>();
    private Map<TextField, Exercice> exerciceResponseFields = new HashMap<>();

    TestModel testModel = new TestModel(new TestFileDB());
    AnamneseModel anamneseModel = HelloApplication.anamneseModel ;
    QuestionQpreuveModel questionQpreuveModel = HelloApplication.testquestions;
    TestExerciceModel testExerciceModel = HelloApplication.testexercice;

    public void initialize(){
        addQuestionsToView();
    }

    private void addQuestionsToView() {
        // Add example questions for demonstration purposes
        ArrayList<QuestionLibre> libreQuestions = new ArrayList<>();
        ArrayList<QCU> qcuQuestions = new ArrayList<>();
        ArrayList<QCM> qcmQuestions = new ArrayList<>();
        ArrayList<Exercice> exerciceQuestions = HelloApplication.testexercice.getExercices();

        // Fill these lists with example questions or fetch from your model
        // Example:
        //libreQuestions.add(new QuestionLibre("What is your favorite color?",""));

        ArrayList<QuestionEpreuve> TestQquestionList = HelloApplication.testquestions.getQuestions();

        for (QuestionEpreuve testquestion : TestQquestionList) {
            if (testquestion instanceof QuestionLibre) {
                libreQuestions.add((QuestionLibre) testquestion);
            } else if (testquestion instanceof QCM qcmQuestion) {
                qcmQuestions.add(qcmQuestion);
            } else if (testquestion instanceof QCU qcuQuestion) {
                qcuQuestions.add(qcuQuestion);
            } else {
                // Handle unexpected question types (optional)
                System.err.println("Unknown question type encountered: " + testquestion.getClass().getName());
            }
        }
        addLibreQuestions(libreQuestions);
        addQCUQuestions(qcuQuestions);
        addQCMQuestions(qcmQuestions);
        addExerciceQuestions(exerciceQuestions);
    }

    private void addLibreQuestions(ArrayList<QuestionLibre> questions) {
        for (QuestionLibre question : questions) {
            Label questionLabel = new Label(question.getQuestion());
            TextField responseField = new TextField();
            libreQuestionsVBox.getChildren().addAll(questionLabel, responseField);
            libreResponseFields.put(responseField, question);
        }
    }

    private void addQCUQuestions(ArrayList<QCU> questions) {
        for (QCU question : questions) {
            Label questionLabel = new Label(question.getQuestion());
            ComboBox<String> responseField = new ComboBox<>();
            responseField.getItems().addAll(question.getPropositions());
            qcuQuestionsVBox.getChildren().addAll(questionLabel, responseField);
            qcuResponseFields.put(responseField, question);
        }
    }

    private void addQCMQuestions(ArrayList<QCM> questions) {
        for (QCM question : questions) {
            Label questionLabel = new Label(question.getQuestion());
            VBox optionsBox = new VBox();
            CheckBox[] checkBoxes = new CheckBox[question.getPropositions().size()];
            for (int i = 0; i < question.getPropositions().size(); i++) {
                checkBoxes[i] = new CheckBox(question.getPropositions().get(i));
                optionsBox.getChildren().add(checkBoxes[i]);
            }
            qcmQuestionsVBox.getChildren().addAll(questionLabel, optionsBox);
            qcmResponseFields.put(checkBoxes, question);
        }
    }

    private void addExerciceQuestions(ArrayList<Exercice> questions) {
        for (Exercice question : questions) {
            Label questionLabel = new Label(question.getNom());
            TextField responseField = new TextField();
            exerciceQuestionsVBox.getChildren().addAll(questionLabel, responseField);
            exerciceResponseFields.put(responseField, question);
        }
    }

    @FXML
    private void handleTestSubmit(javafx.event.ActionEvent event) throws TestNomUniqueException {

        // Create a new TestSchema based on question types
        TestSchema testSchema;
        List<QuestionEpreuve> questions = new ArrayList<>();

        // Process Libre Questions
        for (Map.Entry<TextField, QuestionLibre> entry : libreResponseFields.entrySet()) {
            TextField responseField = entry.getKey();
            QuestionLibre question = entry.getValue();
            String response = responseField.getText();
            question.setReponse(response);
            questions.add(question);
        }

        // Process QCU Questions
        for (Map.Entry<ComboBox<String>, QCU> entry : qcuResponseFields.entrySet()) {
            ComboBox<String> responseField = entry.getKey();
            QCU question = entry.getValue();
            String response = responseField.getValue();
            question.setReponse(response);
            questions.add(question);
        }

        // Process QCM Questions
        for (Map.Entry<CheckBox[], QCM> entry : qcmResponseFields.entrySet()) {
            CheckBox[] checkBoxes = entry.getKey();
            QCM question = entry.getValue();
            ArrayList<String> responses = new ArrayList<>();
            for (CheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    responses.add(checkBox.getText());
                }
            }
            question.setReponses(responses);
            questions.add(question);
        }

        // Process Exercice Questions
        List<Exercice> exercices = new ArrayList<>();
        TreeSet<Float> progress = new TreeSet<>();

        for (Map.Entry<TextField, Exercice> entry : exerciceResponseFields.entrySet()) {
            TextField responseField = entry.getKey();
            Exercice exercice = entry.getValue();
            String response = responseField.getText();
            try {
                float progressValue = Float.parseFloat(response);
                progress.add(progressValue);
                exercice.setProgress(progress); // Add progress instead of setting the entire progress
            } catch (NumberFormatException e) {
                System.err.println("Invalid progress value entered for exercise: " + exercice.getNom());
            }
            exercices.add(exercice);
        }

        // Create TestSchema based on question types
        if (!questions.isEmpty()) {
            testSchema = new TestSchemaQuestionaire("Test Questionnaire de " + HelloApplication.currentPatientName, questions);
        } else if (!exercices.isEmpty()) {
            testSchema = new TestSchemaExercice("Test Exercice de" + HelloApplication.currentPatientName , exercices);
        } else {
            // Handle case where no questions or exercises were answered
            System.err.println("No questions or exercises were answered in the test.");
            return;
        }

        // Add the TestSchema to the TestModel and update bilan
        HelloApplication.testModel.createTest(testSchema); // Assuming createTest creates a new test
        HelloApplication.bilonSchema.setTests(HelloApplication.testModel); // Assuming setTests takes the entire


        try {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("observation-view.fxml" ));
            Scene scene = new Scene(fxmlLoader.load(),600,400);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("PasserRendzVous");
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}