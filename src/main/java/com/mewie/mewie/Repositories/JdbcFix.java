package com.mewie.mewie.Repositories;

import java.sql.*;

public class JdbcFix {


    private final String url = "jdbc:mysql://gruppe-a.cnjchjz7nynx.eu-central-1.rds.amazonaws.com:3306/mewie?useSSL=false";
    private final String username = "rewt";
    private final String password = "supersecurepassword";
    protected Connection connection = null;


    public JdbcFix(){
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e){e.printStackTrace();}
        return connection;
    }
    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
