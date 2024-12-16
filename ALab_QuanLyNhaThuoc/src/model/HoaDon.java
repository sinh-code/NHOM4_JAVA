/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author LAI VAN SINH
 */
public class HoaDon {
    // Thuộc tính
	private int idThuoc; // Khóa ngoại tham chiếu tới lớp Thuoc
	private int id; // ID của đơn hàng
	private String tenKhachHang; // Tên người mua
	private int soLuongMua; // Số lượng thuốc mua
	private Date ngayDat;
	private String maHoaDon;// Ngày mua

	public HoaDon(int idThuoc, int id, String tenKhachHang, int soLuongMua, Date ngayMua, String maHoaDon) {
		super();
		this.idThuoc = idThuoc;
		this.id = id;
		this.tenKhachHang = tenKhachHang;
		this.soLuongMua = soLuongMua;
		this.ngayDat = ngayMua;
		this.maHoaDon = maHoaDon;
	}

	// Getter và Setter
	public int getIdThuoc() {
		return idThuoc;
	}

	public void setIdThuoc(int idThuoc) {
		this.idThuoc = idThuoc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public int getSoLuongMua() {
		return soLuongMua;
	}

	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}

	public Date getNgayMua() {
		return ngayDat;
	}

	public void ngayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

}
