package Controllers.TestControllers;

import Models.Question.QCM;
import Models.Question.QuestionQpreuveModel;
import com.example.patient_management_system.HelloApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class QCMController {

    @FXML
    private TextField propsitionField;

    @FXML
    private TextField questionField;

    @FXML
    private CheckBox value;

    private QCM qcm = new QCM();

    @FXML
    private void addProposition(Event event) {
        qcm.getPropositions().add(propsitionField.getText());
        System.out.println(propsitionField.getText());
        if (value.isSelected()) {
            qcm.getReponses().add(propsitionField.getText());
        }
        propsitionField.clear();
        value.setSelected(false);
    }

    @FXML
    private void submitQCM(Event event) throws IOException {
        qcm.setQuestion(questionField.getText());
        HelloApplication.testquestions.addQuestion(qcm);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tests-view.fxml"));
        Parent root =(Parent) fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("List Des tests");
        stage.show();
    }

    public void loadQuestion(String question) {
        questionField.setText(question);
        // Add code to populate propositions and correct answer
    }
}
