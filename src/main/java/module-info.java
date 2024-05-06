module com.example.patient_management_system {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.patient_management_system to javafx.fxml;
    exports com.example.patient_management_system;
}