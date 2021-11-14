module com.example.asteroids {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.asteroids to javafx.fxml;
    exports com.example.asteroids;
}