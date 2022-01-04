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

    public void updateDB(TextScore textScore)
    {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB=connectNow.getConnection();
        try{
            Statement statement=connectionDB.createStatement();
            statement.executeUpdate("insert into asteroidsscore (score) values ('" + textScore.textParsing() +"');");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
