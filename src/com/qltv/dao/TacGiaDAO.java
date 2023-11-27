/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import static com.qltv.dao.SachDAO.rs;
import com.qltv.entity.Sach;
import com.qltv.entity.TacGia;
import com.qltv.utils.XJdbc;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Admin
 */
public class TacGiaDAO {
    public static String SELECT_ALL = "select * from TacGia";
    
    public ArrayList<TacGia> SelectAll(){
        return SelectById(SELECT_ALL);
    }
    
    protected ArrayList<TacGia> SelectById(String sql, Object...args){
        ArrayList<TacGia> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    TacGia s = new TacGia();
                    s.setMa(rs.getInt(1));
                    s.setTen(rs.getString(2));
                    s.setNamsinh(rs.getInt(3));
                    s.setQuequan(rs.getString(4));
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
    
    public List<String> selectById() {
        String SELECT_ID = "select TenTacGia from TacGia";
        return selectByName( SELECT_ID);
    }
    
    protected ArrayList<String> selectByName(String sql, Object... args) {
        ArrayList<String> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    String ten = rs.getString("TenTacGia");
                    list.add(ten);
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
