package com.alven.fun.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JDBCExample {

    public static void main(String[] args) {

        try {

            Connection con = JDBCUtil.getConnection(); // create connection
            //ps: please create table first!!!
            con.setAutoCommit(false);
            
            Statement s = con.createStatement(); 
            s.executeUpdate("DELETE FROM emp where empno=4");
            con.commit();
            
            String sql = "INSERT INTO emp(empno, ename, deptno) VALUES (?,?,?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, 4);
            st.setString(2, "qqqqq");
            st.setInt(3, 1);
            
            int count = st.executeUpdate();  
              
            System.out.println("insert into emp " + count + " record");
            
            con.commit();
            con.close();
            
        } catch (Exception e) {
            System.out.println("Error! " + e);
        }
    }

}
