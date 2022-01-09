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
import javafx.scene.input.MouseEvent;

public class EndScreen {

    private GridPane pane;
    private Scene scene;
    private Label userName;
    private TextField userTextField;
    private Button btn;
    private HBox hbBtn;
    private Button lbBtn;
    private Button playBtn;

    public EndScreen(Stage stage) {
        createPane();
        createScene(stage);
        createSceneTitle();
        //createLabel();
        createTextField();
        createButton();
        createAction(stage);
    }

    public void createPane() {
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
        sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL,60));
        sceneTitle.setFill(Color.BLACK);
        pane.add(sceneTitle, 1, 0);
    }

    public void createLabel()
    {
        userName=new Label("YES");
        //userName.setMinHeight(50);
        //userName.setMinWidth(50);
        //userName.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
        //userName.setTextFill(Color.BLACK);
        pane.add(userName, 1, 1);
    }

    public void createTextField()
    {
        this.userTextField=new TextField();
        userTextField.setPromptText("Username");
        this.userTextField.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.userTextField.setFont(Font.font("Verdana", 20));
        this.userTextField.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.add(userTextField, 1, 2);

    }

    public void createButton()
    {
        lbBtn = new Button("Leaderboard");
        playBtn = new Button("Play Again");
        btn=new Button("Enter");
        btn.setPrefSize(175, 20);
        lbBtn.setPrefSize(175, 20);
        playBtn.setPrefSize(175, 20);
        hbBtn=new HBox(10);
        hbBtn.setAlignment(Pos.CENTER_LEFT);
        hbBtn.getChildren().add(btn);
        this.btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.btn.setFont(Font.font("Verdana", 20));
        this.btn.setTextFill(Color.BLACK);
        this.btn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.lbBtn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.lbBtn.setFont(Font.font("Verdana", 20));
        this.lbBtn.setTextFill(Color.BLACK);
        this.lbBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.playBtn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.playBtn.setFont(Font.font("Verdana", 20));
        this.playBtn.setTextFill(Color.BLACK);
        this.playBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.add(hbBtn, 2, 2);
        pane.add(new Label(), 1, 3);
        pane.add(new Label(), 2, 3);
        pane.add(playBtn, 1 ,4);
        pane.add(lbBtn, 2, 4);
    }
    public void createAction(Stage stage)
    {
       btn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=userTextField.getText();
                if(!name.isEmpty()){
                    DatabaseConnection.updateDB(name);
                    userTextField.clear();
                    userTextField.setPromptText("Score Submitted!");
                    btn.setVisible(false);
                    int i = GridPane.getRowIndex(lbBtn);
                    GridPane.setRowIndex(lbBtn, 2);
                }
                //new Leaderboard(stage)
            }
        });
        this.btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setTextFill(Color.WHITE);
                btn.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        this.btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setTextFill(Color.BLACK);
                btn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        this.lbBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lbBtn.setTextFill(Color.WHITE);
                lbBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        this.lbBtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lbBtn.setTextFill(Color.BLACK);
                lbBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        this.playBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playBtn.setTextFill(Color.WHITE);
                playBtn.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        this.playBtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playBtn.setTextFill(Color.BLACK);
                playBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        lbBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //GameWindow gameWindow=new GameWindow(new Stage());
                new Leaderboard(stage);
            }
        });
        playBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //GameWindow gameWindow=new GameWindow(new Stage());
                stage.close();
                new GameWindow(stage);
            }
        });
    }
}
