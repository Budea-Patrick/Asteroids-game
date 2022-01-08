package com.example.asteroids;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Entity {

    private final Polygon character;
    private Point2D movement;

    public Entity(Polygon character, int x, int y) {
        this.character = character;
        this.character.setFill(Color.BLACK);
        this.character.setStroke(Color.WHITE);
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.movement=new Point2D(0,0);
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D movement) {
        this.movement = movement;
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

        if(character.getTranslateX()<0){
            this.character.setTranslateX(this.character.getTranslateX() + 600);
        }
        if(character.getTranslateX()>600){
            this.character.setTranslateX(this.character.getTranslateX()%600);
        }
        if(this.character.getTranslateY()<0){
            this.character.setTranslateY(this.character.getTranslateY()+600);
        }
        if(this.character.getTranslateY()>600){
            this.character.setTranslateY(this.character.getTranslateY()%600);
        }
    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(character.getRotate()));
        double changeY = Math.sin(Math.toRadians(character.getRotate()));
        changeX=changeX*0.05;
        changeY=changeY*0.05;
        movement = movement.add(changeX, changeY);
    }
    
    public boolean collision(Entity character){
        Shape collisionArea=Shape.intersect(this.character, character.getCharacter());
        return collisionArea.getBoundsInLocal().getWidth()!=-1;
    }
}
