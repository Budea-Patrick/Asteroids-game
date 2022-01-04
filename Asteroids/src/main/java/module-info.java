module com.example.asteroids {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;

    opens com.example.asteroids to javafx.fxml;
    exports com.example.asteroids;
}