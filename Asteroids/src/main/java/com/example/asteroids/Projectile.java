package com.example.asteroids;

import javafx.scene.shape.Polygon;

public class Projectile extends Entity {

    private final long startTime;

    public Projectile(int x, int y) {
        super(new Polygon(2,-2,2,2,-2,2,-2,-2),x,y);
        startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }


}
