package com.example.patient_management_system;

import DataBases.*;
import Models.Anamnese.AnamneseModel;
import Models.Orthophoniste.OrthophonisteModel;
import Models.Patient.PatientModel;
import Models.Question.QuestionQpreuveModel;
import Models.Test.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {


    public static TestExerciceModel testexercice = new TestExerciceModel();
    public static QuestionQpreuveModel testquestions = new QuestionQpreuveModel();


    public static final OrthophonisteModel orthophonisteModel = new OrthophonisteModel(new OrthophonisteFileDataBase() );
    public static final PatientModel patientModel = new PatientModel( new PatientFileDB() );
    public static final AnamneseModel anamneseModel = new AnamneseModel(new AnamneseDBFile());
    public static final TestModel testModel = new TestModel(new TestFileDB());

    public static final String usersDirectoryName = "orthophoniste_directory";
    public static final String bilonDirectoryName = "bilons_directory";
    public static final String fichSuivisDirectoryName = "fichSuivis_directory";




    //Files Names for each Speech Therapist
    public static final String  TherapistDBuserName = "SpeachTherapistDBFile.dat";
    public static final String patientsDBFileName = "patientsFileDatabase.dat";
    public static final String categoryDbFileName = "rendez_vousDBFile.dat";
    public static final String boDBFileName = "bilonsDBFile.dat";
    public static final String observationCliniqueDBFileName = "observationCliniqueDBFile.dat";
    public static final String testsDBFileName = "testsDBFile.dat";
    public static final String questionsDBFileName = "questionsDBFile.dat";
    public static final String fichSuivisDBFileName = "fichSuivisDBFile.dat";
    public static final String objectifsDBFileName = "objectifsDBFile.dat";
    public static final String troubleDBFileName = "troubleDBFile.dat";
    public static final String anamneseDBFileName = "AnamneseFileDB.dat";


    public static String currentUserName = null;
    public static String currentPatientName = null;

    @Override
    public void start(Stage stage) throws IOException {




        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void init() throws IOException, ClassNotFoundException {
        if( (new File(usersDirectoryName, TherapistDBuserName)).exists() ){
            orthophonisteModel.load();
        }
    }


    @Override
    public void stop() throws IOException {
        orthophonisteModel.save();
        patientModel.save();
        testexercice.save();
        testquestions.save();
        anamneseModel.save();
    }
    public static void main(String[] args) {
        launch();
    }
}