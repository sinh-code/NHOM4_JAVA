/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.Thuoc;
import java.sql.*;


/**
 *
 * @author Alex
 */
public class ThuocDAO {
   private Connection conn;

    public ThuocDAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=quanlythuoc;encrypt=false";
            String user = "sa";
            String pwd = "123";
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Thuoc> getAllThuoc() {
        ArrayList<Thuoc> list = new ArrayList<>();
        String sql = "SELECT * FROM THUOC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Thuoc t = new Thuoc();
                t.setMaThuoc(rs.getInt("maThuoc"));
                t.setTenThuoc(rs.getString("tenThuoc"));
                t.setHangSX(rs.getString("hangSX"));
                t.setNgaySX(rs.getString("ngaySX"));
                t.setHanSD(rs.getString("hanSD"));
                t.setGia(rs.getDouble("gia"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int addThuoc(Thuoc t) {
    String sql = "INSERT INTO THUOC(tenThuoc, hangSX, ngaySX, hanSD, gia) OUTPUT INSERTED.maThuoc VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, t.getTenThuoc());
        ps.setString(2, t.getHangSX());
        ps.setString(3, t.getNgaySX());
        ps.setString(4, t.getHanSD());
        ps.setDouble(5, t.getGia());

        // Execute and retrieve the generated key
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("maThuoc");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Trả về -1 nếu thất bại
}


    public boolean updateThuoc(Thuoc t) {
        String sql = "UPDATE THUOC SET tenThuoc = ?, hangSX = ?, ngaySX = ?, hanSD = ?, gia = ? WHERE maThuoc = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTenThuoc());
            ps.setString(2, t.getHangSX());
            ps.setString(3, t.getNgaySX());
            ps.setString(4, t.getHanSD());
            ps.setDouble(5, t.getGia());
            ps.setInt(6, t.getMaThuoc());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteThuoc(int maThuoc) {
        String sql = "DELETE FROM THUOC WHERE maThuoc = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maThuoc);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void xoaTatCaThuoc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void xoaThuoc(int idThuoc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int themThuoc(String maThuoc, String tenThuoc, double giaTien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object[][] layThongTinHoaDon() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
