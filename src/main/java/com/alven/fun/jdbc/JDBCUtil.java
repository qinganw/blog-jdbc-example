package com.alven.fun.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {

    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String username = "root";
            String password = "smfr";
            String url = "jdbc:mysql://localhost:3306/jdbctest?useUnicode=true&amp;characterEncoding=UTF-8";
            con = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {  
            System.out.println("Database connect fail!" + e.getMessage());  
        }  
        return con;
    }
    
}
