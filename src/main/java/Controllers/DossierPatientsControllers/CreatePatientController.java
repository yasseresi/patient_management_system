package Controllers.DossierPatientsControllers;

import Exceptions.PatientAlreadyExistException;
import Models.Patient.PatientModel;
import Utils.Popups;
import com.example.patient_management_system.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class CreatePatientController {
    public TextField familyName;
    public TextField firstName;
    public TextField Adress;
    public DatePicker Date;
    public TextField Lieu;
    public TextField phone;
    PatientModel patientModel = HelloApplication.patientModel;

    public void create(javafx.event.ActionEvent event) throws IOException, PatientAlreadyExistException {
        HelloApplication.currentPatientName = familyName.getText() + " " + firstName.getText();
        if (patientModel.isEmpty()) {
            System.out.println("there is no patient yet");

            //creation de folder pour chaque patient
            if (new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName).mkdirs()) {
                System.out.println("Directory created");
                //Create a file for each FileDataBase class
                System.out.println(new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName + "/" + HelloApplication.categoryDbFileName).createNewFile());
                new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName + "/" + HelloApplication.boDBFileName).createNewFile();
                new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.currentPatientName + "/" + HelloApplication.fichSuivisDBFileName).createNewFile();
            } else {

                throw new IOException("A problem occurred when creating the directory");
            }
        }else if(patientModel.exists(familyName.getText(), firstName.getText())){
            Popups.showErrorMessage("Patient already exists");
            throw new PatientAlreadyExistException();
        }
        patientModel.create(familyName.getText(),firstName.getText(),Date.getValue(),Lieu.getText(),Adress.getText(), Integer.parseInt(phone.getText()),true);


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
