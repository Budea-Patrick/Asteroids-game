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

public class MainMenu {
    private Label titleScreen;
    private Button startBtn;
    private Button lbBtn;
    private Button exit;
    private GridPane menu;
    private StackPane btnContainer;
    private Scene scene;

    public MainMenu(Stage stage) {
        stage.setTitle("Asteroids");
        stage.setResizable(false);
        createGridPane();
        createTextField();
        createButtons(stage);
        createScene(stage);
    }

    public void createGridPane() {
        this.menu = new GridPane();
        this.menu.setVgap(40);
        this.menu.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.menu.setPrefSize(600, 600);
    }

    public void createScene(Stage stage) {
        this.scene = new Scene(this.menu);
        this.scene.setFill(Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }

    public void createTextField() {
        this.titleScreen = new Label("ASTEROIDS");
        this.titleScreen.setFont(Font.font("Verdana", 70));
        this.titleScreen.setTextFill(Color.BLACK);
        this.titleScreen.setMinWidth(450);
        this.titleScreen.setMinHeight(100);
        this.titleScreen.setMaxWidth(450);
        this.titleScreen.setMaxHeight(100);
        this.titleScreen.setAlignment(Pos.CENTER);
        this.menu.setAlignment(Pos.CENTER);
    }

    public void createButtons(Stage stage) {
        this.btnContainer = new StackPane();
        this.btnContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.startBtn = new Button("START");
        this.lbBtn = new Button("LEADERBOARD");
        this.exit = new Button("EXIT GAME");
        ////Button styling
        this.startBtn.setMinWidth(200);
        this.lbBtn.setMinWidth(200);
        this.exit.setMinWidth(200);
        this.startBtn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.startBtn.setFont(Font.font("Verdana", 20));
        this.startBtn.setTextFill(Color.BLACK);
        this.startBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.lbBtn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.lbBtn.setFont(Font.font("Verdana", 20));
        this.lbBtn.setTextFill(Color.BLACK);
        this.lbBtn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.exit.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.exit.setFont(Font.font("Verdana", 20));
        this.exit.setTextFill(Color.BLACK);
        this.exit.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        ////Adding buttons to grid
        this.menu.add(this.titleScreen, 1, 0);
        this.menu.add(startBtn, 1, 1);
        this.menu.add(lbBtn, 1, 2);
        this.menu.add(exit, 1, 3);
        ////Adding event handler for animation and setting each cell to pe centered horizontally
        this.menu.getChildren().forEach(child -> {
            GridPane.setHalignment(child, HPos.CENTER);
            if (child instanceof Button button) {
                button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                        button.setTextFill(Color.WHITE);
                        button.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                });
                button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                        button.setTextFill(Color.BLACK);
                        button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                });
                if ("EXIT GAME".equals(button.getText())) {
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            closeGame(stage);
                        }
                    });
                }
                if ("START".equals(button.getText())) {
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            new GameWindow(stage);
                        }
                    });
                }
                if ("LEADERBOARD".equals((button.getText()))) {
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            new Leaderboard(stage);
                        }
                    });
                }
            }

        });
    }

    public void closeGame(Stage stage) {
        stage.close();
    }

}
