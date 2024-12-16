package view;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;
import model.HoaDon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import controller.HoaDonDAO;
import controller.ThuocDao1;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class GiaoDienQuanLyUi {
	public JFrame frame;
	private JTextField tenthuoc;
	private JTextField mathuoc;
	private JDateChooser ngaysanxuat;
	private JTextField giatien;
	private JButton btnThem, btnXoaHoaDon, btnXoa;
	private DefaultTableModel tableModel;

	private Object[][] data;
	private String[] columnNames = { "Chọn", "ID Hóa Đơn", // Invoice ID
			"Mã Hóa Đơn", // Invoice Code
			"Tên Khách Hàng", // Customer Name
			"Mã Thuốc", // Medicine Code
			"Tên Thuốc", // Medicine Name
			"Ngày Đặt", // Order Date
			"Số Lượng Mua", // Quantity Sold
			"Giá Tiền", // Price per Item
			"Thành Tiền" // Total Price
	};

	 ThuocDao1 thuocDAO = new ThuocDao1();

	private JTable table;
	private HoaDonDAO hoaDonDao = new HoaDonDAO();
	private JTextField soluong;
	private JTextField tenkhachhang;
	private JTextField mahoadon;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(() -> {
			try {
				GiaoDienQuanLyUi window = new GiaoDienQuanLyUi();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GiaoDienQuanLyUi() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		        frame.setBounds(100, 100, 930, 536);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Giao Diện Quản Lý Bán Thuốc");
        frame.getContentPane().setLayout(null); // Sử dụng layout null (vị trí tuyệt đối)
        frame.setResizable(false);

        // Tạo JPanel cho header và đặt vị trí, kích thước
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 255, 255));
        headerPanel.setBounds(0, 0, 930, 120); // Xác định vị trí và kích thước của headerPanel
        headerPanel.setLayout(null); // Sử dụng layout null cho headerPanel

        // Tạo JButton cho nút "Quay lại" và đặt vị trí, kích thước
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBounds(20, 40, 100, 40); // Xác định vị trí và kích thước của nút
        headerPanel.add(backButton);

        // Tạo headerLabel và thiết lập các thuộc tính, sau đó đặt vị trí, kích thước
        JLabel headerLabel = new JLabel("Quản lý đơn hàng");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(150, 30, 700, 50); // Xác định vị trí và kích thước của tiêu đề
        headerPanel.add(headerLabel);
  frame.getContentPane().add(headerPanel);

  
    backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                frame.dispose(); 
                AdminPage adminPage = new AdminPage();
                adminPage.setVisible(true);
             
            }
        });
		// Tạo JMenuBar
//		JMenuBar menuBar = new JMenuBar();
//		frame.setJMenuBar(menuBar);
//		JMenu menu = new JMenu("Tùy chọn");
//		menuBar.add(menu);
//		JMenuItem menuItemQuayLai = new JMenuItem("Thống kê");
//		menuItemQuayLai.addActionListener(e -> {
//			frame.dispose(); // Đóng cửa sổ hiện tại
//
//			ThongkeUi thongkeUi = new ThongkeUi();
//			thongkeUi.frame.setVisible(true);
//		});
//		menu.add(menuItemQuayLai);
		data = fetchAllOrders();
		// Tạo bảng hiển thị thông tin thuốc
		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0) { // Cột "Chọn"
					return Boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}
		};

		// Thêm bảng vào JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(3, 320, 906, 120);
		frame.getContentPane().add(scrollPane);
		// Tạo các button "Thêm", "Sửa", "Xóa"
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 250, 916, 50); // Di chuyển buttonPanel lên trên bảng
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);

		btnThem = new JButton("Thêm hoá đơn");
		btnThem.setBounds(163, 0, 141, 28);
		btnThem.setPreferredSize(new Dimension(100, 28));
		btnThem.setBackground(new Color(0, 191, 255));
		buttonPanel.add(btnThem);

		btnXoaHoaDon = new JButton("Xoá hoá đơn");
		btnXoaHoaDon.setBounds(403, 0, 141, 28);
		btnXoaHoaDon.setPreferredSize(new Dimension(100, 28));
		btnXoaHoaDon.setBackground(new Color(0, 191, 255));
		buttonPanel.add(btnXoaHoaDon);

		btnXoa = new JButton("Xóa hết");
		btnXoa.setBounds(663, 0, 131, 28);
		btnXoa.setPreferredSize(new Dimension(100, 28));
		btnXoa.setBackground(new Color(0, 191, 255));
		buttonPanel.add(btnXoa);

		Panel panel_1 = new Panel();
		panel_1.setForeground(new Color(0, 128, 192));
		panel_1.setBounds(0, 126, 916, 104);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		soluong = new JTextField();
		soluong.setBounds(568, 68, 130, 26);
		panel_1.add(soluong);
		soluong.setFont(new Font("Tahoma", Font.PLAIN, 13));

		giatien = new JTextField();
		giatien.setBounds(786, 11, 120, 26);
		panel_1.add(giatien);
		giatien.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setBounds(501, 67, 68, 26);
		panel_1.add(lblSLng);
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblGiTin = new JLabel("Giá tiền");
		lblGiTin.setBounds(726, 10, 68, 26);
		panel_1.add(lblGiTin);
		lblGiTin.setFont(new Font("Tahoma", Font.PLAIN, 15));

		mathuoc = new JTextField();
		mathuoc.setBounds(361, 68, 130, 26);
		panel_1.add(mathuoc);
		mathuoc.setFont(new Font("Tahoma", Font.PLAIN, 13));

		ngaysanxuat = new JDateChooser();
		ngaysanxuat.setBounds(568, 10, 130, 26);
		panel_1.add(ngaysanxuat);
		ngaysanxuat.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblNgySnXut = new JLabel("Ngày đặt");
		lblNgySnXut.setBounds(501, 10, 98, 26);
		panel_1.add(lblNgySnXut);
		lblNgySnXut.setFont(new Font("Tahoma", Font.PLAIN, 15));

		tenthuoc = new JTextField();
		tenthuoc.setBounds(361, 10, 130, 26);
		panel_1.add(tenthuoc);
		tenthuoc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLabel lblNewLabel_1 = new JLabel("Tên thuốc");
		lblNewLabel_1.setBounds(276, 10, 82, 26);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		// Tạo các trường nhập liệu và label
		JLabel lblNewLabel = new JLabel("Mã thuốc");
		lblNewLabel.setBounds(276, 67, 75, 26);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblNewLabel_1_1 = new JLabel("Tên khách hàng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(3, 10, 136, 26);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Mã hoá đơn");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(3, 68, 98, 26);
		panel_1.add(lblNewLabel_1_2);

		tenkhachhang = new JTextField();
		tenkhachhang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tenkhachhang.setBounds(116, 10, 130, 26);
		panel_1.add(tenkhachhang);

		mahoadon = new JTextField();
		mahoadon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mahoadon.setBounds(113, 68, 130, 26);
		panel_1.add(mahoadon);

		/*
		 * chuc nang
		 */
		them();
		xoaHoaDon();
		xoaAll();
		frame.setVisible(true);
	}

	public Object[][] fetchAllOrders() {
		// Fetch all orders from the database
		return thuocDAO.layThongTinHoaDon();

	}

	public void them() {
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get values from input fields
				String maThuoc = mathuoc.getText().trim();
				String tenThuoc = tenthuoc.getText().trim();
				String giaTienStr = giatien.getText().trim();
				String soluongStr = soluong.getText().trim();
				String tenKhachHang = tenkhachhang.getText().trim();
				String maHoaDon = mahoadon.getText().trim();
				Date ngaySanXuat = ngaysanxuat.getDate();

				if (maThuoc.isEmpty() || tenThuoc.isEmpty() || giaTienStr.isEmpty() || soluongStr.isEmpty()
						|| tenKhachHang.isEmpty() || maHoaDon.isEmpty() || ngaySanXuat == null) {
					JOptionPane.showMessageDialog(frame, "Vui lòng điền đầy đủ thông tin.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Convert price and quantity to the correct data types
				double giaTien = 0;
				int soLuong = 0;
				try {
					giaTien = Double.parseDouble(giaTienStr);
					soLuong = Integer.parseInt(soluongStr);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Giá tiền và số lượng phải là số hợp lệ.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Add medicine to the Thuoc table
				int thuocId = thuocDAO.themThuoc(maThuoc, tenThuoc, giaTien);
				if (thuocId == -1) {
					JOptionPane.showMessageDialog(frame, "Thêm thuốc không thành công.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Add order to the HoaDon table
				hoaDonDao.themMuaThuoc(thuocId, tenKhachHang, soLuong, ngaySanXuat, maHoaDon);

				// Refresh the table with new data
				refreshTableData();

				JOptionPane.showMessageDialog(frame, "Thêm đơn hàng và thuốc thành công.", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	public void refreshTableData() {
		// Assuming you have a method to refresh the table data
		Object[][] newData = fetchAllOrders(); // Method to fetch data
		tableModel.setDataVector(newData, columnNames); // Update table model with new data
	}

	public void xoaHoaDon() {
		btnXoaHoaDon.addActionListener(e -> {
			int selectedRow = table.getSelectedRow(); // Lấy chỉ số dòng được chọn trong bảng
			if (selectedRow != -1) {
				// Lấy id của hóa đơn từ bảng (ví dụ id nằm ở cột 0)
				int idHoaDon = (int) table.getValueAt(selectedRow, 1); // Cột 1 là id của hóa đơn

				int idThuoc = hoaDonDao.getIdThuoc(idHoaDon); // Lấy idThuoc từ hóa đơn

				boolean success = false;

				if (idThuoc != -1) {
					try {
						// Cập nhật lại khóa ngoại idThuoc trong HoaDon
						hoaDonDao.capNhatIdThuocTrongHoaDon(idHoaDon);

						// Xóa thuốc liên quan
						thuocDAO.xoaThuoc(idThuoc);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Lỗi khi xóa thuốc: " + ex.getMessage(), "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				try {
					// Xóa hóa đơn
					int idXoa = hoaDonDao.xoaHoaDonId(idHoaDon);

					if (idXoa != -1) {
						// Cập nhật lại bảng (xóa dòng khỏi bảng)
						((DefaultTableModel) table.getModel()).removeRow(selectedRow);
						refreshTableData();
						success = true;
					} else {
						JOptionPane.showMessageDialog(null, "Không thể xóa hóa đơn. Vui lòng thử lại.", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi khi xóa hóa đơn: " + ex.getMessage(), "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}

				// Hiển thị thông báo thành công nếu không có lỗi
				if (success) {
					JOptionPane.showMessageDialog(null, "Hóa đơn và thuốc đã được xóa thành công!", "Thành công",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn để xóa.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			}
		});
	}

	public void xoaAll() {
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cập nhật khóa ngoại cho tất cả các hóa đơn
				try {
					hoaDonDao.capNhatIdThuocTrongHoaDonAll(); // Cập nhật tất cả hóa đơn
					thuocDAO.xoaTatCaThuoc(); // Xóa tất cả thuốc

					// Xóa tất cả hóa đơn
					int rowsAffected = hoaDonDao.xoaTatCaHoaDon();

					if (rowsAffected > 0) {
						// Cập nhật lại bảng sau khi xóa
						refreshTableData();
						JOptionPane.showMessageDialog(null, "Tất cả hóa đơn và thuốc đã được xóa thành công!",
								"Thành công", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Không có hóa đơn nào để xóa.", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lỗi khi xóa tất cả: " + ex.getMessage(), "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
