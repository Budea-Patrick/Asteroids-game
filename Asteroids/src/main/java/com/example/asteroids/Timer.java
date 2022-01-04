package com.example.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public record Timer(Stage stage, Scene scene, Ship ship, ArrayList<Asteroid> asteroids, ArrayList<Projectile> projectiles, Pane pane, TextScore textScore) {

    public Timer(Stage stage, Scene scene, Ship ship, ArrayList<Asteroid> asteroids, ArrayList<Projectile> projectiles, Pane pane, TextScore textScore) {
        this.scene = scene;
        this.stage=stage;
        this.ship = ship;
        this.asteroids = asteroids;
        this.projectiles=projectiles;
        this.pane=pane;
        this.textScore=textScore;
        startTimer();
    }


    public void startTimer() {

        AtomicInteger points=new AtomicInteger();
        KeyHandler keyHandler=new KeyHandler();
        DatabaseConnection databaseConnection=new DatabaseConnection();
        keyHandler.initialize(scene);

        new AnimationTimer() {
            @Override
            public void handle(long now) {

                keyHandler.movement(ship);
                keyHandler.shoot(projectiles,ship,pane);

                asteroids.forEach(asteroid -> asteroid.move());
                asteroids.forEach(asteroid -> {
                    if(ship.collision(asteroid)){
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        databaseConnection.updateDB(textScore);
                        /*
                        alert.setTitle("GAME OVER!");
                        alert.setContentText("YOU SUCK!!!");
                        alert.show();*/

                        alert.show();
                        this.stop();
                        stage.close();
                    }
                });
                projectiles.forEach(projectile -> projectile.move());
                List<Projectile> projectilesToRemove = projectiles.stream().filter(projectile -> {
                    List<Asteroid> collisions = asteroids.stream()
                            .filter(asteroid -> asteroid.collision(projectile)).toList();
                    if (collisions.isEmpty()) {
                        return false;
                    }
                    collisions.forEach(collided -> {
                        asteroids.remove(collided);
                        pane.getChildren().remove(collided.getCharacter());
                    });
                    return true;
                }).toList();
                projectilesToRemove.forEach(projectile -> {
                    pane.getChildren().remove(projectile.getCharacter());
                    projectiles.remove(projectile);
                    textScore.getText().setText("Points: "+points.addAndGet(100));
                });

                Iterator<Projectile> iterator=projectiles.iterator();
                while(iterator.hasNext()){
                    Projectile projectile=iterator.next();
                    if(System.currentTimeMillis()-projectile.getStartTime()>2000){
                        pane.getChildren().remove(projectile.getCharacter());
                        iterator.remove();

                    }
                }

                if(Math.random()<0.010){
                    Asteroid asteroid=new Asteroid(GameWindow.width, GameWindow.height);
                    if(!asteroid.collision(ship)){
                        asteroids.add(asteroid);
                        pane.getChildren().add(asteroid.getCharacter());
                    }
                }

            }
        }.start();
    }
}