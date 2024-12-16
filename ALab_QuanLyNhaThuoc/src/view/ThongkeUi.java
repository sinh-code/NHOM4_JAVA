package view;

import com.toedter.calendar.JDateChooser;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import java.text.NumberFormat;
import java.util.Locale;

import controller.ThongKeDAO;
import model.ThongKe;

public class ThongkeUi {

	public JFrame frame;
	private JDateChooser ngay;
	private JTextField masp;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField thongketheongay;
//	private JTextField thongketheothang;
	private JButton timkiem;
//        private JButton  btnquanli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongkeUi window = new ThongkeUi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ThongkeUi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 255, 255));
		frame.setBounds(100, 100, 844, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("THỐNG KÊ DOANH THU");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(301, 28, 320, 59);
		frame.getContentPane().add(lblNewLabel);

	        JButton	btnquanli = new JButton("Quay lại");
	
		btnquanli.setBounds(5, 45, 177, 26);
		frame.getContentPane().add(btnquanli);

		JLabel lblNewLabel_1 = new JLabel("Thống kê theo ngày");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(150, 96, 164, 20);
		frame.getContentPane().add(lblNewLabel_1);

		ngay = new JDateChooser();
		ngay.setBounds(370, 89, 207, 27);
		frame.getContentPane().add(ngay);
  btnquanli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
              
                frame.dispose(); // Đóng cửa sổ hiện tại
                 AdminPage adminPage = new AdminPage();
                adminPage.setVisible(true);
            }
        });
		JLabel lblNewLabel_2 = new JLabel("Danh sách đơn hàng (Mã sp)");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(150, 181, 230, 27);
		frame.getContentPane().add(lblNewLabel_2);

		masp = new JTextField();
		masp.setColumns(10);
		masp.setBounds(370, 181, 207, 27);
		frame.getContentPane().add(masp);

		timkiem = new JButton("Tìm");
		timkiem.setBounds(588, 184, 85, 21);
		frame.getContentPane().add(timkiem);

		// Create a table model
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Mã thuốc");
		tableModel.addColumn("Tên Thuốc");
		tableModel.addColumn("Số Lượng Bán");
		tableModel.addColumn("Giá");
		tableModel.addColumn("Tổng Tiền");

		// Create a JTable and set the model
		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		// Add table to scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 250, 740, 200);
		frame.getContentPane().add(scrollPane);

		

//		JLabel lblNewLabel_3_1 = new JLabel("Tổng danh thu theo tháng");
//		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblNewLabel_3_1.setBounds(602, 472, 188, 23);
//		frame.getContentPane().add(lblNewLabel_3_1);

		

//		JLabel lblNewLabel_4_1 = new JLabel("VNĐ");
//		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblNewLabel_4_1.setBounds(745, 511, 45, 13);
//		frame.getContentPane().add(lblNewLabel_4_1);

		JLabel lblNewLabel_3 = new JLabel("Tổng danh thu theo ngày");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(617, 482, 173, 21);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("VNĐ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(764, 520, 45, 13);
		frame.getContentPane().add(lblNewLabel_4);

		thongketheongay = new JTextField();
		thongketheongay.setBounds(627, 514, 127, 25);
		frame.getContentPane().add(thongketheongay);
		thongketheongay.setColumns(10);

//		thongketheothang = new JTextField();
//		thongketheothang.setColumns(10);
//		thongketheothang.setBounds(608, 505, 127, 25);
//		frame.getContentPane().add(thongketheothang);

		// Example data
		laydulieu();
		thongke();
		timKiem();
//		quit();
	}

	
	private void addRowToTable(String maSP, String tenThuoc, int soLuongBan, double gia) {
		double tongTien = soLuongBan * gia;
		tableModel.addRow(new Object[] { maSP, tenThuoc, soLuongBan, gia, tongTien });
	}

	public void laydulieu() {
		ThongKeDAO thongKeDAO = new ThongKeDAO();
		List<ThongKe> thongKeList = thongKeDAO.layTatCa();

		// Xóa tất cả các dòng trong JTable trước khi thêm mới
		tableModel.setRowCount(0);

	
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

		// Chuyển đổi giá tiền và tổng tiền sang tiền Việt Nam
		for (ThongKe thongKe : thongKeList) {
			String giaTienVND = currencyFormat.format(thongKe.getGiaTien());
			String tongTienVND = currencyFormat.format(thongKe.getTongTien());

			tableModel.addRow(new Object[] { thongKe.getMaThuoc(), thongKe.getTenThuoc(), thongKe.getSoLuongMua(),
					giaTienVND, tongTienVND // Hiển thị tổng tiền theo định dạng VND
			});
		}
	}

	public void thongke() {
		// ActionListener to handle date selection
		ngay.addPropertyChangeListener("date", e -> {
			java.util.Date selectedDate = ngay.getDate();
			if (selectedDate != null) {

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String formattedDate = dateFormat.format(selectedDate);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(selectedDate);
				int thang = calendar.get(Calendar.MONTH) + 1;
				int nam = calendar.get(Calendar.YEAR);

				ThongKeDAO thongKeDAO = new ThongKeDAO();
				double tongDoanhThu = thongKeDAO.thongKeDoanhThuTheoNgay(formattedDate);

				// Tính doanh thu theo tháng
				thongKeDAO.thongKeDoanhThuTheoThang(thang, nam);

				// Lấy tổng doanh thu theo ngày

				// Lấy tổng doanh thu theo tháng
				double tongDoanhThuThang = thongKeDAO.getTongDoanhThuthang();

				NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
				String formattedRevenue = currencyFormat.format(tongDoanhThu);
				String formattedRevenueThang = currencyFormat.format(tongDoanhThuThang);

				thongketheongay.setText(formattedRevenue);

//				thongketheothang.setText(formattedRevenueThang);
			}
		});
	}

	public void timKiem() {
		timkiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Lấy mã sản phẩm từ trường nhập liệu masp
				String ma = masp.getText();

				// Kiểm tra nếu mã sản phẩm không rỗng
				if (ma != null && !ma.trim().isEmpty()) {
				
					ThongKeDAO thongKeDAO = new ThongKeDAO();
					List<ThongKe> thongKeList = thongKeDAO.getDulieuId(ma);

					// Kiểm tra nếu danh sách kết quả tìm kiếm không rỗng
					if (thongKeList != null && !thongKeList.isEmpty()) {
						
						tableModel.setRowCount(0);

						NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

						
						for (ThongKe thongKe : thongKeList) {
							String giaTienVND = currencyFormat.format(thongKe.getGiaTien());
							String tongTienVND = currencyFormat.format(thongKe.getTongTien());
							tableModel.addRow(new Object[] { thongKe.getMaThuoc(), thongKe.getTenThuoc(),
									thongKe.getSoLuongMua(), giaTienVND, tongTienVND });
						}
					} else {
					
						JOptionPane.showMessageDialog(frame, "Không tìm thấy sản phẩm với mã: " + ma, "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					
					JOptionPane.showMessageDialog(frame, "Vui lòng nhập mã sản phẩm để tìm kiếm.", "Thông báo",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}

//	public void quit() {
//	    btnquanli.addActionListener(new ActionListener() {
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	            // Đóng giao diện hiện tại
//	            frame.dispose(); // Đóng cửa sổ hiện tại
//	            
//	            // Mở giao diện mới (Ví dụ: mở cửa sổ quản lý)
//	            // Giả sử bạn có một lớp GiaoDienQuanLyUi để mở trang quản lý
//	            GiaoDienQuanLyUi quanLyUi = new GiaoDienQuanLyUi();
//	            quanLyUi.frame.setVisible(true);
//	        }
//	    });
//	}

}
