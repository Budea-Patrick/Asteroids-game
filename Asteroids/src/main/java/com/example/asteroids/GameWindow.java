package com.example.asteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class GameWindow extends Application {

    @Override
    public void start(Stage stage) {
        Pane pane=new Pane();
        pane.setPrefSize(500,400);
        pane.getChildren().add(new Circle(30, 50, 10));
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
