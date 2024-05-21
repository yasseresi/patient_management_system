package Controllers.TestControllers;

import Models.Question.QuestionEpreuve;
import Models.Question.QuestionLibre;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class QuestionLibreController {

    @FXML
    private TextField questionField;

    @FXML
    private void submitQuestionLibre(Event event) throws IOException {
        String question = questionField.getText();
        QuestionLibre questionLibre = new QuestionLibre();
        questionLibre.setQuestion(question);
        HelloApplication.testquestions.addQuestion(questionLibre);

        //print the model content
        for (QuestionEpreuve question1 : HelloApplication.testquestions.getQuestions()){
            if(question1 instanceof QuestionLibre ) System.out.println(question1.getQuestion());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tests-view.fxml"));
        Parent root =(Parent) fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("List Des tests");
        stage.show();
    }

    public void loadQuestion(String question) {
        questionField.setText(question);
    }
}
