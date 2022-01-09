package com.example.asteroids;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Asteroid extends Entity {

    private final double rotation;

    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);

        Random rand = new Random();
        super.getCharacter().setRotate(rand.nextInt(360));
        int acceleration = 1 + rand.nextInt(90);
        for (int i = 0; i < acceleration; i++) {
            accelerate();
        }
        this.rotation = 0.5 - rand.nextDouble();
    }

    @Override
    public void move() {
        super.move();
        super.getCharacter().setRotate(super.getCharacter().getRotate() + rotation);
    }

    public static void laserCollision(ArrayList<Projectile> projectiles, ArrayList<Asteroid> asteroids, Pane pane, AtomicInteger points, TextScore textScore) {
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
            textScore.getText().setText("Points: " + points.addAndGet(100));
        });

        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            if (System.currentTimeMillis() - projectile.getStartTime() > 2000) {
                pane.getChildren().remove(projectile.getCharacter());
                iterator.remove();
            }
        }
    }

    public static void addAsteroids(Ship ship, ArrayList<Asteroid> asteroids, Pane pane) {
        if (Math.random() < 0.020) {
            Asteroid asteroid = new Asteroid(GameWindow.width, GameWindow.height);
            if (!asteroid.collision(ship)) {
                asteroids.add(asteroid);
                pane.getChildren().add(asteroid.getCharacter());
            }
        }
    }
}
