package Controllers.OrthophonisteControllers;

import Exceptions.PasswordNotProvidedException;
import Exceptions.UserDoesNotExistException;
import Exceptions.UserNameNotProvidedException;

import Exceptions.WrongPasswordException;
import Models.Orthophoniste.OrthophonisteModel;
import Models.Orthophoniste.OrthophonisteSchema;
import Models.Patient.PatientModel;
import Utils.Popups;
import Utils.SceneSwitcher;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;

import static com.example.patient_management_system.HelloApplication.currentPatientName;
import static com.example.patient_management_system.HelloApplication.orthophonisteModel;


public class LogInController {
    private final OrthophonisteModel userModel = orthophonisteModel;
    @FXML
    TextField userName;
    @FXML
    PasswordField password;
    @FXML
    Button loginButton;

    public void handle() throws IOException {

        String username = this.userName.getText();
        String password = this.password.getText();
        try  {

            if (username == null || username.isEmpty()) throw new UserNameNotProvidedException();
            if (password == null || password.isEmpty()) throw new PasswordNotProvidedException();

            OrthophonisteSchema user = orthophonisteModel.find(username); //If he doesn't exist an exception will be thrown

            System.out.println("username trouve = "+user.getNom()+" "+user.getPrenom() + ", password = "+user.getPassword());

            if (!user.getPassword().equals(password)) throw new WrongPasswordException();
            System.out.println("the current user before login is "+HelloApplication.currentUserName);
            System.out.println("the current patient before login is "+HelloApplication.currentPatientName);
            HelloApplication.currentUserName = username;
            System.out.println("the current user after login is "+HelloApplication.currentUserName);
            System.out.println("the current patient after login is "+HelloApplication.currentPatientName);
            //todo: load the needed files
            HelloApplication.patientModel.load();
            HelloApplication.anamneseModel.load();
            HelloApplication.testquestions.load();
            HelloApplication.testquestions.load();
            HelloApplication.dossierPatientModel.load();
//            HelloApplication.currentUserSettings = user.getSettings();

            //Load the DBs from the corresponding files
//            HelloApplication.taskModel.load();
//            HelloApplication.dayModel.load();

//            //create days from today to 30 days from now, and 10 days before
//            for (int i = -10; i < 40; i++) {
//                HelloApplication.dayModel.create(LocalDate.now().plusDays(i));
//            }

//            HelloApplication.freeSlotModel.load();
//            HelloApplication.projectsModel.load();
//            HelloApplication.categoryModel.load();
            System.out.println("Logged in successfully");

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-page-view.fxml"));
//            fxmlLoader.setControllerFactory(c -> new LoginController());
            Scene scene = new Scene(fxmlLoader.load(), 840, 500);
            Stage stage = (Stage) userName.getScene().getWindow();
            stage.setTitle("home");

            //center the view on the user's screen
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2);
            stage.setScene(scene);

        } catch (PasswordNotProvidedException | UserNameNotProvidedException | UserDoesNotExistException |
                 WrongPasswordException e) {
            System.out.println(e.getMessage());
            Popups.showErrorMessage(e.getMessage());
        }
    }
    private void showErrorMessage(String message) {
        Alert errorMessage = new Alert(Alert.AlertType.ERROR);
        errorMessage.setContentText(message);
        errorMessage.setHeaderText("Error");
        errorMessage.setTitle("Error");
        errorMessage.showAndWait();
    }
    public void moveToSignupView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "signup-view.fxml", 1200, 625);
    }
}

