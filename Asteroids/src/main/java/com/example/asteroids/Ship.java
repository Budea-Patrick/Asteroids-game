package com.example.asteroids;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Ship extends Entity {


    public Ship(int x, int y) {
        super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);
    }

    public void shoot(ArrayList<Projectile> projectiles, Pane pane) {
        Projectile projectile = new Projectile((int) this.getCharacter().getTranslateX(), (int) this.getCharacter().getTranslateY());
        projectile.getCharacter().setRotate(this.getCharacter().getRotate());
        projectiles.add(projectile);
        projectile.accelerate();
        projectile.setMovement(projectile.getMovement().normalize().multiply(3));
        pane.getChildren().add(projectile.getCharacter());
    }

    public boolean collisionWithAsteroid(ArrayList<Asteroid> asteroids) {
        AtomicBoolean checkForCollision= new AtomicBoolean(false);
        asteroids.forEach(Asteroid::move);
        asteroids.forEach(asteroid -> {
            if (this.collision(asteroid)) {
                checkForCollision.set(true);
            }
        });
        return checkForCollision.get();
    }
}