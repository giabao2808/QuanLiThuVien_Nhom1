/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import com.qltv.entity.ChiTietPhieuNhap;
import com.qltv.utils.XJdbc;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhapDAO {

    public ResultSet rs;
    public String SELECT_BY_ID = "select * from ChiTietPhieuNhap where MaPN = ?";
    public String SELECT_ALL = "select * from ChiTietPhieuNhap";
    public String UPDATE_SQL = "update ChiTietPhieuNhap set MaPN = ?, MaSach = ?, Gia = ?, SoLuong = ?, ThanhTien = ? where MaCTPN= ?";

    public void update(ChiTietPhieuNhap entity) {
        XJdbc.update(UPDATE_SQL,
                entity.getMapn(),
                entity.getMasach(),
                entity.getGia(),
                entity.getSoluong(),
                entity.getThanhtien(),
                entity.getMa());
    }

    public List<ChiTietPhieuNhap> selectAll() {
        return selectBySql(SELECT_ALL);
    }

    public String convertToTenSach(int ma) {
        String ten = "";
        try {
            String sql = "select TenSach from Sach where MaSach = ?";
            try (ResultSet resultSet = XJdbc.query(sql, ma)) {
                if (resultSet.next()) {
                    ten = resultSet.getString("TenSach");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ten;
    }

    public List<ChiTietPhieuNhap> selectByIds(int id) {
        List<ChiTietPhieuNhap> list = this.selectBySql(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    protected List<ChiTietPhieuNhap> selectBySql(String sql, Object... args) {
        List<ChiTietPhieuNhap> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    ChiTietPhieuNhap entity = new ChiTietPhieuNhap();
                    entity.setMa(rs.getInt(1));
                    entity.setMapn(rs.getInt(2));
                    entity.setMasach(rs.getInt(3));
                    entity.setGia(rs.getInt(4));
                    entity.setSoluong(rs.getInt(5));
                    entity.setThanhtien(rs.getInt(6));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public int convertToMaSach(String tenNCC) {
        int maNCC = 0;
        try {
            String sql = "SELECT MaSach FROM Sach WHERE TenSach = ?";
            try (ResultSet resultSet = XJdbc.query(sql, tenNCC)) {
                if (resultSet.next()) {
                    maNCC = resultSet.getInt("MaSach");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return maNCC;
    }
}
