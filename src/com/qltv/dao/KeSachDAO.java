/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import com.qltv.entity.KeSach;
import com.qltv.entity.LoaiSach;
import com.qltv.utils.XJdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KeSachDAO {
    
    public static ResultSet  rs;
    public static String INSERT_SQL = "insert into KeSach (MaKe) values (?,?)";
    public static String UPDATE_SQL = "update KeSach set MaKe = ? where MaKe = ?";
    public static String DELETE_SQL = "delete from KeSach where MaKe = ?";
    public static String SELECT_ALL_SQL = "select * from KeSach";
    
    public List<KeSach> selectAll(){
        return selectBySql(SELECT_ALL_SQL);
    }
    
    protected ArrayList<KeSach> selectBySql(String sql, Object... args) {
        ArrayList<KeSach> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    KeSach ks = new KeSach();
                    ks.setMaKe(rs.getInt(1));
                    ks.setVitri(rs.getString(2));
                    list.add(ks);
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
