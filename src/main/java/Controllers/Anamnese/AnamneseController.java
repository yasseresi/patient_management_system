package Controllers.Anamnese;

import Models.Question.TypeAdult;
import Models.Question.TypeEnfant;
import Utils.Popups;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

public class AnamneseController {

    @FXML
    private TextField childrenQuestionField;

    @FXML
    private TextField adultQuestionField;

    @FXML
    private ListView<String> childrenQuestionListView;

    @FXML
    private ListView<String> adultQuestionListView;

    @FXML
    private ChoiceBox<TypeEnfant> childrenTypeChoiceBox;

    @FXML
    private ChoiceBox<TypeAdult> adultTypeChoiceBox;

    @FXML
    private ToggleButton childrenToggle;

    @FXML
    private ToggleButton adultToggle;

    @FXML
    private VBox childrenPage;

    @FXML
    private VBox adultPage;

    private ObservableList<String> childrenQuestions;
    private ObservableList<String> adultQuestions;

    public void initialize() {
        childrenQuestions = FXCollections.observableArrayList();
        adultQuestions = FXCollections.observableArrayList();

        childrenQuestionListView.setItems(childrenQuestions);
        adultQuestionListView.setItems(adultQuestions);

        childrenTypeChoiceBox.setItems(FXCollections.observableArrayList(TypeEnfant.values()));
        adultTypeChoiceBox.setItems(FXCollections.observableArrayList(TypeAdult.values()));

        childrenToggle.setSelected(true);
        childrenPage.setVisible(true);
        adultPage.setVisible(false);
    }

    @FXML
    private void toggleChildren() {
        childrenToggle.setSelected(true);
        adultToggle.setSelected(false);
        childrenPage.setVisible(true);
        adultPage.setVisible(false);
    }

    @FXML
    private void toggleAdult() {
        childrenToggle.setSelected(false);
        adultToggle.setSelected(true);
        childrenPage.setVisible(false);
        adultPage.setVisible(true);
    }

    @FXML
    private void addChildrenQuestion() {
        String question = childrenQuestionField.getText().trim();
        TypeEnfant type = childrenTypeChoiceBox.getValue();
        if (!question.isEmpty() && type != null) {
            childrenQuestions.add(type + ":/t" + question);
            childrenQuestionField.clear();
            childrenTypeChoiceBox.setValue(null);
        } else {
            Popups.showErrorMessage("Input Error", "Question field and type cannot be empty!");
        }
    }

    @FXML
    private void addAdultQuestion() {
        String question = adultQuestionField.getText().trim();
        TypeAdult type = adultTypeChoiceBox.getValue();
        if (!question.isEmpty() && type != null) {
            adultQuestions.add(type + ": " + question);
            adultQuestionField.clear();
            adultTypeChoiceBox.setValue(null);
        } else {
            Popups.showErrorMessage("Input Error", "Question field and type cannot be empty!");
        }
    }

    @FXML
    private void deleteChildrenQuestion() {
        String selectedQuestion = childrenQuestionListView.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            childrenQuestions.remove(selectedQuestion);
        } else {
            Popups.showErrorMessage("Selection Error", "Please select a question to delete!");
        }
    }

    @FXML
    private void deleteAdultQuestion() {
        String selectedQuestion = adultQuestionListView.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            adultQuestions.remove(selectedQuestion);
        } else {
            Popups.showErrorMessage("Selection Error", "Please select a question to delete!");
        }
    }

    @FXML
    private void modifyChildrenQuestion() {
        String selectedQuestion = childrenQuestionListView.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            String newQuestion = childrenQuestionField.getText().trim();
            TypeEnfant newType = childrenTypeChoiceBox.getValue();
            if (!newQuestion.isEmpty() && newType != null) {
                int selectedIndex = childrenQuestionListView.getSelectionModel().getSelectedIndex();
                childrenQuestions.set(selectedIndex, newType + ": " + newQuestion);
                childrenQuestionField.clear();
                childrenTypeChoiceBox.setValue(null);
            } else {
                Popups.showErrorMessage("Input Error", "Question field and type cannot be empty!");
            }
        } else {
            Popups.showErrorMessage("Selection Error", "Please select a question to modify!");
        }
    }

    @FXML
    private void modifyAdultQuestion() {
        String selectedQuestion = adultQuestionListView.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            String newQuestion = adultQuestionField.getText().trim();
            TypeAdult newType = adultTypeChoiceBox.getValue();
            if (!newQuestion.isEmpty() && newType != null) {
                int selectedIndex = adultQuestionListView.getSelectionModel().getSelectedIndex();
                adultQuestions.set(selectedIndex, newType + ": " + newQuestion);
                adultQuestionField.clear();
                adultTypeChoiceBox.setValue(null);
            } else {
                Popups.showErrorMessage("Input Error", "Question field and type cannot be empty!");
            }
        } else {
            Popups.showErrorMessage("Selection Error", "Please select a question to modify!");
        }
    }
}
