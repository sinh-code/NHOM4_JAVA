package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kết nối đến cơ sở dữ liệu SQL Server
 * Lớp này cung cấp phương thức để lấy kết nối và kiểm tra kết nối thành công.
 */
public class DatabaseConnection {

    // Các thông tin kết nối cơ sở dữ liệu
   private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=quanlythuoc;encrypt=false";
    private static final String USER = "sa";  
    private static final String PASSWORD = "123";  
    
    // Phương thức để lấy kết nối
    public static Connection getConnection() throws SQLException {
        try {
            // Tải driver JDBC SQL Server (SQL Server JDBC Driver 8.4 trở lên không cần gọi Class.forName())
            // Đảm bảo bạn đã thêm thư viện "mssql-jdbc" vào project của bạn
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Kết nối đến cơ sở dữ liệu
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Xử lý nếu driver không tìm thấy
            System.out.println("Driver JDBC không tìm thấy: " + e.getMessage());
            throw new SQLException("Driver JDBC không tìm thấy", e);
        } catch (SQLException e) {
            // Xử lý lỗi kết nối
            System.out.println("Kết nối không thành công: " + e.getMessage());
            throw e;
        }
    }

    // Phương thức kiểm tra kết nối
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        }
    }
}
