/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.entity;

/**
 *
 * @author Admin
 */
public class NhanVien {
    String ten,nam,sdt,diachi;
    int ma;
    boolean giotinh;

    public NhanVien() {
    }

    public NhanVien(int ma, String ten) {
        this.ten = ten;
        this.ma = ma;
    }

    public NhanVien(String ten) {
        this.ten = ten;
    }
    
    public NhanVien(String ten, String nam, String sdt, String diachi, int ma, boolean giotinh) {
        this.ten = ten;
        this.nam = nam;
        this.sdt = sdt;
        this.diachi = diachi;
        this.ma = ma;
        this.giotinh = giotinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public boolean isGiotinh() {
        return giotinh;
    }

    public void setGiotinh(boolean giotinh) {
        this.giotinh = giotinh;
    }
    
    @Override
    public String toString(){
        return ten;
    }
    
}
