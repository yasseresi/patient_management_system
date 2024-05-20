module com.example.patient_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;
    requires java.desktop;

    opens com.example.patient_management_system to javafx.fxml;
    opens Controllers.OrthophonisteControllers to javafx.fxml;
    opens Controllers.HomeController to javafx.fxml;
    opens Controllers.ProfileControllers to javafx.fxml;
    opens Controllers.RendezVousController to javafx.fxml;
    opens Controllers.StatistiqueControllers to javafx.fxml;
    opens Controllers.DossierPatientsControllers to javafx.fxml;
    opens Controllers.Anamnese to javafx.fxml;

    opens Models.Orthophoniste to javafx.fxml;
    opens DataBases to javafx.fxml;
    opens Exceptions to javafx.fxml;
    opens Utils to javafx.fxml;



    exports com.example.patient_management_system;



    //Exporting models
    exports Models.Orthophoniste;
    exports Models.Patient;
    exports Models.Objectif;
    exports Models.RendezVous;
    exports Models.Anamnese;
    exports Models.ObservationsCliniques;
    exports Models.Question;



    //Exporting databases
    exports DataBases;


    //Exporting Controllers
    exports Controllers.OrthophonisteControllers;
    exports Controllers.HomeController;
    exports Controllers.ProfileControllers;
    exports Controllers.RendezVousController;
    exports Controllers.StatistiqueControllers;
    exports Controllers.DossierPatientsControllers;
    exports Controllers.Anamnese;



    //Exporting Exceptions
    exports Exceptions;


    //Exporting Utils
    exports Utils;
}