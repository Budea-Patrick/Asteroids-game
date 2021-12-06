package com.example.asteroids;

import javafx.scene.shape.Polygon;

public class Asteroid extends Entity {

    public Asteroid(int x, int y) {
        super(new Polygon(20,-20,10,20,-5,20,-20,-20), x, y);
    }
}
