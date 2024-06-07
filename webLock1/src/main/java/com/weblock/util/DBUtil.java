package com.weblock.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/weblock1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root1262"; // Update this
    
    
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
