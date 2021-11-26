package com.example.asteroids;

import javafx.scene.shape.Polygon;

public class Ship{

    private Polygon parallelogram;


    public Ship(Polygon parallelogram) {
        this.parallelogram = parallelogram;
    }

    public Polygon getParallelogram() {
        return parallelogram;
    }

    public void setParallelogram(Polygon parallelogram) {
        this.parallelogram = parallelogram;
    }
}
