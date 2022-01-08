package com.example.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.*;
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

        keyHandler.initialize(scene);

        new AnimationTimer() {
            @Override
            public void handle(long now) {

                keyHandler.movement(ship);
                keyHandler.shoot(projectiles,ship,pane);

                if(ship.collisionWithAsteroid(asteroids,textScore)){
                    stage.close();
                    //MainMenu mainMenu = new MainMenu(new Stage());
                    EndScreen endScreen=new EndScreen(new Stage());
                    this.stop();
                }

                Asteroid.laserCollision(projectiles,asteroids,pane,points,textScore);
                Asteroid.addAsteroids(ship,asteroids,pane);

            }
        }.start();
    }
}