/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import com.qltv.entity.LoaiSach;
import com.qltv.entity.NhaCungCap;
import com.qltv.utils.XJdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhaCungCapDAO {
    
    public static ResultSet rs;
    public static String INSERT_SQL = "insert into NhaCungCap (TenNCC) values (?)";
    public static String UPDATE_SQL = "update NhaCungCap set TenNCC = ? where MaNCC = ?";
    public static String DELETE_SQL = "delete from NhaCungCap where MaNCC = ?";
    public static String SELECT_ALL_SQL = "select * from NhaCungCap";
    
    public void insert(NhaCungCap entity){
        XJdbc.update(INSERT_SQL, 
                    entity.getTenNCC());
    }
    
    public void update(NhaCungCap entity){
        XJdbc.update(UPDATE_SQL, 
                    entity.getTenNCC(),
                    entity.getMaNCC());
    }
    
    public void delete(int key) {
        XJdbc.update(DELETE_SQL, key);
    }
    
    public List<NhaCungCap> selectAll(){
        return selectById(SELECT_ALL_SQL);
    }
    
    public List<NhaCungCap> selectById(String sql, Object...args){
        ArrayList<NhaCungCap> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    NhaCungCap ncc = new NhaCungCap();
                    ncc.setMaNCC(rs.getInt("MaNCC"));
                    ncc.setTenNCC(rs.getString("TenNCC"));
                    list.add(ncc);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
//            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<String> selectById() {
        String SELECT_ID = "select TenNCC from NhaCungCap";
        return selectByName( SELECT_ID);
    }
    
    protected ArrayList<String> selectByName(String sql, Object... args) {
        ArrayList<String> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    String tenLoai = rs.getString("TenNCC");
                    list.add(tenLoai);
                                    }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
//            throw new RuntimeException(ex);
        }
        return list;
    }
    }

