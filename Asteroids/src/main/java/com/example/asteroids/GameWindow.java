package com.example.asteroids;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class GameWindow {

    private Pane pane;
    private Entity ship;
    private Entity asteroid;

    public GameWindow(Stage stage) {
        createPane();
        Scene scene=createScene(pane);
        createShip();
        createAsteroid();
        addElements(this.ship.getCharacter());
        addElements(this.asteroid.getCharacter());
        stage.setScene(scene);
        stage.show();
        Timer timer = new Timer(scene, (Ship) ship, (Asteroid) asteroid);

    }

    public void createPane(){
        this.pane=new Pane();
        this.pane.setPrefSize(600,400);
    }

    public void createShip(){
        this.ship = new Ship(150,100);
    }
    public void createAsteroid(){
        this.asteroid=new Asteroid(50,50);
    }

    public Scene createScene(Pane pane){
        return new Scene(pane);
    }

    public void addElements(Node node){
        this.pane.getChildren().add(node);
    }
}