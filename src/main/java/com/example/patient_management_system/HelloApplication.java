package com.example.patient_management_system;

import DataBases.*;
import Models.Anamnese.AnamneseModel;
import Models.Bilan.BilonModel;
import Models.DossierPatient.DossierPatientModel;
import Models.Objectif.ObjectifModel;
import Models.Orthophoniste.OrthophonisteModel;
import Models.Patient.PatientModel;
import Models.Question.QuestionQpreuveModel;
import Models.RendezVous.RendezVousModel;
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
import java.util.ArrayList;

public class HelloApplication extends Application {


    public static   TestExerciceModel testexercice = new TestExerciceModel();
    public static   QuestionQpreuveModel testquestions = new QuestionQpreuveModel();


    public static  OrthophonisteModel orthophonisteModel = new OrthophonisteModel(new OrthophonisteFileDataBase() );
    public static  PatientModel patientModel = new PatientModel( new PatientFileDB() );
    public static  AnamneseModel anamneseModel = new AnamneseModel(new AnamneseDBFile());
    public static  DossierPatientModel dossierPatientModel = new DossierPatientModel();

//    public static final RendezVousModel rendezvousModel = new RendezVousModel(new RendezVousFileDB());
//    public static final ObjectifModel fichesuivi = new ObjectifModel(new objectifFileDB());
    public static final BilonModel bilonModel = new BilonModel();

    public static  TestModel testModel = new TestModel(new TestFileDB());

    public static final String usersDirectoryName = "orthophoniste_directory";
    public static final String dossierPatientFileName = "dossiersPatientFileDB.dat";
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
    public static final String patientFolderName = "dossierPatient.dat";


    public static String currentUserName ;
    public static String currentPatientName ;

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
        currentPatientName=null;
        currentUserName=null;
        if( (new File(usersDirectoryName, TherapistDBuserName)).exists() ){
            orthophonisteModel.load();
        }
    }


    @Override
    public void stop() throws IOException {
        orthophonisteModel.save();
        if(currentUserName!=null){
            patientModel.save();
            testquestions.save();
            testexercice.save();
            anamneseModel.save();
            dossierPatientModel.save();


        }

    }
    public static void main(String[] args) {
        launch();
    }
}