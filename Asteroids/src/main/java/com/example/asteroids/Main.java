package com.example.asteroids;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
    public void start(Stage stage){
        new MainMenu(stage);
        //GameWindow gameWindow=new GameWindow(stage);
        //EndScreen endScreen=new EndScreen(stage);






        /*
        Button btn=new Button("Enter");
        HBox hbBtn=new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,1,4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        btn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                actionTarget.setFill(Color.BLACK);
                actionTarget.setText("FUck you");
            }
        });
        */

    }
}
