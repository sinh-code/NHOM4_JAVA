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
public class ThongKe {
    private String maThuoc;
    private String tenThuoc;
    private int soLuongMua;
    private String tenKhachHang;
    private Date ngayMua;
    private double giaTien;
    private double tongTien;

    // Constructor, getters và setters
    public ThongKe(String maThuoc, String tenThuoc, int soLuongMua, String  tenKhachHang, Date ngayMua, double giaTien, double tongDoanhThu) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.soLuongMua = soLuongMua;
        this. tenKhachHang =  tenKhachHang;
        this.ngayMua = ngayMua;
        this.giaTien = giaTien;
        this.tongTien = tongDoanhThu;
    }

    // Getters and Setters
    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public String getTenKhachHang() {
        return  tenKhachHang;
    }

    public void setTenKhachHang(String  tenKhachHang) {
        this. tenKhachHang =  tenKhachHang;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongDoanhThu(double tongTien) {
        this.tongTien = tongTien;
    }
}
