
package Controllers.TestControllers;

import Models.Question.*;
        import Models.Test.Exercice;
import Models.Test.TestExerciceModel;
import Utils.Popups;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateTestsController {

    @FXML
    private ToggleButton exerciseToggle;

    @FXML
    private ToggleButton questionToggle;

    @FXML
    private VBox exercisePage;

    @FXML
    private VBox questionPage;

    @FXML
    private TextField exerciseNameField;

    @FXML
    private StackPane stackPane;


    @FXML
    private ListView<String> exerciseListView;
    @FXML
    private ChoiceBox<String> questionTypeChoiceBox;
    @FXML
    private TextField questionField;
    @FXML
    private ListView<String> questionListView;

    private TestExerciceModel exerciceModel = HelloApplication.testexercice;



    public void initialize() {


        questionTypeChoiceBox.getItems().addAll("QCM", "QCU", "Libre");
        updateQuestionListView();

        toggoleExercice();
    }

    @FXML
    void toAccueilPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("HomePage");
        stage.setScene(scene);


    }

    @FXML
    void toDossiersPatients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dossier-patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Patients");
        stage.setScene(scene);
    }

    @FXML
    void toLogOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
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
    void toProfilPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profil-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profil");
        stage.setScene(scene);


    }

    @FXML
    void toRendezVousPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rendez-vous-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rendez vous");
        stage.setScene(scene);


    }

    @FXML
    void toStatistiquePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("statistique-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);


    }










    @FXML
    private void toggoleExercice() {
        exerciseToggle.setSelected(true);
        questionToggle.setSelected(false);
        exercisePage.setVisible(true);
        questionPage.setVisible(false);
    }

    @FXML
    private void toggleQuestion() {
        exerciseToggle.setSelected(false);
        questionToggle.setSelected(true);
        exercisePage.setVisible(false);
        questionPage.setVisible(true);
    }

    @FXML
    private void addExercise() {
        String exerciseName = exerciseNameField.getText();
        if (!exerciseName.isEmpty()) {
            Exercice newExercise = new Exercice(exerciseName);
            exerciceModel.addExercice(newExercise);
            updateExerciseListView();
            exerciseNameField.clear();
        }
    }

    @FXML
    private void modifyExercise() {
        String selectedExerciseName = exerciseListView.getSelectionModel().getSelectedItem();
        if (selectedExerciseName != null) {
            TextInputDialog dialog = new TextInputDialog(selectedExerciseName);
            dialog.setTitle("Modify Exercise");
            dialog.setHeaderText("Modify the exercise name");
            dialog.setContentText("Exercise name:");
            dialog.showAndWait().ifPresent(newName -> {
                Exercice oldExercise = exerciceModel.getExerciceByName(selectedExerciseName);
                if (oldExercise != null) {
                    oldExercise.setNom(newName);
                    updateExerciseListView();
                }
            });
        }
    }

    @FXML
    private void deleteExercise() {
        String selectedExerciseName = exerciseListView.getSelectionModel().getSelectedItem();
        if (selectedExerciseName != null) {
            Exercice exercise = exerciceModel.getExerciceByName(selectedExerciseName);
            if (exercise != null) {
                exerciceModel.removeExercice(exercise);
                updateExerciseListView();
            }
        }
    }

    @FXML
    private void addQuestion(ActionEvent event) {
        String questionType = questionTypeChoiceBox.getValue();
        if (questionType != null) {
            switch (questionType) {
                case "QCM":
                    openPopup(event, "QCMPopup.fxml", "Add QCM Question");
                    break;
                case "QCU":
                    openPopup(event, "QCUPopup.fxml", "Add QCU Question");
                    break;
                case "Libre":
                    openPopup(event, "LibrePopup.fxml", "Add Libre Question");
                    break;
            }
        }
    }

    @FXML
    private void modifyQuestion() {
        String selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            String questionType = HelloApplication.testquestions.getQuestionTypeByText(selectedQuestion);
            switch (questionType) {
                case "QCM":
                    openPopup("QCMPopup.fxml", "Modify QCM Question", selectedQuestion, questionType);
                    break;
                case "QCU":
                    openPopup("QCUPopup.fxml", "Modify QCU Question", selectedQuestion, questionType);
                    break;
                case "Libre":
                    openPopup("LibrePopup.fxml", "Modify Libre Question", selectedQuestion, questionType);
                    break;
            }
        }
    }


    private void openPopup(Event event, String fxmlPath, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
            Parent root =(Parent) fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void openPopup(String fxmlPath, String title, String question, String questionType) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
            Parent root = fxmlLoader.load();

            if (questionType.equals("QCM")) {
                QCMController controller = fxmlLoader.getController();
                controller.loadQuestion(question);
            } else if (questionType.equals("QCU")) {
                QCUController controller = fxmlLoader.getController();
                controller.loadQuestion(question);
            } else if (questionType.equals("Libre")) {
                QuestionLibreController controller = fxmlLoader.getController();
                controller.loadQuestion(question);
            }

            DialogPane dialogPane = new DialogPane();
            dialogPane.setContent(root);

            Dialog<String> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle(title);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void deleteQuestion() {
        String selectedQuestion = questionListView.getSelectionModel().getSelectedItem();
        questionListView.getItems().remove(selectedQuestion);
    }

    private void updateExerciseListView() {
        exerciseListView.getItems().clear();
        for (Exercice exercice : exerciceModel.getExercices()) {
            exerciseListView.getItems().add(exercice.getNom());
        }
    }
    private void updateQuestionListView(){
        questionListView.getItems().clear();
        for(QuestionEpreuve questionEpreuve : HelloApplication.testquestions.getQuestions()) {
            questionListView.getItems().add(questionEpreuve.getQuestion());
        }
    }



    @FXML
    public void toAnamnese(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("anamnese-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setTitle("statistique");
        stage.setScene(scene);
    }
}
