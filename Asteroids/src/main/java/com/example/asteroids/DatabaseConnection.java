package com.example.asteroids;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

    private Connection databaseLink;

    public Connection getConnection(){
        String databaseName="Asteroids";
        String databaseUser="Patrick";
        String databasePassword="20011002pat";
        String url="jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }

    public void updateDBScore(TextScore textScore)
    {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection databaseConnection = connectNow.getConnection();
        try{
            Statement statement=databaseConnection.createStatement();
            statement.executeUpdate("insert into asteroidsscore (score) values ('" + textScore.textParsing() +"');");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateDB(String string){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection databaseConnection = connectNow.getConnection();
        try{
            Statement statement=databaseConnection.createStatement();
            statement.executeUpdate("insert into asteroidsscore (name, score) values ('" + string + "'," + " '" + TextScore.textParsing() + "');");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
