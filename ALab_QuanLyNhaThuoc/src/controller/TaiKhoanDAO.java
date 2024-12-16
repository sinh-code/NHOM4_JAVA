/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.TaiKhoan;
import java.sql.*;


/**
 *
 * @author Alex
 */
public class TaiKhoanDAO {
    private Connection conn;
    public TaiKhoanDAO(){
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
    public ArrayList<TaiKhoan> getAllTaiKhoan() {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TaiKhoan tk = new TaiKhoan();
                tk.setId(rs.getInt("id")); // Lấy giá trị cột id
                tk.setUsername(rs.getString("username")); // Lấy giá trị cột username
                tk.setPassword(rs.getString("password")); // Lấy giá trị cột password
                tk.setRole(rs.getString("role")); // Lấy giá trị cột role
                list.add(tk);                                        
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }


     public boolean addTaiKhoan(TaiKhoan s) {
        String sql = "INSERT INTO TAIKHOAN(username, password, role) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ps.setString(3, s.getRole());
            
            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
    public boolean updateTaiKhoan(TaiKhoan tk) {
        String sql = "UPDATE TAIKHOAN SET username = ?, password = ?, role = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, tk.getUsername());
            ps.setString(2, tk.getPassword());
            ps.setString(3, tk.getRole());
            ps.setInt(4, tk.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteTaiKhoan(int id) {

        String sql = "DELETE FROM TAIKHOAN WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate(); // Số dòng bị ảnh hưởng
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi

    }

}
