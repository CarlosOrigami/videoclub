module com.example.videoclub {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.videoclub.controller to javafx.fxml;
    exports com.example.videoclub.controller to javafx.fxml;
    exports com.example.videoclub;
    opens com.example.videoclub;
}
