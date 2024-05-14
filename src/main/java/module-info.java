module com.example.patient_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;

    opens com.example.patient_management_system to javafx.fxml;
    opens Controllers.OrthophonisteControllers to javafx.fxml;


    exports com.example.patient_management_system;



    //Exporting models
    exports Models.Orthophoniste;


    //Exporting databases
    exports DataBases;


    //Exporting Controllers
    exports Controllers.OrthophonisteControllers;



    //Exporting Exceptions
    exports Exceptions;


    //Exporting Utils
    exports Utils;
}