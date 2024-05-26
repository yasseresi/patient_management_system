package Controllers.OrthophonisteControllers;

import DataBases.OrthophonisteFileDataBase;
import Exceptions.PasswordNotProvidedException;
import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;
import Exceptions.UserNameNotProvidedException;
import Models.Orthophoniste.OrthophonisteModel;
import Models.Orthophoniste.OrthophonisteSchema;
import Utils.Popups;
import Utils.SceneSwitcher;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


public class SignUpController {
    //assign models and views
    private final OrthophonisteModel orthophonisteModel = HelloApplication.orthophonisteModel;

    @FXML
    private TextField Adress;

    @FXML
    private TextField Email;

    @FXML
    private TextField FamilyName;

    @FXML
    private TextField FirstName;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Phone;

    @FXML
    private Hyperlink loginButton;

    @FXML
    private Button signUpButton;

    public void handle(ActionEvent event) throws IOException {

        String nom = FamilyName.getText();
        String prenom= FirstName.getText();
        String password = Password.getText();
        String phone = Phone.getText();
        String email = Email.getText();
        String adress = Adress.getText();
        try  {

            if (nom == null || nom.isEmpty()) throw new UserNameNotProvidedException();
            if (password == null || password.isEmpty()) throw new PasswordNotProvidedException();

            System.out.println("nom = "+ nom + "prenom = "+ prenom);

            if(orthophonisteModel.exists(nom)) throw new UniqueUsernameViolationException();

            orthophonisteModel.create(nom+" "+prenom, password);
            System.out.println("create : nom = "+ nom + "prenom = "+ prenom);

            //Create a directory with the username as the name
            if(new File(HelloApplication.usersDirectoryName + "/" + nom+" "+prenom).mkdirs()){
                System.out.println("Directory created");
                //Create a file for each FileDataBase class
                HelloApplication.currentUserName = nom+" "+prenom;
                new File(HelloApplication.usersDirectoryName + "/"  + HelloApplication.TherapistDBuserName).createNewFile();
                //create patients file~~
                new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.patientsDBFileName).createNewFile();
                new File(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+"listTestQuestions.dt").createNewFile();
                new File(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+"listTestExercice.dt").createNewFile();
                new File(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+HelloApplication.anamneseDBFileName).createNewFile();
                //load them to the model
                HelloApplication.patientModel.load();
                HelloApplication.testquestions.load();
                HelloApplication.testexercice.load();
                HelloApplication.anamneseModel.load();





            }


        } catch (UniqueUsernameViolationException | UserNameNotProvidedException | PasswordNotProvidedException |
                 IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            Popups.showErrorMessage(e.getMessage());
        }
        finally {

            System.out.println("\nUsersDB content the orthophiniste is "+ nom + " " +prenom + " " + password + " " + phone + " " + email + " " + adress + "\n");



            //create days from today to 30 days from now, and 10 days before
//                for (int i = -10; i < 40; i++) {
//                    dayModel.create(LocalDate.now().plusDays(i));
//                }

            try {
                System.out.println(orthophonisteModel.find(nom + " " + prenom).getNom() + orthophonisteModel.find(nom + " " + prenom).getPrenom() );
            } catch (UserDoesNotExistException e) {
                Popups.showErrorMessage("User does not exist");
            }
        }


    }
    public void moveToLoginView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "login-view.fxml", 1200, 625);
    }
}




