package com.example.asteroids;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    public void start(Stage stage){
        new MainMenu(stage);
        //GameWindow gameWindow=new GameWindow(stage);
        //EndScreen endScreen=new EndScreen(stage);
        //DatabaseConnection.getPlayersName();
        //Leaderboard l=new Leaderboard(stage);
    }

}
