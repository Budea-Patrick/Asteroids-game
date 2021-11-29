package com.example.asteroids;


import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.security.Key;


public class GameWindow {

    private Pane pane;
    private Ship ship;
    private Timer timer;

    public GameWindow(Stage stage) {
        createPane();
        Scene scene=createScene(pane);
        createShip(scene);
        addElements(this.ship.getCharacter());
        stage.setScene(scene);
        stage.show();
        timer=new Timer(scene,ship);
    }

    public void createPane(){
        this.pane=new Pane();
        this.pane.setPrefSize(600,400);
    }

    public void createShip(Scene scene){
        this.ship = new Ship(150,100);
    }

    public Scene createScene(Pane pane){
        return new Scene(pane);
    }

    public void addElements(Node node){
        this.pane.getChildren().add(node);
    }
}
