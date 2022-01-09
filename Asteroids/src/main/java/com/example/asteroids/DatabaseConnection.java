package com.example.asteroids;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {

    private Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "Asteroids";
        String databaseUser = "Patrick";
        String databasePassword = "20011002pat";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }

    public static void updateDB(String string) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection databaseConnection = connectNow.getConnection();
        try {
            Statement statement = databaseConnection.createStatement();
            statement.executeUpdate("insert into asteroidsscore (name, score) values ('" + string + "'," + " '" + TextScore.textParsing() + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Player> getPlayersInfo() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection databaseConnection = connectNow.getConnection();
        try {
            ArrayList<Player> top10 = new ArrayList<>();
            Statement getName = databaseConnection.createStatement();
            ResultSet resultSet = getName.executeQuery("select name, score from asteroidsscore order by score desc limit 10;");
            while (resultSet.next()) {
                Player player = new Player();
                player.setName(resultSet.getString(1));
                player.setScore(resultSet.getInt(2));
                top10.add(player);
                System.out.println(player.getName() + player.getScore());
            }
            return top10;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
