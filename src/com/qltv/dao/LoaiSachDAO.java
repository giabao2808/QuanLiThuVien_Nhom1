/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.dao;
import static com.qltv.dao.NhaXuatBanDAO.DELETE_SQL;
import static com.qltv.dao.NhaXuatBanDAO.INSERT_SQL;
import static com.qltv.dao.NhaXuatBanDAO.SELECT_ALL_SQL;
import static com.qltv.dao.NhaXuatBanDAO.SELECT_BY_ID_SQL;
import static com.qltv.dao.NhaXuatBanDAO.UPDATE_SQL;
import static com.qltv.dao.NhaXuatBanDAO.rs;
import com.qltv.entity.LoaiSach;
import com.qltv.entity.NhaXuatBan;
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
    public String INSERT_SQL = "insert into Loai(TenLoai) values (?,?)";
    public String UPDATE_SQL = "update Loai set TenLoai = ? where MaLoai = ?";
    public String DELETE_SQL = "delete from Loai where MaLoai = ? ";
    public String SELECT_ALL = "select * from Loai";
    
    public void insert(NhaXuatBan entity) {
        XJdbc.update(INSERT_SQL,
                entity.getTen(),
                entity.getDiachi(),
                entity.getSdt(),
                entity.getHinh());
    }

    public void update(NhaXuatBan entity) {
        XJdbc.update(UPDATE_SQL,
                entity.getTen(),
                entity.getDiachi(),
                entity.getSdt(),
                entity.getHinh(),
                entity.getMa());
    }

    public void delete(int key) {
        XJdbc.update(DELETE_SQL, key);
    }

    public List<LoaiSach> selectAll() {
        return selectBySql(SELECT_ALL);
    }

    public LoaiSach selectById(String id) {
        List<LoaiSach> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
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
}
