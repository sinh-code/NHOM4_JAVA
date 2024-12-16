/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.NhanVien;
import java.sql.*;

/**
 *
 * @author Alex
 */
public class NhanVienDAO {
      private Connection conn;

    public NhanVienDAO() {
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

    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getInt("maNhanVien"));
                nv.setTenNV(rs.getString("tenNV"));
                nv.setSdt(rs.getString("sdt"));
                nv.setChucVu(rs.getString("chucVu"));
                nv.setLuong(rs.getDouble("luong"));
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addNhanVien(NhanVien nv) {
       String sql = "INSERT INTO NhanVien(tenNV, sdt, chucVu, luong) OUTPUT INSERTED.maNhanVien VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getSdt());
            ps.setString(3, nv.getChucVu());
            ps.setDouble(4, nv.getLuong());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("maNhanVien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if insertion fails*/
       
    }

    public boolean updateNhanVien(NhanVien nv) {
        String sql = "UPDATE NhanVien SET tenNV = ?, sdt = ?, chucVu = ?, luong = ? WHERE maNhanVien = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getSdt());
            ps.setString(3, nv.getChucVu());
            ps.setDouble(4, nv.getLuong());
            ps.setInt(5, nv.getMaNhanVien());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteNhanVien(int maNhanVien) {
        String sql = "DELETE FROM NhanVien WHERE maNhanVien = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maNhanVien);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


