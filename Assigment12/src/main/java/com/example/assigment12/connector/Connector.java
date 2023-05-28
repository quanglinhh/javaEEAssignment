package com.example.assigment12.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector { private Connection connection;

    public synchronized Connection getConnection(){
        try{
            if(connection == null){
                String url = "jdbc:mysql://localhost:3066/test";
                String user= "root";
                String pass="";
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url,user,pass);
            }
        }catch (Exception ex){}
        return connection;

    }

}
