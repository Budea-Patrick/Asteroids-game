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
        this.movement=new Point2D(1,1);
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
            this.character.setTranslateX(this.character.getTranslateX() + GameWindow.getWidth());
        }
        if(character.getTranslateX()>GameWindow.getWidth()){
            this.character.setTranslateX(this.character.getTranslateX()%GameWindow.getWidth());
        }
        if(this.character.getTranslateY()<0){
            this.character.setTranslateY(this.character.getTranslateY()+GameWindow.getHeight());
        }
        if(this.character.getTranslateY()>GameWindow.getHeight()){
            this.character.setTranslateY(this.character.getTranslateY()%GameWindow.getHeight());
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
