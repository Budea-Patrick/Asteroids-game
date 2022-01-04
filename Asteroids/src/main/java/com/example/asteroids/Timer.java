package com.example.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
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

public record Timer(Stage stage, Scene scene, Ship ship, ArrayList<Asteroid> asteroids, ArrayList<Projectile> projectiles, Pane pane, Text text) {

    public Timer(Stage stage, Scene scene, Ship ship, ArrayList<Asteroid> asteroids, ArrayList<Projectile> projectiles, Pane pane, Text text) {
        this.scene = scene;
        this.stage=stage;
        this.ship = ship;
        this.asteroids = asteroids;
        this.projectiles=projectiles;
        this.pane=pane;
        this.text=text;
        startTimer();
    }


    public void startTimer() {
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        AtomicBoolean pressed= new AtomicBoolean(false);
        AtomicInteger points=new AtomicInteger();

        scene.setOnKeyPressed(keyEvent -> {
            pressedKeys.put(keyEvent.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(keyEvent -> {
            pressedKeys.put(keyEvent.getCode(), Boolean.FALSE);
            pressed.set(false);
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                }
                if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                }
                if(pressedKeys.getOrDefault(KeyCode.SPACE, false) && projectiles.size()<10 && !pressed.get()){
                    Projectile projectile=new Projectile((int)ship.getCharacter().getTranslateX(),(int)ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectiles.add(projectile);
                    projectile.accelerate();
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    pane.getChildren().add(projectile.getCharacter());
                    pressed.set(true);
                }
                ship.move();
                asteroids.forEach(asteroid -> asteroid.move());
                asteroids.forEach(asteroid -> {
                    if(ship.collision(asteroid)){
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        /*
                        alert.setTitle("GAME OVER!");
                        alert.setContentText("YOU SUCK!!!");
                        alert.show();*/
                        DatabaseConnection connectNow=new DatabaseConnection();
                        Connection connectionDB=connectNow.getConnection();
                        String connectQuery="insert  into asteroidsscore (score) values ('" + text.getText() +"');";
                        try{
                            Statement statement=connectionDB.createStatement();
                            statement.executeUpdate("insert into asteroidsscore (score) values ('" + text.getText() +"');");
                            ResultSet queryOutput=statement.executeQuery(connectQuery);
                            /*
                            while(queryOutput.next()){
                                alert.setContentText(queryOutput.getString("name"));
                            }*/
                        }
                        catch (Exception e){

                        }
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
                    text.setText("Points: "+points.addAndGet(100));
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