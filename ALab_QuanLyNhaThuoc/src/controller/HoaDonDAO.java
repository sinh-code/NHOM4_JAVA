package controller;

import connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;



public class HoaDonDAO {

	public void themMuaThuoc(int idThuoc, String tenKhachHang, int soLuongMua, Date ngayMua, String maHoaDon) {

		String query = "INSERT INTO HoaDon (idThuoc, tenKhachHang, soLuongMua, ngayDat, maHoaDon) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, idThuoc);
			stmt.setString(2, tenKhachHang);
			stmt.setInt(3, soLuongMua);
			stmt.setDate(4, new java.sql.Date(ngayMua.getTime()));
			stmt.setString(5, maHoaDon);

			// Thực thi câu lệnh INSERT
			stmt.executeUpdate();
			System.out.println("Thêm đơn hàng thành công, Mã hóa đơn: " + maHoaDon);

		} catch (SQLException e) {
			System.out.println("Lỗi thêm đơn hàng mua thuốc: " + e.getMessage());
		}
	}

	public void capNhatIdThuocTrongHoaDon(int idHoaDon) {
		String query = "UPDATE HoaDon SET idThuoc = NULL WHERE id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, idHoaDon); // Cập nhật idThuoc trong HoaDon

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Khóa ngoại trong HoaDon đã được cập nhật.");
			} else {
				System.out.println("Không tìm thấy hóa đơn với id " + idHoaDon + " để cập nhật.");
			}
		} catch (SQLException e) {
			System.out.println("Lỗi cập nhật khóa ngoại trong HoaDon: " + e.getMessage());
		}
	}

	public int xoaHoaDonId(int id) {
		String query = "DELETE FROM HoaDon WHERE id = ?";
		int idXoa = -1; // Biến lưu id đã xóa, mặc định là -1 (chưa xóa)

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, id);

			// Thực thi câu lệnh DELETE
			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				idXoa = id; // Nếu xóa thành công, gán id đã xóa vào biến
				System.out.println("Đơn hàng đã được xóa thành công, ID: " + id);
			} else {
				System.out.println("Không tìm thấy đơn hàng với ID: " + id);
			}

		} catch (SQLException e) {
			System.out.println("Lỗi xóa đơn hàng theo ID: " + e.getMessage());
		}

		return idXoa; // Trả về id đã xóa hoặc -1 nếu không có bản ghi nào bị xóa
	}

	public int getIdThuoc(int idHoaDon) {
		String query = "SELECT Thuoc.id AS idThuoc " + "FROM HoaDon " + "INNER JOIN Thuoc1 ON HoaDon.idThuoc = Thuoc.id "
				+ "WHERE HoaDon.id = ?";
		int idThuoc = -1; // Mặc định trả về -1 nếu không tìm thấy kết quả

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, idHoaDon); // Gán idHoaDon vào câu truy vấn

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					idThuoc = rs.getInt("idThuoc"); // Lấy idThuoc từ kết quả
				}
			}
		} catch (SQLException e) {
			System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
		}

		return idThuoc; // Trả về idThuoc hoặc -1 nếu không tìm thấy
	}

	public void capNhatIdThuocTrongHoaDonAll() {
		String query = "UPDATE HoaDon SET idThuoc = NULL"; // Cập nhật tất cả hóa đơn

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Đã cập nhật khóa ngoại idThuoc cho tất cả các hóa đơn.");
			} else {
				System.out.println("Không có hóa đơn nào để cập nhật.");
			}
		} catch (SQLException e) {
			System.out.println("Lỗi khi cập nhật khóa ngoại idThuoc cho tất cả hóa đơn: " + e.getMessage());
		}
	}

	// Xóa tất cả các hóa đơn
	public int xoaTatCaHoaDon() {
		String query = "DELETE FROM HoaDon";
		int rowsAffected = 0;

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			rowsAffected = stmt.executeUpdate(); // Thực thi câu lệnh DELETE
			if (rowsAffected > 0) {
				System.out.println("Đã xóa tất cả hóa đơn.");
			} else {
				System.out.println("Không có hóa đơn nào để xóa.");
			}

		} catch (SQLException e) {
			System.out.println("Lỗi khi xóa tất cả hóa đơn: " + e.getMessage());
		}

		return rowsAffected; // Trả về số lượng bản ghi bị xóa
	}
}
