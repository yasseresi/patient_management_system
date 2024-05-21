package Controllers.OrthophonisteControllers;

import DataBases.OrthophonisteFileDataBase;
import Exceptions.PasswordNotProvidedException;
import Exceptions.UniqueUsernameViolationException;
import Exceptions.UserDoesNotExistException;
import Exceptions.UserNameNotProvidedException;
import Models.Orthophoniste.OrthophonisteModel;
import Models.Orthophoniste.OrthophonisteSchema;
import Utils.Popups;
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



            String nom = FirstName.getText();
            String prenom= FamilyName.getText();
            String password = Password.getText();
            String phone = Phone.getText();
            String familyName= FamilyName.getText();
            String email = Email.getText();
            String adress = Adress.getText();
            try  {

                if (nom == null || nom.isEmpty()) throw new UserNameNotProvidedException();
                if (password == null || password.isEmpty()) throw new PasswordNotProvidedException();

                if(orthophonisteModel.exists(nom)) throw new UniqueUsernameViolationException();

                orthophonisteModel.create(nom+" "+prenom, password);

                //Create a directory with the username as the name
                if(new File(HelloApplication.usersDirectoryName + "/" + nom+" "+prenom).mkdirs()){
                    System.out.println("Directory created");
                    //Create a file for each FileDataBase class
                    HelloApplication.currentUserName = nom+" "+prenom;
                    new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.TherapistDBuserName).createNewFile();
                    //create patients file~~
                    new File(HelloApplication.usersDirectoryName + "/" + HelloApplication.currentUserName + "/" + HelloApplication.patientsDBFileName).createNewFile();
                    new File(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+"listTestQuestions.dt").createNewFile();
                    new File(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+"listTestExercice.dt").createNewFile();
                    new File(HelloApplication.usersDirectoryName + "/"+ HelloApplication.currentUserName +"/"+HelloApplication.anamneseDBFileName).createNewFile();


                    //TODO: create a view for the users who sign up for the first time to get their settings
//                    System.out.println(new File(HelloApplication.usersDirectoryName + "/" + username + "/" + HelloApplication.taskDbFileName).createNewFile());
//                    new File(HelloApplication.usersDirectoryName + "/" + username + "/" + HelloApplication.freeSlotDbFileName).createNewFile();
//                    new File(HelloApplication.usersDirectoryName + "/" + username + "/" + HelloApplication.dayDbFileName).createNewFile();
//                    new File(HelloApplication.usersDirectoryName + "/" + username + "/" + HelloApplication.projectDbFileName).createNewFile();

                    //Show the calendar view

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-page-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 840, 500);
                    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("HomePage");

                    //center the view on the user's screen
                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2);
                    stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2);
                    stage.setScene(scene);
                } else {
                    throw new IOException("A problem occurred when creating the directory");
                }

            } catch (UniqueUsernameViolationException | UserNameNotProvidedException | PasswordNotProvidedException | IOException e) {
                System.out.println(e.getMessage());
                Popups.showErrorMessage(e.getMessage());
            }
            finally {

                System.out.println("\nUsersDB content the orthophiniste is "+ nom + " " +prenom + " " + password + " " + phone + " " + familyName + " " + email + " " + adress + "\n");


                //create days from today to 30 days from now, and 10 days before
//                for (int i = -10; i < 40; i++) {
//                    dayModel.create(LocalDate.now().plusDays(i));
//                }

                try {
                    System.out.println(orthophonisteModel.find(nom + " " + prenom));
                } catch (UserDoesNotExistException e) {
                    Popups.showErrorMessage("User does not exist");
                }
            }


        }
        public void moveToLoginView(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Login");

            //center the view on the user's screen
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2);
            stage.setScene(scene);
        }
    }




