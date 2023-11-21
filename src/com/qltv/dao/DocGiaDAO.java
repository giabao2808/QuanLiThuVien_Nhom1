/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;

import com.qltv.entity.DocGia;
import com.qltv.utils.XJdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DocGiaDAO{
    
//        ResultSet rs;
//        public static String SELECT_BY_ID_SQL = "SELECT * FROM DocGia WHERE MaDocGia=?";
//        

//
//	// @SuppressWarnings("null")
//	public static int themdocgia(DocGia ke) {
//		int i = -1;
//		String sql = "insert into DocGia (TenDocGia,GioTinh,DiaChi,Sđt) values(?,?,?,?)";
//
//		try {
//			
//			Connection conn = XJdbc.getConnection();
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setString(1, ke.getTenDG());
//			pstm.setBoolean(2, ke.isGioiTinh());
//			pstm.setString(3, ke.getDiaChi());
//			pstm.setString(4, ke.getSoDT());
////			pstm.setInt(5, 1);
//			i = pstm.executeUpdate();
//			conn.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			// dreturn null;
//		}
//
//		return i;
//	}
//
//	public static int suadocgia(DocGia ke) {
//		int i = -1;
//		String sql = "update DocGia set TenDocGia = ?,DiaChi =? ,GioiTinh = ?,Sđt = ? where MaDocGia = ?";
//
//		try {
//
//			Connection conn = XJdbc.getConnection();
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setString(1, ke.getTenDG());
//			pstm.setString(2, ke.getDiaChi());
//			pstm.setBoolean(3, ke.isGioiTinh());
//			pstm.setString(4, ke.getSoDT());
//			pstm.setInt(5, ke.getMaDG());
//			// System.out.println(ke.getViTri());
//			i = pstm.executeUpdate();
//			conn.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			// dreturn null;
//		}
//
//		return i;
//	}
//
//	public static int xoadocgia(DocGia ke) {
//		int i = -1;
//		String sql = "delete from DocGia where MaDocGia = ?";
//		try {
//
//			Connection conn = XJdbc.getConnection();
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setInt(1, ke.getMaDG());
//			// System.out.println(ke.getViTri());
//			i = pstm.executeUpdate();
//			conn.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			// dreturn null;
//		}
//
//		return i;
//	}
//	public DocGia timdocgia(int ma) {
//		String sql = "select * from DocGia where MaDocGia = ?";
//		DocGia docgia = new DocGia();
//		try {
//			Connection conn = XJdbc.getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, ma);
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()) {
//				docgia.setMaDG(rs.getInt(1));
//				docgia.setDiaChi(rs.getString(4));
//				docgia.setTenDG(rs.getString(2));
//				docgia.setGioiTinh(rs.getBoolean(3));
//				docgia.setSoDT(rs.getString(5));
//			}
//			return docgia; 
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null ; 
//		}
//		
//	}
//        
//    public List<DocGia> selectBySql(String sql, Object... args) {
//        List<DocGia> list = new ArrayList<>();
//        try {
//            try {
//                rs = (ResultSet) XJdbc.getConnection();
//                while (rs.next()) {
//                    DocGia entity = new DocGia();
//                    entity.setMaDG(rs.getInt(1));
//                    entity.setTenDG(rs.getString(2));
//                    entity.setGioiTinh(rs.getBoolean(3));
//                    entity.setDiaChi(rs.getString(4));
//                    entity.setSoDT(rs.getString(5));
//                    list.add(entity);
//                }
//            } finally {
//                rs.getStatement().getConnection().close();
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return list;
//    }
//    
//    public DocGia selectById(int id) {
//        List<DocGia> list = selectBySql(SELECT_BY_ID_SQL, id);
//        if(list.isEmpty()){
//            return null;
//        }
//        return list.get(0);
//        
//    }
    public static ResultSet rs = null ; // Trả về kết quả truy vấn
    public static String INSERT_SQL = "INSERT INTO DocGia (TenDocGia,GioiTinh,DiaChi,Sdt) VALUES (?,?,?,?)";
    public static String UPDATE_SQL = "UPDATE DocGia SET TenDocGia=?,GioiTinh=?,DiaChi=?,Sdt=? WHERE MaDocGia=?";
    public static String DELETE_SQL = "DELETE FROM DocGia WHERE MaDocGia=?";
    public static String DELETE_SQL_TTV = "DELETE FROM TheThuVien WHERE MaDocGia=?";
    public static String SELECT_ALL_SQL = "SELECT * FROM DocGia";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM DocGia WHERE MaDocGia=?";
    public static String SELECT_BY_NAME_SQL = "SELECT * FROM DocGia WHERE  TenDocGia like N'%' + N'?' + N'%' or Sdt like ?";

    public void insert(DocGia entity) {
        XJdbc.update(INSERT_SQL,
                entity.getTenDG(),
                entity.isGioiTinh(),
                entity.getDiaChi(),
                entity.getSoDT());
    }

    public void update(DocGia entity) {
        XJdbc.update(UPDATE_SQL,
                entity.getTenDG(),
                entity.isGioiTinh(),
                entity.getDiaChi(),
                entity.getSoDT(),
                entity.getMaDG());
    }
    
    public void delete(int key) {
        XJdbc.update(DELETE_SQL, key);
    }
    
    public List<DocGia> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public DocGia selectById(int id) {
        List<DocGia> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
        
    }

    protected ArrayList<DocGia> selectBySql(String sql, Object... args) {
        ArrayList<DocGia> list = new ArrayList<>();
        try {
            try {
                rs = XJdbc.query(sql);
                while (rs.next()) {
                    DocGia dg = new DocGia();
                    dg.setMaDG(rs.getInt("MaDocGia"));
                    dg.setTenDG(rs.getString("TenDocGia"));
                    dg.setGioiTinh(rs.getBoolean("GioiTinh"));
                    dg.setDiaChi(rs.getString("DiaChi"));
                    dg.setSoDT(rs.getString("Sdt"));
                    System.out.println(rs.getInt("MaDocGia"));
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
    
    
//    	public static ArrayList<DocGia> getdanhsachdocgia() {
//		try {
//			String sql = "select * from DocGia";
//			Connection conn = XJdbc.getConnection();
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			ResultSet rs = stmt.executeQuery();
//
//			ArrayList<DocGia> dsl = new ArrayList<>();
//			while (rs.next()) {
//				// System.out.println("Db connect");
//				DocGia dg = new DocGia();
//				dg.setMaDG(rs.getInt(1));
//				dg.setDiaChi(rs.getString(4));
//				dg.setTenDG(rs.getString(2));
//				dg.setGioiTinh(rs.getBoolean(3));
//				dg.setSoDT(rs.getString(5));
//				
//				dsl.add(dg);
//
//			}
//
//			return dsl;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return null;
//		}
//
//	}

    
}
