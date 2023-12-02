/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.entity;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhap {
    int ma,mapn,masach,soluong,gia,thanhtien;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(int ma, int mapn, int masach, int soluong, int gia, int thanhtien) {
        this.ma = ma;
        this.mapn = mapn;
        this.masach = masach;
        this.soluong = soluong;
        this.gia = gia;
        this.thanhtien = thanhtien;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getMapn() {
        return mapn;
    }

    public void setMapn(int mapn) {
        this.mapn = mapn;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
    
    
}
