/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.entity;

import java.util.Date;
import org.bridj.cpp.com.OLEAutomationLibrary;

/**
 *
 * @author Admin
 */
public class TheThuVien {
    private int maTheThuVien;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private String ghiChu;
	private int madocgia;

    public TheThuVien(int maTheThuVien, Date ngayBatDau, Date ngayKetThuc, String ghiChu, int madocgia) {
        this.maTheThuVien = maTheThuVien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.ghiChu = ghiChu;
        this.madocgia = madocgia;
    }

    public int getMaTheThuVien() {
        return maTheThuVien;
    }

    public void setMaTheThuVien(int maTheThuVien) {
        this.maTheThuVien = maTheThuVien;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getMadocgia() {
        return madocgia;
    }

    public void setMadocgia(int madocgia) {
        this.madocgia = madocgia;
    }

	

//	@Override
//	public String toString() {
//		return "TheThuVien [MaTheThuVien=" + MaTheThuVien + ", NgayBatDau=" + NgayBatDau + ", NgayKetThuc="
//				+ NgayKetThuc + ", GhiChu=" + GhiChu + ", madocgia=" + madocgia + ", tendocgia=" + tendocgia + "]";
//	}

    public TheThuVien() {
    }
}
