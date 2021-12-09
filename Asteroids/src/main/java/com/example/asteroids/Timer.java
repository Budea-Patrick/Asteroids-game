package com.example.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public record Timer(Stage stage, Scene scene, Ship ship, ArrayList<Asteroid> asteroids) {

    public Timer(Stage stage, Scene scene, Ship ship, ArrayList<Asteroid> asteroids) {
        this.scene = scene;
        this.stage=stage;
        this.ship = ship;
        this.asteroids = asteroids;
        startTimer();
    }

    public void startTimer() {
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        scene.setOnKeyPressed(keyEvent -> {
            pressedKeys.put(keyEvent.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(keyEvent -> {
            pressedKeys.put(keyEvent.getCode(), Boolean.FALSE);
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
            }
        }.start();
    }
}