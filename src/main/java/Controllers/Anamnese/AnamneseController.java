package Controllers.Anamnese;

import Exceptions.QuestionAlreadyExistException;
import Exceptions.QuestionNotFoundException;
import Models.Anamnese.AnamneseModel;
import Models.Question.QuestionAdult;
import Models.Question.QuestionEnfant;
import Models.Question.TypeAdult;
import Models.Question.TypeEnfant;
import Utils.Popups;
import com.example.patient_management_system.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AnamneseController {

    @FXML
    private VBox adultPage;

    @FXML
    private TextField adultQuestionField;

    @FXML
    private TableView<QuestionAdult> adultQuestionListView;

    @FXML
    private ToggleButton adultToggle;

    @FXML
    private ChoiceBox<TypeAdult> adultTypeChoiceBox;

    @FXML
    private VBox childrenPage;

    @FXML
    private TextField childrenQuestionField;

    @FXML
    private TableView<QuestionEnfant> childrenQuestionListView;

    @FXML
    private ToggleButton childrenToggle;

    @FXML
    private ChoiceBox<TypeEnfant> childrenTypeChoiceBox;

    @FXML
    private StackPane stackPane;

    private AnamneseModel anamneseModel = HelloApplication.anamneseModel;

    private ObservableList<QuestionEnfant> childrenQuestions ;
    private ObservableList<QuestionAdult> adultQuestions ;

    @FXML
    private TableColumn<QuestionEnfant, String> childrenQuestionColumn;

    @FXML
    private TableColumn<QuestionAdult, String> adultQuestionColumn;

    @FXML
    private TableColumn<QuestionEnfant, TypeEnfant> childrenTypeColumn;

    @FXML
    private TableColumn<QuestionAdult, TypeAdult> adultTypeColumn;


    public void initialize() {
        childrenQuestions = FXCollections.observableArrayList(anamneseModel.getQuestionsEnfant());
        adultQuestions = FXCollections.observableArrayList(anamneseModel.getQuestionsAdulte());

        // Set the cell value factory for each column
        childrenQuestionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        adultQuestionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        childrenTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeQuestion"));
        adultTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeQuestion"));

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
            try {
                anamneseModel.createChildQuestion(question, type);
                childrenQuestions.setAll(anamneseModel.getQuestionsEnfant());
            } catch (QuestionAlreadyExistException e) {
                Popups.showErrorMessage(e.getMessage());
            }
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
            try {
                anamneseModel.createAdultQuestion(question, type);
                adultQuestions.setAll(anamneseModel.getQuestionsAdulte());
            } catch (QuestionAlreadyExistException e) {
                Popups.showErrorMessage(e.getMessage());
            }
            adultQuestionField.clear();
            adultTypeChoiceBox.setValue(null);
        } else {
            Popups.showErrorMessage("Input Error", "Question field and type cannot be empty!");
        }
    }

    @FXML
    private void deleteChildrenQuestion() {
        QuestionEnfant selectedQuestion = childrenQuestionListView.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            try {
                anamneseModel.deleteQuestion(selectedQuestion);
                childrenQuestions.setAll(anamneseModel.getQuestionsEnfant());
            } catch (QuestionNotFoundException e) {
                Popups.showErrorMessage(e.getMessage());
            }
        } else {
            Popups.showErrorMessage("Selection Error", "Please select a question to delete!");
        }
    }

    @FXML
    private void deleteAdultQuestion() {
        QuestionAdult selectedQuestion = adultQuestionListView.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            try {
                anamneseModel.deleteQuestion(selectedQuestion);
                adultQuestions.setAll(anamneseModel.getQuestionsAdulte());
            } catch (QuestionNotFoundException e) {
                Popups.showErrorMessage(e.getMessage());
            }
        } else {
            Popups.showErrorMessage("Selection Error", "Please select a question to delete!");
        }
    }
   //todo: check out the update methods cause it's not working
    @FXML
    private void modifyChildrenQuestion() {
        QuestionEnfant selectedQuestion = childrenQuestionListView.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            String newQuestion = childrenQuestionField.getText().trim();
            TypeEnfant newType = childrenTypeChoiceBox.getValue();
            if (!newQuestion.isEmpty() && newType != null) {
                try {
                    anamneseModel.updateQuestion(new QuestionEnfant(newQuestion,newType));
                    childrenQuestions.setAll(anamneseModel.getQuestionsEnfant());
                } catch (QuestionNotFoundException e) {
                    Popups.showErrorMessage(e.getMessage());
                }
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
        QuestionAdult selectedQuestion = adultQuestionListView.getSelectionModel().getSelectedItem();
        if (selectedQuestion != null) {
            String newQuestion = adultQuestionField.getText().trim();
            TypeAdult newType = adultTypeChoiceBox.getValue();
            if (!newQuestion.isEmpty() && newType != null) {
                try {
                    anamneseModel.updateQuestion(new QuestionAdult( newQuestion, newType));
                    adultQuestions.setAll(anamneseModel.getQuestionsAdulte());
                } catch (QuestionNotFoundException  e) {
                    Popups.showErrorMessage(e.getMessage());
                }
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
