package com.example.asteroids;
import java.sql.Connection;
import java.sql.DriverManager;

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
}
