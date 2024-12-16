/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Alex
 */
public class Thuoc {
    private int maThuoc;
    private String tenThuoc;
    private String hangSX;
    private String ngaySX;
    private String hanSD;
    private double gia;

   
    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Thuoc() {
    }

    public Thuoc(int maThuoc, String tenThuoc, String hangSX, String ngaySX, String hanSD, double gia) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.hangSX = hangSX;
        this.ngaySX = ngaySX;
        this.hanSD = hanSD;
        this.gia = gia;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getHangSX() {
        return hangSX;
    }

    public void setHangSX(String hangSX) {
        this.hangSX = hangSX;
    }

    public String getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(String ngaySX) {
        this.ngaySX = ngaySX;
    }

    public String getHanSD() {
        return hanSD;
    }

    public void setHanSD(String hanSD) {
        this.hanSD = hanSD;
    }


    
}
