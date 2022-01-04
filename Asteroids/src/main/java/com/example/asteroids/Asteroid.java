package com.example.asteroids;

import javafx.scene.shape.Polygon;

import java.util.Random;

public class Asteroid extends Entity {

    private final double rotation;

    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);

        Random rand=new Random();
        super.getCharacter().setRotate(rand.nextInt(360));
        int acceleration=1+rand.nextInt(30);
        for(int i=0;i<acceleration;i++){
            accelerate();
        }
        this.rotation=0.5-rand.nextDouble();
    }

    @Override
    public void move(){
        super.move();
        super.getCharacter().setRotate(super.getCharacter().getRotate()+rotation);
    }
}
