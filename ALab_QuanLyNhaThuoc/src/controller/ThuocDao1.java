package controller;

import connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import model.Thuoc1;

public class ThuocDao1 {

	public int themThuoc(String maThuoc, String tenThuoc, double giaTien) {
		// Updated SQL query to include giaTien
		String query = "INSERT INTO Thuoc1 (maThuoc, tenThuoc, giaTien) VALUES (?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			// Set tham số cho PreparedStatement
			stmt.setString(1, maThuoc);
			stmt.setString(2, tenThuoc);
			stmt.setDouble(3, giaTien); // Set giaTien

			// Thực thi câu lệnh INSERT
			stmt.executeUpdate();

			// Get the generated keys (auto-generated ID)
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1); // Return the generated ID
				}
			}
		} catch (SQLException e) {
			System.out.println("Lỗi thêm thuốc: " + e.getMessage());
		}

		return -1; // Return -1 if there was an error
	}

	   public Object[][] layThongTinHoaDon() {
        // Modified query to fetch data, excluding the "Chọn" column for now
        String query = "SELECT "
                + "h.id AS hoaDonId, "
                + "h.maHoaDon, "
                + "h.tenkhachHang, "
                + "t.maThuoc, "
                + "t.tenThuoc, "
                + "h.ngayDat, "
                + "h.soLuongMua, "
                + "t.giaTien, "
                + "(h.soLuongMua * t.giaTien) AS thanhTien "
                + "FROM HoaDon h "
                + "JOIN Thuoc1 t ON h.idThuoc = t.id";

        // Array to store the results, increased column count to 10 (for the 9 columns + 1 for 'Chọn')
        Object[][] data = new Object[100][10]; // Adjusted to 10 columns (9 + 1 for 'Chọn')
        int index = 0;

        // Initialize the NumberFormat for VND
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Extracting data from the result set
                int hoaDonId = rs.getInt("hoaDonId");
                String maHoaDon = rs.getString("maHoaDon");
                String tenKhachHang = rs.getString("tenKhachHang");
                String maThuoc = rs.getString("maThuoc");
                String tenThuoc = rs.getString("tenThuoc");
                Date ngayDat = rs.getDate("ngayDat");
                int soLuongMua = rs.getInt("soLuongMua");
                double giaTien = rs.getDouble("giaTien");
                double thanhTien = rs.getDouble("thanhTien");

                // Add the "Chọn" column (false by default, you can modify this logic later)
                data[index][0] = false; // Default value for "Chọn" column (Boolean)

                // Convert giaTien and thanhTien to VND formatted currency
                String giaTienFormatted = currencyFormat.format(giaTien);
                String thanhTienFormatted = currencyFormat.format(thanhTien);

                // Storing the results into the data array, including the "Chọn" column
                data[index][1] = hoaDonId;
                data[index][2] = maHoaDon;
                data[index][3] = tenKhachHang;
                data[index][4] = maThuoc;
                data[index][5] = tenThuoc;
                data[index][6] = ngayDat;
                data[index][7] = soLuongMua;
                data[index][8] = giaTienFormatted; // Displaying giaTien in VND
                data[index][9] = thanhTienFormatted; // Displaying thanhTien in VND

                index++;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn: " + e.getMessage());
        }

        // Return the data array up to the filled index, with 10 columns (1 for "Chọn")
        Object[][] result = new Object[index][10]; // Updated to 10 columns
        System.arraycopy(data, 0, result, 0, index);
        return result;
    }

	// Phương thức xóa thuốc
	public void xoaThuoc(int idThuoc) {
		String query = "DELETE FROM Thuoc1 WHERE id = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, idThuoc); // Gán idThuoc cần xóa

			int rowsDeleted = stmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Thuốc với id " + idThuoc + " đã được xóa thành công.");
			} else {
				System.out.println("Không tìm thấy thuốc với id " + idThuoc + " để xóa.");
			}
		} catch (SQLException e) {
			System.out.println("Lỗi xóa thuốc: " + e.getMessage());
		}
	}

	public void xoaTatCaThuoc() {
		String query = "DELETE FROM Thuoc1"; // Xóa tất cả thuốc

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			int rowsAffected = stmt.executeUpdate(); // Thực thi câu lệnh DELETE
			if (rowsAffected > 0) {
				System.out.println("Đã xóa tất cả thuốc.");
			} else {
				System.out.println("Không có thuốc nào để xóa.");
			}

		} catch (SQLException e) {
			System.out.println("Lỗi khi xóa tất cả thuốc: " + e.getMessage());
		}
	}

    public ArrayList<Thuoc1> getAllThuoc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean deleteThuoc(int maThuoc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean updateThuoc(Thuoc1 t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int addThuoc(Thuoc1 t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
