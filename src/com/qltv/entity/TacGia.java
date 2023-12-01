/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.entity;

/**
 *
 * @author Admin
 */
public class TacGia {
    int ma,namsinh;
    String ten,quequan,hinh;

    public TacGia() {
    }

    public TacGia(int ma, int namsinh, String ten, String quequan, String hinh) {
        this.ma = ma;
        this.namsinh = namsinh;
        this.ten = ten;
        this.quequan = quequan;
        this.hinh = hinh;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    
    
    
}
