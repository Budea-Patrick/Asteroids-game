package com.example.asteroids;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;


public class GameWindow {

    private static Pane pane;
    private Entity ship;
    private ArrayList<Asteroid> asteroids;

    public GameWindow(Stage stage) {
        createPane();
        Scene scene=createScene(pane);
        createShip();
        createAsteroids();
        addElements(this.ship.getCharacter());
        addElements(asteroids);
        setStage(stage, scene);
        Timer timer = new Timer(stage, scene, (Ship) ship, asteroids);
    }

    public void createPane(){
        pane=new Pane();
        int height = 600;
        int width = 400;
        pane.setPrefSize(height, width);
    }

    public void createShip(){
        this.ship = new Ship(150,100);
    }
    public void createAsteroids(){

        asteroids=new ArrayList<>();
        for(int i=0;i<5;i++){
            Random rnd= new Random();
            Asteroid asteroid=new Asteroid(rnd.nextInt(100), rnd.nextInt(100));
            asteroids.add(asteroid);
        }
    }

    public Scene createScene(Pane pane){
        Scene scene=new Scene(pane);
        scene.setFill(Color.BLACK);
        return scene;
    }

    public void addElements(ArrayList <Asteroid> asteroids){
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));
    }

    public void addElements(Node node){
        pane.getChildren().add(node);
    }

    public void setStage(Stage stage, Scene scene){
        stage.setScene(scene);
        ///stage.setFullScreen(true);
        stage.show();
    }

    public static double getHeight() {
        return pane.getHeight();
    }

    public static double getWidth() {
        return pane.getWidth();
    }
}