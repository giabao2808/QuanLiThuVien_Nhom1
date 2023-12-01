/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import static com.qltv.dao.SachDAO.SELECT_BY_LOAI;
import com.qltv.entity.LoaiSach;
import com.qltv.utils.XJdbc;
    import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoaiSachDAO {
    public ResultSet rs;
    public String INSERT_SQL = "insert into Loai(TenLoai) values (?)";
    public String UPDATE_SQL = "update Loai set TenLoai = ? where MaLoai = ?";
    public String DELETE_SQL = "delete from Loai where MaLoai = ? ";
    public String SELECT_ALL = "select * from Loai";
    public String SELECT_BY_ID = "select MaLoai from Loai where TenLoai like N'?'";
    
    public void insert(LoaiSach entity){
        XJdbc.update(INSERT_SQL, 
                    entity.getTenLoai());
    }
    
    public void update(LoaiSach entity){
        XJdbc.update(UPDATE_SQL, 
                    entity.getTenLoai(),
                    entity.getMaLoai());
    }
    
    public void delete(int key) {
        XJdbc.update(DELETE_SQL, key);
    }
    

    public List<LoaiSach> selectAll() {
        return selectBySql(SELECT_ALL);
    }

    public List<String> selectById() {
        String SELECT_ID = "select TenLoai from Loai";
        return selectByName( SELECT_ID);
    }
    
    public int selectByLoai(String key) {
        List<Integer> list = selectBy(SELECT_BY_ID, key);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected ArrayList<LoaiSach> selectBySql(String sql, Object... args) {
        ArrayList<LoaiSach> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    LoaiSach dg = new LoaiSach();
                    dg.setMaLoai(rs.getInt(1));
                    dg.setTenLoai(rs.getString(2));
                    
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
    
    protected ArrayList<String> selectByName(String sql, Object... args) {
        ArrayList<String> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    String tenLoai = rs.getString("TenLoai");
                    list.add(tenLoai);
                                    }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
//            System.out.println(ex);
                ex.printStackTrace();
//            throw new RuntimeException(ex);
        }
        return list;
    }
    
    protected ArrayList<Integer> selectBy(String sql, Object... args) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    int tenLoai = rs.getInt("MaLoai");
                    list.add(tenLoai);
                                    }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
//            System.out.println(ex);
                ex.printStackTrace();
//            throw new RuntimeException(ex);
        }
        return list;
    }
    
    
    
//    public ArrayList<LoaiSach> selectByLoaiSach() {
//        String sql= "Select * from Loai";
//        return this.selectBySql(sql);
//    }
}
