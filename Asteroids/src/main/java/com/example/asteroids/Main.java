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
