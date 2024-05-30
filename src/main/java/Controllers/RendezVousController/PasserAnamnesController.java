package Controllers.RendezVousController;

import Models.Anamnese.AnamneseModel;
import Models.Bilan.BilonModel;
import Models.Bilan.BilonSchema;
import Models.DossierPatient.DossierPatientSchema;
import Models.Patient.PatientModel;
import Models.Question.QuestionAdult;
import Models.Question.QuestionEnfant;
import Models.RendezVous.RendezVousSchema;
import com.example.patient_management_system.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PasserAnamnesController {

    @FXML
    public Button validateButton;

    @FXML
    private VBox questionsVBox;

    private Map<TextField, Object> responseFields = new HashMap<>();

    public void setRendezVous(RendezVousSchema rendezVous) {
        questionsVBox.getChildren().clear(); // Clear previous questions if any
        responseFields.clear(); // Clear previous responses if any

        if (HelloApplication.patientModel.getPatientById(HelloApplication.currentPatientName).getAge() < 13) {
            ArrayList<QuestionEnfant> listQuestionsEnfant = HelloApplication.anamneseModel.getQuestionsEnfant();
            System.out.println("Type est Enfant");
            addQuestionsToView(listQuestionsEnfant);
        } else {
            ArrayList<QuestionAdult> listQuestionsAdulte = HelloApplication.anamneseModel.getQuestionsAdulte();
            System.out.println("Type est Adult");
            addQuestionsToView(listQuestionsAdulte);
        }
    }

    private void addQuestionsToView(ArrayList<?> questions) {
        for (Object question : questions) {
            String questionText = "";
            if (question instanceof QuestionEnfant) {
                questionText = ((QuestionEnfant) question).getQuestion();
            } else if (question instanceof QuestionAdult) {
                questionText = ((QuestionAdult) question).getQuestion();
            }

            Label questionLabel = new Label(questionText);
            TextField responseField = new TextField();

            questionsVBox.getChildren().addAll(questionLabel, responseField);
            responseFields.put(responseField, question);
        }
    }

    @FXML
//    private void handleSubmit( ActionEvent event ) {
//
//    }

    public void handleSubmit(javafx.event.ActionEvent event) {
        for (Map.Entry<TextField, Object> entry : responseFields.entrySet()) {
            TextField responseField = entry.getKey();
            Object question = entry.getValue();
            String response = responseField.getText();

            if (question instanceof QuestionEnfant) {
                ((QuestionEnfant) question).setReponse(response);
            } else if (question instanceof QuestionAdult) {
                ((QuestionAdult) question).setReponse(response);
            }

            System.out.println("Response: " + response);
        }

        HelloApplication.bilonSchema.setAnamneseModel(HelloApplication.anamneseModel);

        // Add the bilan to the BilonModel (assuming you have a way to access it)
        //HelloApplication.bilonModel.addBilan(HelloApplication.bilonSchema);


        // Change the scene to the Tests window
        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("passer_test.fxml"));
//            Parent root = fxmlLoader.load();
//
//            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("passer_test.fxml" ));
            Scene scene = new Scene(fxmlLoader.load(),600,400);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("PasserRendzVous");
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Anamnesis added to a new bilan schema.");
    }
}
