/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import static com.qltv.dao.SachDAO.rs;
import com.qltv.entity.Sach;
import com.qltv.entity.TacGia;
import com.qltv.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Admin
 */
public class TacGiaDAO {
    
    public static ResultSet  rs;
    public static String INSERT_SQL = "insert into TacGia (TenTacGia, NamSinh, QueQuan, Hinh) values (?,?,?,?)";
    public static String UPDATE_SQL = "update TacGia set TenTacGia = ?, NamSinh = ?, QueQuan = ?, Hinh = ? where MaTacGia = ?";
    public static String DELETE_SQL = "delete from TacGia where MaTacGia = ?";
    public static String SELECT_ALL = "select * from TacGia";
    public static String SELECT_BY_ID = "select * from TacGia where MaTacGia = ?";
    
    public void insert(TacGia entity){
        XJdbc.update(INSERT_SQL, 
                    entity.getTen(),
                    entity.getNamsinh(),
                    entity.getQuequan(),
                    entity.getHinh());
    }
    
    public void update(TacGia entity){
        XJdbc.update(UPDATE_SQL, 
                    entity.getTen(),
                    entity.getNamsinh(),
                    entity.getQuequan(),
                    entity.getHinh(),
                    entity.getMa());
    }
    
    public void delete(int key) {
        XJdbc.update(DELETE_SQL, key);
    }
    
    public ArrayList<TacGia> SelectAll(){
        return SelectById(SELECT_ALL);
    }
    
    public TacGia selectByIds(int id) {
        List<TacGia> list = SelectById(SELECT_BY_ID, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
        
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
