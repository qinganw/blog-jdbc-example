package com.alven.fun.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;

public class ProcedureExample {
    public static void main(String[] args) {

        try {

            Connection con = JDBCUtil.getConnection(); // create connection
            //ps: please create table and procedure first!!!
            
            String spName="{call sp_add_emp2(?,?,?,?,?)}";  
            CallableStatement cstmt=con.prepareCall(spName); 
            
            cstmt.setInt(1, 2);  
            cstmt.setString(2, "wwww");  
            cstmt.setInt(3, 1);  
            cstmt.setString(4, "qwqwq");  
            cstmt.registerOutParameter(5, java.sql.Types.INTEGER);  
            cstmt.executeUpdate();  
            int i = cstmt.getInt(5);  
            System.out.println(i);  
            cstmt.close();  
            con.close();
            
        } catch (Exception e) {
            System.out.println("Error! " + e);
        }
    }
}
