package com.example.asteroids;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Leaderboard {

    private ArrayList<Player> top10;
    private GridPane leaderboard;
    private Scene scene;
    private Label label;


    public Leaderboard(Stage stage) {
        top10=DatabaseConnection.getPlayersInfo();
        leaderboard=new GridPane();
        for(int i=0;i<10;i++){
            Label temp=new Label();
            temp.setText(top10.get(i).getName());
            Label temp2=new Label();
            temp2.setText(top10.get(i).getScore());
            leaderboard.add(temp, 0 , i);
            leaderboard.add(temp2, 2, i);
        }
        scene=new Scene(leaderboard);
        stage.setScene(scene);
        stage.show();
    }

}
