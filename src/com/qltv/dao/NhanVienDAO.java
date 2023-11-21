/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import com.qltv.entity.NhanVien;
import com.qltv.utils.XJdbc;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhanVienDAO {
    public ResultSet rs;
    public static String SELECT_ALL = "select * from NhanVien";
    
    public ArrayList<NhanVien> selectAll(){
        return selectBySql(SELECT_ALL);
    }
    
    protected ArrayList<NhanVien> selectBySql(String sql, Object... args) {
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    NhanVien dg = new NhanVien();
                    dg.setMa(rs.getInt(1));
                    dg.setTen(rs.getString(2));
                    dg.setNam(rs.getString(3));
                    dg.setGiotinh(rs.getBoolean(4));
                    dg.setDiachi(rs.getString(5));
                    dg.setSdt(rs.getString(6));
                    list.add(dg);
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
