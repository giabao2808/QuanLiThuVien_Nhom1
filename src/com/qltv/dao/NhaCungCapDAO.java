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
    public static String INSERT_SQL = "insert into NhaCungCap (TenNCC) values (?,?)";
    public static String UPDATE_SQL = "update NhaCungCap set TenNCC = ? where MaNCC = ?";
    public static String DELETE_SQL = "delete from NhaCungCap";
    public static String SELECT_ALL_SQL = "select * from NhaCungCap";
    
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
                    ncc.setMaNCC(rs.getInt(1));
                    ncc.setTenNCC(rs.getString(2));
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
    }
}
