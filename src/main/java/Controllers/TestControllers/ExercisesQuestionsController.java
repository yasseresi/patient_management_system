package Controllers.TestControllers;

import Models.Question.QuestionEpreuve;
import Models.Test.Exercice;
import Models.Test.TestExerciceModel;
import com.example.patient_management_system.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ExercisesQuestionsController {

    @FXML
    private ToggleButton exerciseToggle;
    @FXML
    private ToggleButton questionToggle;
    @FXML
    private StackPane stackPane;
    @FXML
    private VBox exercisePage;
    @FXML
    private VBox questionPage;
    @FXML
    private TextField exerciseNameField;
    @FXML
    private ListView<String> exerciseListView;
    @FXML
    private ChoiceBox<String> questionTypeChoiceBox;
    @FXML
    private TextField questionField;
    @FXML
    private ListView<String> questionListView;
    private  TestExerciceModel exerciceModel = HelloApplication.testexercice;

    @FXML
    private void initialize() {
        questionTypeChoiceBox.getItems().addAll("QCM", "QCU", "Libre");
        updateQuestionListView();
    }

    @FXML
    private void toggleExercise() {
        exercisePage.setVisible(true);
        questionPage.setVisible(false);
    }

    @FXML
    private void toggleQuestion() {
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

}
