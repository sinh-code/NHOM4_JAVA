/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Alex
 */
public class NhanVien {
    private int maNhanVien;
    private String tenNV, sdt, chucVu;
    private double luong;

    public NhanVien() {
    }

    public NhanVien(int maNhanVien, String tenNV, String sdt, String chucVu, double luong) {
        this.maNhanVien = maNhanVien;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.chucVu = chucVu;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public double getLuong() {
        return luong;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
