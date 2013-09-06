package com.alven.fun.jdbc;

import java.io.*;
import java.sql.*;

public class BlobClobExample {
    public static void main(String[] args) {
        try {
            
            Connection conn = JDBCUtil.getConnection(); 
            conn.setAutoCommit(false);
            
            Statement s = conn.createStatement(); 
            
            s.executeUpdate("drop table documents");
            s.executeUpdate("CREATE TABLE documents(id INT, text MEDIUMTEXT , photo LONGBLOB)");
            
            conn.commit(); 
            
            String filepath = BlobClobExample.class.getResource("/").getPath();
            System.out.println("filepath is " + filepath);
            File file1 = new File(filepath+"/database.sql");
            int len1 = (int) file1.length();
            InputStream fis1 = new java.io.FileInputStream(file1);
            
            File file2 = new File(filepath+"/1.JPG");
            int len2 = (int) file2.length();
            InputStream fis2 = new FileInputStream(file2);

            PreparedStatement ps = conn.prepareStatement("INSERT INTO documents VALUES (?, ?,?)");
            ps.setInt(1, 250);
            ps.setAsciiStream(2, fis1, len1);
            ps.setBinaryStream(3, fis2, len2);

            ps.execute(); 
            
            conn.commit();
            
            ResultSet rs = s.executeQuery("SELECT text , photo FROM documents WHERE id = 250");
            while (rs.next()) {
                Clob aclob = rs.getClob(1); 
                InputStream is = rs.getAsciiStream(1); 
                
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while (null != (line = br.readLine())) {
                    System.out.println(line); 
                }
                is.close();
                
                java.sql.Blob ablob = rs.getBlob(2); 
                System.out.println(ablob.length()); 
                
                InputStream bis = ablob.getBinaryStream(); 
                OutputStream os = new FileOutputStream(filepath+"/11.jpg");
                int b = bis.read(); 
                while (b != -1) {
                    os.write((char) b);
                    b = bis.read();
                }
                os.flush();
                os.close();
                bis.close();
            }
        } catch (Exception e) {
            System.out.println("Error! " + e);
        }
    }
}