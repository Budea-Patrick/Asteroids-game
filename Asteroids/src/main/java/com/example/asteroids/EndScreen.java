package com.example.asteroids;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndScreen {

    private StackPane pane;
    private Scene scene;
    private Label userName;
    private TextField userTextField;
    private Button btn;
    private HBox hbBtn;

    public EndScreen(Stage stage) {
        createPane();
        createScene(stage);
        //createSceneTitle();
        //createLabel();
        createTextField();
        createButton();
        createAction(stage);
    }

    public void createPane() {
        pane = new StackPane();
        pane.setPrefSize(600,600);
    }

    public void createScene(Stage stage){
        scene=new Scene(this.pane);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    public void createSceneTitle()
    {
        Text sceneTitle = new Text("YOU LOST!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,60));
        sceneTitle.setFill(Color.WHITE);
        pane.getChildren().add(sceneTitle);
        StackPane.setAlignment(sceneTitle,Pos.CENTER);
    }

    public void createLabel()
    {
        userName=new Label("YES");
        //userName.setMinHeight(50);
        //userName.setMinWidth(50);
        //userName.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
        //userName.setTextFill(Color.BLACK);
        StackPane.setAlignment(userName,Pos.TOP_CENTER);
        pane.getChildren().add(userName);
    }

    public void createTextField()
    {
        userTextField=new TextField();
        StackPane.setAlignment(userTextField,Pos.CENTER);
        pane.getChildren().add(userTextField);
    }

    public void createButton()
    {
        btn=new Button("Enter");
        hbBtn=new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        pane.getChildren().add(hbBtn);
    }

    public void createAction(Stage stage)
    {
        btn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=userTextField.getText();
                if(!name.isEmpty()){
                    DatabaseConnection.updateDB(name);
                    stage.close();
                }
                //new Leaderboard(stage)
            }
        });
    }
}
