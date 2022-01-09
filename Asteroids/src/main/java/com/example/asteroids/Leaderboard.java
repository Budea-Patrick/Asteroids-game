package com.example.asteroids;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Leaderboard {

    private ArrayList<Player> top10;
    private GridPane leaderboard;
    private Button mainBtn;
    private Scene scene;
    private Label label;


    public Leaderboard(Stage stage) {
        stage.setTitle("Asteroids");
        top10=DatabaseConnection.getPlayersInfo();
        createGrid();
        styleStuff(stage);
        scene=new Scene(leaderboard);
        scene.setFill(Color.RED);
        stage.setScene(scene);
    }
    public void createGrid() {
        leaderboard=new GridPane();
        for(int i=0;i<5;i++){
            Label temp=new Label();
            temp.setText(top10.get(i).getName());
            temp.setFont(Font.font("Verdana", 20));
            temp.setTextFill(Color.BLACK);
            temp.setMinWidth(150);
            temp.setAlignment(Pos.CENTER);
            Label temp2=new Label();
            temp2.setText(top10.get(i).getScore());
            temp2.setFont(Font.font("Verdana", 20));
            temp2.setTextFill(Color.BLACK);
            temp2.setMinWidth(150);
            temp2.setAlignment(Pos.CENTER);
            leaderboard.add(temp, 0 , i + 1);
            leaderboard.add(temp2, 2, i + 1);
        }
    }
    public void styleStuff(Stage stage) {
        mainBtn = new Button("Main Menu");
        label = new Label("LEADERBOARD");
        label.setFont(Font.font("Verdana", 25));
        label.setTextFill(Color.BLACK);
        label.setAlignment(Pos.CENTER);
        label.setMinWidth(450);
        label.setMaxWidth(450);
        this.mainBtn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.mainBtn.setFont(Font.font("Verdana", 20));
        this.mainBtn.setTextFill(Color.BLACK);
        this.mainBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        leaderboard.add(label, 1, 0);
        leaderboard.add(mainBtn, 1, 6);
        leaderboard.setVgap(20);
        leaderboard.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        leaderboard.setPrefSize(600,600);
        leaderboard.setAlignment(Pos.CENTER);
        leaderboard.setVgap(15);
        leaderboard.getChildren().forEach(child -> {
            this.leaderboard.setHalignment(child, HPos.CENTER);
        });
        mainBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        stage.close();
                        new MainMenu(new Stage());
                    }
                });
            }
        });
        mainBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                mainBtn.setTextFill(Color.WHITE);
                mainBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        mainBtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                mainBtn.setTextFill(Color.BLACK);
                mainBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
    }
}