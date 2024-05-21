package Controllers.TestControllers;

import Models.Question.QCU;
import Models.Question.QuestionQpreuveModel;
import com.example.patient_management_system.HelloApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class QCUController {

    @FXML
    private TextField propsitionField;

    @FXML
    private TextField questionField;

    @FXML
    private RadioButton value;

    private QCU qcu = new QCU();

    @FXML
    private void addProposition() {
        qcu.getPropositions().add(propsitionField.getText());
        if (value.isSelected() && qcu.getReponse().isEmpty()) {
            qcu.setReponse(propsitionField.getText());
        }
        propsitionField.clear();
        value.setSelected(false);
    }

    @FXML
    private void submitQCU(Event event ) throws IOException {
        qcu.setQuestion(questionField.getText());
        HelloApplication.testquestions.addQuestion(qcu);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tests-view.fxml"));
        Parent root =(Parent) fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("List Des tests");
        stage.show();
        //todo: make these instructions as toString() in qcu and qcm
        System.out.println("ceated qcu:title: " +qcu.getQuestion()+"\n propos: ");
        for (String question : qcu.getPropositions()){
            System.out.println(question);
        }
        System.out.println("correct answer is :"+ qcu.getReponse());
    }

    public void loadQuestion(String question) {
        questionField.setText(question);
        // Add code to populate propositions and correct answer
    }
}
