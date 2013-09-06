package com.alven.fun.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCQueryExample {

    public static void main(String[] args) {

        try {

            Connection con = JDBCUtil.getConnection(); // create connection
            //ps: please create table first!!!
            
            String sql="select * from emp where empno= ? ";  
            PreparedStatement st = con.prepareStatement(sql);    
            
            st.setInt(1, 2);
            
            ResultSet rs = st.executeQuery();    
            System.out.println("The result is: ");  
            while (rs.next()) {  
                  
                int empno = rs.getInt("empno");  
                int deptno = rs.getInt("deptno");
                String ename = rs.getString("ename");
                  
                System.out.println(empno + " " + ename + " " + deptno);  
            }  
            con.close();
            
        } catch (Exception e) {
            System.out.println("Error! " + e);
        }
    }

}
