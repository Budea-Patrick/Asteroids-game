package com.example.asteroids;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Leaderboard {

    private ArrayList<Player> top10;
    private GridPane leaderboard;
    private Scene scene;
    private Label label;


    public Leaderboard(Stage stage) {
        top10=DatabaseConnection.getPlayersInfo();
        createGrid();
        styleStuff();
        scene=new Scene(leaderboard);
        scene.setFill(Color.RED);
        stage.setScene(scene);
        stage.setMaximized(true);
    }
    public void createGrid() {
        leaderboard=new GridPane();
        for(int i=0;i<10;i++){
            Label temp=new Label();
            temp.setText(top10.get(i).getName());
            temp.setFont(Font.font("Verdana", 20));
            temp.setTextFill(Color.BLACK);
            Label temp2=new Label();
            temp2.setText(top10.get(i).getScore());
            temp2.setFont(Font.font("Verdana", 20));
            temp2.setTextFill(Color.BLACK);
            leaderboard.add(temp, 0 , i + 1);
            leaderboard.add(temp2, 2, i + 1);
        }
    }
    public void styleStuff() {
        label = new Label("LEADERBOARD");
        label.setFont(Font.font("Verdana", 35));
        label.setTextFill(Color.BLACK);
        label.setAlignment(Pos.CENTER);
        label.setMinWidth(450);
        label.setMaxWidth(450);
        leaderboard.add(label, 1, 0);
        leaderboard.setVgap(20);
        leaderboard.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        leaderboard.setPrefSize(600,600);
        leaderboard.setAlignment(Pos.CENTER);
        leaderboard.getChildren().forEach(child -> {
            this.leaderboard.setHalignment(child, HPos.CENTER);
        });
    }
}