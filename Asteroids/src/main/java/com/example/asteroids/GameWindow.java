package com.example.asteroids;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class GameWindow {

    private Pane pane;
    private Ship ship;

    public GameWindow(Stage stage) {
        createShip();
        createPane();
        addElements(this.ship.getParallelogram());
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.LEFT){
                ship.getParallelogram().setRotate(ship.getParallelogram().getRotate()-5);
            }
            if(keyEvent.getCode()==KeyCode.RIGHT){
                ship.getParallelogram().setRotate(ship.getParallelogram().getRotate()+5);
            }
            if(keyEvent.getCode()==KeyCode.ESCAPE){
                stage.close();
            }
        });
    }
    public void createPane(){
        this.pane=new Pane();
        this.pane.setPrefSize(600,400);
    }
    public void createShip(){
        this.ship = new Ship(new Polygon(0, 0, 100, 0, 100, 50, 0, 50));
        this.ship.getParallelogram().setTranslateX(300);
        this.ship.getParallelogram().setTranslateY(200);
    }
    public void addElements(Node node){
        this.pane.getChildren().add(node);
    }
}
