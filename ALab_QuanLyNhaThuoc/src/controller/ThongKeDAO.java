package controller;

import connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import model.ThongKe;

public class ThongKeDAO {
	private double tongDoanhThu = 0;
	double tongDoanhThuthang = 0;

	public List<ThongKe> layTatCa() {
	    List<ThongKe> thongKeList = new ArrayList<>();
	    
	    // Updated query to join HoaDon and Thuoc tables based on the new schema
	    String query = "SELECT t.maThuoc, t.tenThuoc, h.soLuongMua, h.tenKhachHang, h.ngayDat, t.giaTien, "
	            + "(h.soLuongMua * t.giaTien) AS tongDoanhThu "
	            + "FROM HoaDon h "
	            + "JOIN Thuoc1 t ON h.idThuoc = t.id";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            String maThuoc = rs.getString("maThuoc");
	            String tenThuoc = rs.getString("tenThuoc");
	            int soLuongMua = rs.getInt("soLuongMua");
	            String tenKhachHang = rs.getString("tenKhachHang");
	            Date ngayMua = rs.getDate("ngayDat"); // Using ngayDat as the purchase date
	            double giaTien = rs.getDouble("giaTien");
	            double doanhThu = rs.getDouble("tongDoanhThu");

	            ThongKe thongKe = new ThongKe(maThuoc, tenThuoc, soLuongMua, tenKhachHang, ngayMua, giaTien, doanhThu);
	            thongKeList.add(thongKe);
	        }
	    } catch (SQLException e) {
	        System.out.println("Lỗi truy vấn thống kê doanh thu: " + e.getMessage());
	    }

	    return thongKeList;
	}

	public double thongKeDoanhThuTheoNgay(String ngay) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	

	    try {
	        Date date = dateFormat.parse(ngay);

	        // Updated query to use HoaDon and Thuoc tables based on new schema
	        String query = "SELECT t.maThuoc, t.tenThuoc, h.soLuongMua, h.tenKhachHang, h.ngayDat, t.giaTien, "
	                + "(h.soLuongMua * t.giaTien) AS tongDoanhThu "
	                + "FROM HoaDon h "
	                + "JOIN Thuoc1 t ON h.idThuoc = t.id "
	                + "WHERE h.ngayDat = ?";

	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setDate(1, new java.sql.Date(date.getTime()));
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                double doanhThu = rs.getDouble("tongDoanhThu");
	                tongDoanhThu += doanhThu;
	            }

	        } catch (SQLException e) {
	            System.out.println("Lỗi truy vấn thống kê doanh thu theo ngày: " + e.getMessage());
	        }

	    } catch (Exception e) {
	        System.out.println("Lỗi phân tích ngày: " + e.getMessage());
	    }

	    return tongDoanhThu;
	}

	public void thongKeDoanhThuTheoThang(int thang, int nam) {
	    String query = "SELECT t.maThuoc, t.tenThuoc, h.soLuongMua, h.tenKhachHang, h.ngayDat, t.giaTien, "
	            + "(h.soLuongMua * t.giaTien) AS tongDoanhThu "
	            + "FROM HoaDon h "
	            + "JOIN Thuoc1 t ON h.idThuoc = t.id "
	            + "WHERE MONTH(h.ngayDat) = ? AND YEAR(h.ngayDat) = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setInt(1, thang);
	        stmt.setInt(2, nam);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            double doanhThu = rs.getDouble("tongDoanhThu");
	            tongDoanhThuthang += doanhThu;
	        }

	    } catch (SQLException e) {
	        System.out.println("Lỗi truy vấn thống kê doanh thu theo tháng: " + e.getMessage());
	    }
	}

	public List<ThongKe> getDulieuId(String mathuoc) {
	    List<ThongKe> thongKeList = new ArrayList<>();

	    String query = "SELECT t.maThuoc, t.tenThuoc, h.soLuongMua, h.tenKhachHang, h.ngayDat, t.giaTien, "
	            + "(h.soLuongMua * t.giaTien) AS tongDoanhThu "
	            + "FROM HoaDon h "
	            + "JOIN Thuoc1 t ON h.idThuoc = t.id "
	            + "WHERE t.maThuoc = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, mathuoc); // Set the value for the parameter

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String maThuoc = rs.getString("maThuoc");
	            String tenThuoc = rs.getString("tenThuoc");
	            int soLuongMua = rs.getInt("soLuongMua");
	            String tenKhachHang = rs.getString("tenKhachHang");
	            Date ngayMua = rs.getDate("ngayDat");
	            double giaTien = rs.getDouble("giaTien");
	            double doanhThu = rs.getDouble("tongDoanhThu");

	            ThongKe thongKe = new ThongKe(maThuoc, tenThuoc, soLuongMua, tenKhachHang, ngayMua, giaTien, doanhThu);
	            thongKeList.add(thongKe);
	        }

	    } catch (SQLException e) {
	        System.out.println("Lỗi truy vấn thống kê doanh thu theo ID thuốc: " + e.getMessage());
	    }

	    return thongKeList;
	}


	public double getTongDoanhThu() {
		return tongDoanhThu;
	}

	public double getTongDoanhThuthang() {
		return tongDoanhThuthang;
	}
}
