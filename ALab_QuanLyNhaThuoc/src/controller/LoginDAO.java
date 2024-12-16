/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;


/**
 *
 * @author Alex
 */
public class LoginDAO {
    private Connection conn;
    public LoginDAO(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=quanlythuoc;encrypt=false";
            String user = "sa";
            String pwd = "123";
            conn = (Connection) DriverManager.getConnection(url,user, pwd);
            
        }catch(Exception e){
            System.out.println(e.toString());
       }
    }
     public String authenticate(String username, String password) {
          String role = null;

        try  {
            String query = "SELECT role FROM TAIKHOAN WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                role = resultSet.getString("role"); // Lấy quyền (admin hoặc user)
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return role; // Trả về vai trò nếu xác thực thành công, nếu không thì trả về null
    }
    

}
