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
    private ArrayList<Projectile> projectiles;
    public static int width=600;
    public static int height=600;

    public GameWindow(Stage stage) {
        createPane();
        Scene scene=createScene(pane);
        createShip();
        createAsteroids();
        createProjectiles();
        addElements(this.ship.getCharacter());
        addElements(asteroids);
        setStage(stage, scene);
        Timer timer = new Timer(stage, scene, (Ship) ship, asteroids, projectiles, pane);
    }

    public void createPane(){
        pane=new Pane();
        pane.setPrefSize(width, height);
    }

    public void createShip(){
        this.ship = new Ship(width/2,height/2);
    }
    public void createAsteroids(){

        asteroids=new ArrayList<>();
        for(int i=0;i<5;i++){
            Random rnd= new Random();
            Asteroid asteroid=new Asteroid(rnd.nextInt(width/3), rnd.nextInt(height));
            asteroids.add(asteroid);
        }
    }
    public void createProjectiles(){
        projectiles=new ArrayList<>();
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
        //stage.setFullScreen(true);
        stage.show();
    }


    public static double getHeight() {
        return pane.getHeight();
    }

    public static double getWidth() {
        return pane.getWidth();
    }
}