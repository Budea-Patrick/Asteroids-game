package com.example.asteroids;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Ship {

    private final Polygon character;
    private Point2D movement;

    public Ship(int x, int y) {
        Polygon parallelogram = new Polygon(-5,-5,10,0,-5,5);
        parallelogram.setTranslateX(x);
        parallelogram.setTranslateY(y);
        this.character = parallelogram;
        movement = new Point2D(0, 0);
    }

    public Polygon getCharacter() {
        return character;
    }

    public void turnLeft() {
        character.setRotate(character.getRotate() - 5);
    }

    public void turnRight() {
        character.setRotate(character.getRotate() + 5);
    }

    public void move() {
        character.setTranslateX(character.getTranslateX() + movement.getX());
        character.setTranslateY(character.getTranslateY() + movement.getY());
    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(character.getRotate()));
        double changeY = Math.sin(Math.toRadians(character.getRotate()));
        changeX=changeX*0.1;
        changeY=changeY*0.1;
        movement = movement.add(changeX, changeY);
    }
}