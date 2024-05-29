package Controllers.ProfileControllers;

import Exceptions.UserDoesNotExistException;
import Models.Orthophoniste.OrthophonisteSchema;
import Utils.Popups;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Button accueilButton;

    @FXML
    private Button btn_adr;

    @FXML
    private Button btn_mdp;

    @FXML
    private Button btn_tele;

    @FXML
    private TextField tf_adr;

    @FXML
    private TextField tf_mdp;

    @FXML
    private TextField tf_tele;

    private String currentUser;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentUser = HelloApplication.currentUserName;
        tf_tele.setText("0");
    }





    @FXML
    public void toTests(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tests-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);
    }


    @FXML
    public void toAnamnese(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("anamnese-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);
    }
    @FXML
    void toAccueilPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("HomePage");
        stage.setScene(scene);


    }

    @FXML
    void toLogOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("login");
        stage.setScene(scene);
        System.out.println("Logged out successfully");
        HelloApplication.patientModel.save();
        HelloApplication.testquestions.save();
        HelloApplication.testexercice.save();
        HelloApplication.anamneseModel.save();
        System.out.println("Files saved succeessfully");
        HelloApplication.currentUserName = null;
        HelloApplication.currentPatientName = null;
        System.out.println("the current user after logout is "+HelloApplication.currentUserName);
        System.out.println("the current patient after logout is "+HelloApplication.currentPatientName);

    }

    @FXML
    void toDossiersPatients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dossier-patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Patients");
        stage.setScene(scene);


    }

    @FXML
    void toRendezVousPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rendez vous");
        stage.setScene(scene);


    }

    @FXML
    void toStatistiquePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);


    }


    @FXML
    void handleUpdateAdr(ActionEvent event) {
        String adr = this.tf_adr.getText();
        if(adr.isEmpty()){
            Popups.showErrorMessage("L'adresse est vide ");
        }else {
            HelloApplication.orthophonisteModel.updateAdress(currentUser,adr);
            Popups.showSuccessMessage("success","adress updated succesfuly");
            tf_adr.clear();
        }

    }

    @FXML
    void handleUpdatePassword(ActionEvent event) {
            String paswrd = this.tf_mdp.getText();
        if (paswrd.isEmpty()) {
            Popups.showErrorMessage("le nouveau mot de pass est vide");
        } else {
            HelloApplication.orthophonisteModel.updatePassword(currentUser, paswrd);
            Popups.showSuccessMessage("success","password updated succesfuly");
            tf_mdp.clear();
        }
    }

    @FXML
    void handleUpdateTele(ActionEvent event) {
        int nbPhone = 0;
        try {
             nbPhone = Integer.parseInt(this.tf_tele.getText());
            if (nbPhone == 0){
                Popups.showErrorMessage("le nouveau numero de telephone est vide");
            }else {
                HelloApplication.orthophonisteModel.updatePhoneNumber(currentUser,nbPhone);
                Popups.showSuccessMessage("phone number updated","your phone number has been updated succesfuly "+nbPhone);
                tf_tele.clear();
            }
        }catch (NumberFormatException e){
            Popups.showErrorMessage("Entrer une  format correct de numbero telephone");
        }


    }


}
