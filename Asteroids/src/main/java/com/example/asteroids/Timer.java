package com.example.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public record Timer(Stage stage, Scene scene, Ship ship, ArrayList<Asteroid> asteroids, ArrayList<Projectile> projectiles, Pane pane) {

    public Timer(Stage stage, Scene scene, Ship ship, ArrayList<Asteroid> asteroids, ArrayList<Projectile> projectiles, Pane pane) {
        this.scene = scene;
        this.stage=stage;
        this.ship = ship;
        this.asteroids = asteroids;
        this.projectiles=projectiles;
        this.pane=pane;
        startTimer();
    }


    public void startTimer() {
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        AtomicBoolean pressed= new AtomicBoolean(false);

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
                        alert.setTitle("GAME OVER!");
                        alert.setContentText("YOU SUCK!!!");
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
                    collisions.stream().forEach(collided -> {
                        asteroids.remove(collided);
                        pane.getChildren().remove(collided.getCharacter());
                    });
                    return true;
                }).toList();
                projectilesToRemove.forEach(projectile -> {
                    pane.getChildren().remove(projectile.getCharacter());
                    projectiles.remove(projectile);
                });

                Iterator<Projectile> iterator=projectiles.iterator();
                while(iterator.hasNext()){
                    Projectile projectile=iterator.next();
                    if(System.currentTimeMillis()-projectile.getStartTime()>2000){
                        pane.getChildren().remove(projectile.getCharacter());
                        iterator.remove();

                    }
                }
            }
        }.start();
    }
}