/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuNhap {
    int ma,mancc,manv,tong;
    Date ngaynhap;

    public PhieuNhap() {
    }

    public PhieuNhap(int ma, int mancc, int manv, Date ngaynhap, int tong) {
        this.ma = ma;
        this.mancc = mancc;
        this.manv = manv;
        this.ngaynhap = ngaynhap;
        this.tong = tong;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public Date getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(Date ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public int getTong() {
        return tong;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }
    
    
}
