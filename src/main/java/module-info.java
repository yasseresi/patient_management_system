module com.example.patient_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;

    opens com.example.patient_management_system to javafx.fxml;
    opens Controllers.OrthophonisteControllers to javafx.fxml;
    opens Controllers.HomeController to javafx.fxml;
    opens Controllers.ProfileControllers to javafx.fxml;
    opens Controllers.RendezVousController to javafx.fxml;
    opens Controllers.StatistiqueControllers to javafx.fxml;

    opens Models.Orthophoniste to javafx.fxml;
    opens DataBases to javafx.fxml;
    opens Exceptions to javafx.fxml;
    opens Utils to javafx.fxml;



    exports com.example.patient_management_system;



    //Exporting models
    exports Models.Orthophoniste;


    //Exporting databases
    exports DataBases;


    //Exporting Controllers
    exports Controllers.OrthophonisteControllers;
    exports Controllers.HomeController;



    //Exporting Exceptions
    exports Exceptions;


    //Exporting Utils
    exports Utils;
}