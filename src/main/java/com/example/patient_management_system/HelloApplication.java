package com.example.patient_management_system;

import DataBases.OrthophonisteFileDataBase;
import DataBases.OrthophonistheDataBase;
import Models.Orthophoniste.OrthophonisteModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {



    public static final OrthophonisteModel orthophonisteModel = new OrthophonisteModel(new OrthophonisteFileDataBase() );


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


    public static String currentUserName = null;

    @Override
    public void start(Stage stage) throws IOException {




        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void stop() throws IOException {
        orthophonisteModel.save();
    }
    public static void main(String[] args) {
        launch();
    }
}