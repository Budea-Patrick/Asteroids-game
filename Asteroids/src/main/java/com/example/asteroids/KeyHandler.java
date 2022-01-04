package com.example.asteroids;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class KeyHandler {

    private final Map<KeyCode,Boolean> pressedKeys;
    private final AtomicBoolean pressed;

    public KeyHandler() {
        this.pressedKeys = new HashMap<>();
        this.pressed = new AtomicBoolean();
    }

    public void initialize(Scene scene)
    {
        scene.setOnKeyPressed(keyEvent -> {
            pressedKeys.put(keyEvent.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(keyEvent -> {
            pressedKeys.put(keyEvent.getCode(), Boolean.FALSE);
            pressed.set(false);
        });
    }

    public void movement(Ship ship)
    {
        if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
            ship.turnLeft();
        }
        if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
            ship.turnRight();
        }
        if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
            ship.accelerate();
        }
        ship.move();
    }

    public void shoot(ArrayList<Projectile> projectiles, Ship ship, Pane pane)
    {
        if(pressedKeys.getOrDefault(KeyCode.SPACE, false) && projectiles.size()<10 && !pressed.get()){
            ship.shoot(projectiles,pane);
            pressed.set(true);
        }
    }

}
