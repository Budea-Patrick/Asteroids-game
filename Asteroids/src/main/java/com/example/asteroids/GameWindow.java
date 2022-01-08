package com.example.asteroids;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Random;


public class GameWindow {

    private Pane pane;
    private Entity ship;
    private Scene scene;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Projectile> projectiles;
    public static int width = 600;
    public static int height = 600;
    private TextScore textScore;


    public GameWindow(Stage stage) {
        createPane();
        createScene(pane);
        setStage(stage, scene);
        createShip();
        createAsteroids();
        createProjectiles();
        createScore();
        addElements(this.ship.getCharacter());
        addElements(asteroids);
        addElements(textScore.getText());
        Timer timer = new Timer(stage, scene, (Ship) ship, asteroids, projectiles, pane, textScore);
        stage.show();
    }

    public void createPane() {
        pane = new Pane();
        pane.setPrefSize(width, height);
    }

    public void createShip() {
        this.ship = new Ship(width / 2, height / 2);
    }

    public void createAsteroids() {

        asteroids = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random rnd = new Random();
            Asteroid asteroid = new Asteroid(rnd.nextInt(width / 3), rnd.nextInt(height));
            asteroids.add(asteroid);
        }
    }

    public void createProjectiles() {
        projectiles = new ArrayList<>();
    }

    public void createScene(Pane pane) {
        scene = new Scene(pane);
        scene.setFill(Color.YELLOW);
    }

    public void addElements(ArrayList<Asteroid> asteroids) {
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));
    }

    public void addElements(Node node) {
        pane.getChildren().add(node);
    }


    public void setStage(Stage stage, Scene scene) {
        stage.setScene(scene);
        //stage.setFullScreen(true);
        //stage.show();
    }

    public void createScore() {
        textScore = new TextScore();
    }

    /*
    public static double getHeight() {
        return pane.getHeight();
    }

    public static double getWidth() {
        return pane.getWidth();
    }*/
}