module com.example.rc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rc to javafx.fxml;
    exports com.example.rc;
}