/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import com.qltv.entity.NhaCungCap;
import com.qltv.entity.NhanVien;
import com.qltv.entity.PhieuNhap;
import com.qltv.utils.XJdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class PhieuNhapDAO {
    public ResultSet rs;
    public String SELECT_ALL_SQL = "select * from PhieuNhap";
    public String SELECT_BY_NCC = "select TenNCC from NhaCungCap where MaNCC = ?";
    public String SELECT_BY_NV = "select TenNV from NhanVien where MaNV = ?";
    public String INSERT_SQL = "insert into PhieuNhap (MaNCC,MaNV,NgayNhap,TongTien) values (?,?,?,?)";
    public String SELECT_BY_NV_ID = "select MaNV from NhanVien where TenNV = N'?'";
    public String SELECT_BY_NCC_ID = "select MaNCC from NhaCungCap where TenNCC = N'?'";
    
    public void insert(PhieuNhap entity){
        XJdbc.update(INSERT_SQL, 
                entity.getMancc(),
                entity.getManv(),
                entity.getNgaynhap(),
                entity.getTong());
    }
    
    public ArrayList<PhieuNhap> selectAll(){
        return selectBySql(SELECT_ALL_SQL);
    }
    
    public ArrayList<String> selectNCC(int id){
        return selectByNCC(SELECT_BY_NCC, id);
    }
    
    public ArrayList<String> selectNXB(int id){
        return selectByNV(SELECT_BY_NV, id);
    }
    
    public int selectNV(String id){
         List<Integer> list = selectByNV1(SELECT_BY_NV_ID, id);
        if(!list.isEmpty()){
            return list.get(0);
        }
        else{
            return 0;
        }
    }
    
    public int selectNCC1(String id){
         List<Integer> list = selectByNCC1(SELECT_BY_NCC_ID, id);
        if(!list.isEmpty()){
            return list.get(0);
        }
        else{
            return 0;
        }
    }
    
    protected ArrayList<String> selectByNCC(String sql, Object...args){
        ArrayList<String> list = new ArrayList<>();
        try{
        try{
            rs = XJdbc.query(sql, args);
            while(rs.next()){
                String pn = rs.getString("TenNCC");
                list.add(pn);
            }
        }finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    protected ArrayList<Integer> selectByNCC1(String sql, Object... args) {
    ArrayList<Integer> list = new ArrayList<>();
    try {
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                int pn = rs.getInt("MaNCC");
                list.add(pn);
            }
        }
    } catch (SQLException ex) {
        // Xử lý ngoại lệ, bạn có thể ném một ngoại lệ hoặc ghi log
        // Nếu ném ngoại lệ, đảm bảo rằng ngoại lệ này được xử lý tại nơi gọi hàm
        ex.printStackTrace(); // Hoặc sử dụng logging framework như SLF4J để ghi log
    }
    return list;
}

    
    protected ArrayList<String> selectByNV(String sql, Object...args){
        ArrayList<String> list = new ArrayList<>();
        try{
        try{
            rs = XJdbc.query(sql, args);
            while(rs.next()){
                String pn = rs.getString("TenNV");
                list.add(pn);
            }
        }finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    
    protected ArrayList<Integer> selectByNV1(String sql, Object... args) {
    ArrayList<Integer> list = new ArrayList<>();
    try {
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                int pn = rs.getInt("MaNV");
                list.add(pn);
            }
        }
    } catch (SQLException ex) {
        // Xử lý ngoại lệ, bạn có thể ném một ngoại lệ hoặc ghi log
        // Nếu ném ngoại lệ, đảm bảo rằng ngoại lệ này được xử lý tại nơi gọi hàm
        ex.printStackTrace(); // Hoặc sử dụng logging framework như SLF4J để ghi log
    }
    return list;
}

    
    protected ArrayList<PhieuNhap> selectBySql(String sql, Object...args){
        ArrayList<PhieuNhap> list = new ArrayList<>();
        try{
        try{
            rs = XJdbc.query(sql, args);
            while(rs.next()){
                PhieuNhap pn = new PhieuNhap();
                pn.setMa(rs.getInt(1));
                pn.setMancc(rs.getInt(2));
                pn.setManv(rs.getInt(3));
                pn.setNgaynhap(rs.getDate(4));
                pn.setTong(rs.getInt(5));
                
                list.add(pn);
            }
        }finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
}
