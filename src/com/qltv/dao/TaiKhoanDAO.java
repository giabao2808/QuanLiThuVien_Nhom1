/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import com.qltv.entity.TaiKhoan;
import com.qltv.utils.XJdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TaiKhoanDAO {
    
    public static String SELECT_ALL = "select * from TaiKhoan";
    public static String SELECT_BY_ID = "select * from TaiKhoan where Username = ?";
    
    public ArrayList<TaiKhoan> SelectAll(){
        return SelectById(SELECT_ALL);
    }
    
    public TaiKhoan selectByIds(String id) {
        List<TaiKhoan> list = this.SelectById(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    protected ArrayList<TaiKhoan> SelectById(String sql, Object...args){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        ResultSet rs = XJdbc.query(sql, args);
        try {
            try {
                
                while (rs.next()) {
                    TaiKhoan s = new TaiKhoan();
                    s.setMatk(rs.getInt("MaTK"));
                    s.setUser(rs.getString("Username"));
                    s.setPass(rs.getString("Password"));
                    s.setQuyen(rs.getBoolean("Quyen"));
                    s.setManv(rs.getInt("MaNV"));
                list.add(s);
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
