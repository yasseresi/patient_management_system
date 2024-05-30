package Controllers.DossierPatientsControllers;

import Exceptions.PatientAlreadyExistException;
import Models.Patient.PatientSchema;
import Utils.Popups;
import com.example.patient_management_system.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatePatientController {
    public TextField familyName;
    public TextField firstName;
    public TextField Adress;
    public DatePicker Date;
    public TextField Lieu;
    public TextField phone;

    public void create(javafx.event.ActionEvent event) throws IOException, PatientAlreadyExistException {
        try {
            if (HelloApplication.patientModel.exists(familyName.getText(), firstName.getText())) {
                throw new PatientAlreadyExistException();
            }
            HelloApplication.patientModel.create(familyName.getText(),
                    firstName.getText(),
                    Date.getValue(),
                    Lieu.getText(),
                    Adress.getText(),
                    Integer.parseInt(phone.getText()),
                    true);
        } catch (PatientAlreadyExistException e) {
            Popups.showErrorMessage("Patient already exists");
        }


        //go to the dossier patient view
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dossier-patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Patients");
        stage.setScene(scene);

    }

    public void back(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dossier-patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Patients");
        stage.setScene(scene);

    }


}
