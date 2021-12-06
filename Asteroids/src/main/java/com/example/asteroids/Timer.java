package com.example.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public record Timer(Scene scene, Ship ship, Asteroid asteroid) {

    public Timer(Scene scene, Ship ship, Asteroid asteroid) {
        this.scene = scene;
        this.ship = ship;
        this.asteroid = asteroid;
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
                asteroid.turnRight();
                //asteroid.turnRight();
                asteroid.move();
                if(ship.collision(asteroid)){
                    stop();
                }
            }

        }.start();
    }
}