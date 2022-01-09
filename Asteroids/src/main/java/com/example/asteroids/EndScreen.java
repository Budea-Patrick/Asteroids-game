package com.example.asteroids;

import com.mysql.cj.util.StringUtils;
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

    public EndScreen(Stage stage) {
        createPane();
        createScene(stage);
        createSceneTitle();
        createTextField();
        createButton();
        createAction(stage);
    }

    public void createPane() {
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setPrefSize(600, 600);
    }

    public void createScene(Stage stage) {
        scene = new Scene(this.pane);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    public void createSceneTitle() {
        Text sceneTitle = new Text("YOU LOST!");
        sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 60));
        sceneTitle.setFill(Color.BLACK);
        pane.add(sceneTitle, 1, 0);
    }


    public void createTextField() {
        this.userTextField = new TextField();
        userTextField.setPromptText("Username");
        this.userTextField.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.userTextField.setFont(Font.font("Verdana", 20));
        this.userTextField.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.add(userTextField, 1, 2);

    }

    public void createButton() {
        btn = new Button("Enter");
        hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        this.btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.btn.setFont(Font.font("Verdana", 20));
        this.btn.setTextFill(Color.BLACK);
        this.btn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.add(hbBtn, 2, 2);
    }

    public void createAction(Stage stage) {
        btn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userTextField.getText();
                if (!StringUtils.isEmptyOrWhitespaceOnly(name)) {
                    DatabaseConnection.updateDB(name);
                    stage.close();
                    new Leaderboard(stage);
                }
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
    }
}
