module com.example.carrace12 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.carrace12 to javafx.fxml;
    exports com.example.carrace12;
}