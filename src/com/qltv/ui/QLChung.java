/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.qltv.ui;

import com.qltv.dao.KeSachDAO;
import com.qltv.dao.LoaiSachDAO;
import com.qltv.dao.NhaCungCapDAO;
import com.qltv.entity.DocGia;
import com.qltv.entity.KeSach;
import com.qltv.entity.LoaiSach;
import com.qltv.entity.NhaCungCap;
import com.qltv.entity.NhaXuatBan;
import com.qltv.utils.MsgBox;
import com.qltv.utils.XImage;
import com.qltv.utils.XValidate;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QLChung extends javax.swing.JPanel {

    LoaiSachDAO lsdao = new LoaiSachDAO();
    KeSachDAO ksdao = new KeSachDAO();
    NhaCungCapDAO nccdao = new NhaCungCapDAO();
    int i = -1;

    public QLChung() {
        initComponents();
        this.fillTableLoai();
        this.fillTableKeSach();
        this.fillTableNCC();
    }

    //--------------------------------------------------------------------------//
    
    // -----LOẠI SÁCH---- //
    
    // Đổ dữ liệu lên bảng
    private void fillTableLoai() {
        DefaultTableModel model = (DefaultTableModel) tblLoai.getModel();
        model.setRowCount(0);
        try {
            List<LoaiSach> list = lsdao.selectAll();
            for (LoaiSach dg : list) {
                Object[] row = {
                    dg.getMaLoai(),
                    dg.getTenLoai()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu loại sách!");
            e.printStackTrace();
        }
    }
    
    // Click vào bảng để hiện lên form
    private void clickTableLoai(){
        int i = tblLoai.getSelectedRow();
        if(i > -1){
            txtLoai.setText((String) tblLoai.getValueAt(i, 1));
        }
    }
    
    // Thêm
    private void insertLS(){
        LoaiSach ls = this.getFormLS();
        lsdao.insert(ls);
        this.fillTableLoai();
        this.clearFormLS();
        MsgBox.alert(this, "Thêm loại sách thành công");
    }
    
    // Cập nhật loại sách
    private void updateLS(){
        LoaiSach ls = this.getFormLS1();
        lsdao.update(ls);
        this.fillTableLoai();
        this.clearFormLS();
        MsgBox.alert(this, "Cập nhật loại sách thành công");
    }
    
    // Xóa
    private void deleteLS() {
        int c = tblLoai.getSelectedRow();
        int id = (int) tblLoai.getValueAt(c, 0);
        lsdao.delete(id);
        this.fillTableLoai();
        this.clearFormLS();
        MsgBox.alert(this, "Xóa thành công");
    }
    
    // Làm mới
        public void clearFormLS(){
            txtLoai.setText("");
    }
    
    // Lấy dữ liệu cho nút thêm
    private LoaiSach getFormLS(){
        LoaiSach ls = new LoaiSach();
        ls.setTenLoai(txtLoai.getText());
        return ls;
    }
    
    // Lấy dữ liệu cho nút cập nhật
    private LoaiSach getFormLS1(){
        LoaiSach ls = new LoaiSach();
        ls.setMaLoai((int) tblLoai.getValueAt(tblLoai.getSelectedRow(), 0));
        ls.setTenLoai(txtLoai.getText());
        return ls;
    }
        
    // ---------------------------------KỆ SÁCH-------------------------------- //
    
    // Đổ dữ liệu lên bảng
    public void fillTableKeSach(){
        DefaultTableModel model = (DefaultTableModel) tblKeSach.getModel();
        model.setRowCount(0);
        try{
            List<KeSach> list = ksdao.selectAll();
            for (KeSach dg : list) {
                Object[] row = {
                    dg.getMaKe(),
                    dg.getVitri()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu kệ sách!");
            e.printStackTrace();
        }
    }
    
    // Click vào bảng để hiện lên form
    private void clickTableKeSach(){
        int i = tblKeSach.getSelectedRow();
        if(i > -1){
            txtKeSach.setText((String) tblKeSach.getValueAt(i, 1));
        }
    }
    
    // Thêm
    private void insertKS(){
        KeSach ks = this.getFormKS();
        ksdao.insert(ks);
        this.fillTableKeSach();
        this.clearFormKS();
        MsgBox.alert(this, "Thêm kệ sách thành công");
    }
    
    // Sửa
    private void updateKS(){
        KeSach ks = this.getFormKS1();
        ksdao.update(ks);
        this.fillTableKeSach();
        this.clearFormKS();
        MsgBox.alert(this, "Cập nhật kệ sách thành công");
    }
    
    // Xóa
    private void deleteKS() {
        int c = tblKeSach.getSelectedRow();
        int id = (int) tblKeSach.getValueAt(c, 0);
        ksdao.delete(id);
        this.fillTableKeSach();
        this.clearFormKS();
        MsgBox.alert(this, "Xóa thành công");
    }
    
    // Làm mới
        public void clearFormKS(){
            txtKeSach.setText("");
    }
    
    // Lấy dữ liệu cho nút thêm
    private KeSach getFormKS(){
        KeSach ks = new KeSach();
        ks.setVitri(txtKeSach.getText());
        return ks;
    }
     
    // Lấy dữ liệu cho nút sửa
    private KeSach getFormKS1(){
        KeSach ks = new KeSach();
        int maks = (int) tblKeSach.getValueAt(tblKeSach.getSelectedRow(), 0);
        ks.setMaKe(maks);
        ks.setVitri(txtKeSach.getText());
        return ks;
    }
    
    // ---------------------------------NHÀ CUNG CẤP-------------------------- //
    
    // Đổ dữ liệu lên bảng
    public void fillTableNCC(){
        DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
        model.setRowCount(0);
        try{
            List<NhaCungCap> list = nccdao.selectAll();
            for (NhaCungCap ncc : list) {
                Object[] row = {
                    ncc.getMaNCC(),
                    ncc.getTenNCC()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu nhà cung cấp!");
            e.printStackTrace();
        }
    }
    
    // Click vào bảng để hiện lên form
    private void clickTableNCC(){
        int i = tblNCC.getSelectedRow();
        if(i > -1){
            txtTenNCC.setText((String) tblNCC.getValueAt(i, 1));
        }
    }
    
    // Thêm
    private void insertNCC(){
        NhaCungCap ncc = this.getFormNCC();
        nccdao.insert(ncc);
        this.fillTableNCC();
        this.clearFormNCC();
        MsgBox.alert(this, "Thêm nhà cung cấp thành công");
    }
    
    // Sửa
    private void updateNCC(){
        NhaCungCap ncc = this.getFormNCC1();
        nccdao.update(ncc);
        this.fillTableNCC();
        this.clearFormNCC();
        MsgBox.alert(this, "Cập nhật nhà cung cấp thành công");
    }
    
    // Xóa
    private void deleteNCC() {
        int c = tblNCC.getSelectedRow();
        int id = (int) tblNCC.getValueAt(c, 0);
        nccdao.delete(id);
        this.fillTableNCC();
        this.clearFormNCC();
        MsgBox.alert(this, "Xóa thành công");
    }
    
    // Làm mới
        public void clearFormNCC(){
            txtTenNCC.setText("");
    }
    
    // Lấy dữ liệu cho nút thêm
    private NhaCungCap getFormNCC(){
        NhaCungCap ncc = new NhaCungCap();
        ncc.setTenNCC(txtTenNCC.getText());
        return ncc;
    }
        
    // Lấy dữ liệu cho nút cập nhật
    private NhaCungCap getFormNCC1(){
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNCC((int) tblNCC.getValueAt(tblNCC.getSelectedRow(), 0));
        ncc.setTenNCC(txtTenNCC.getText());
        return ncc;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlChung = new com.qltv.swing.PanelBorder();
        tabs = new javax.swing.JTabbedPane();
        pnlLoaiSach = new javax.swing.JPanel();
        txtLoai = new com.qltv.swing.TextField();
        lblLoai = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoai = new javax.swing.JTable();
        btnThemLS = new com.qltv.swing.Button();
        btnSuaLS = new com.qltv.swing.Button();
        btnXoaLS = new com.qltv.swing.Button();
        btnMoiLS = new com.qltv.swing.Button();
        pnlKeSach = new javax.swing.JPanel();
        txtKeSach = new com.qltv.swing.TextField();
        lblKeSach = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKeSach = new javax.swing.JTable();
        btnThemKS = new com.qltv.swing.Button();
        btnSuaKS = new com.qltv.swing.Button();
        btnXoaKS = new com.qltv.swing.Button();
        btnMoiKS = new com.qltv.swing.Button();
        pnlNCC = new javax.swing.JPanel();
        txtTenNCC = new com.qltv.swing.TextField();
        lblTenNCC = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNCC = new javax.swing.JTable();
        btnThemNCC = new com.qltv.swing.Button();
        btnSuaNCC = new com.qltv.swing.Button();
        btnXoaNCC = new com.qltv.swing.Button();
        btnMoiNCC = new com.qltv.swing.Button();

        pnlChung.setBackground(new java.awt.Color(255, 255, 255));
        pnlChung.setPreferredSize(new java.awt.Dimension(1100, 650));

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setForeground(new java.awt.Color(153, 102, 0));
        tabs.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        pnlLoaiSach.setBackground(new java.awt.Color(255, 255, 255));
        pnlLoaiSach.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtLoai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtLoai.setLabelText("Tên loại");
        pnlLoaiSach.add(txtLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 119, 514, -1));

        lblLoai.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        lblLoai.setForeground(new java.awt.Color(102, 51, 0));
        lblLoai.setText("LOẠI SÁCH");
        pnlLoaiSach.add(lblLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 44, -1, -1));

        tblLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã loại", "Tên loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLoai);

        pnlLoaiSach.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 275, 701, 319));

        btnThemLS.setText("Thêm");
        btnThemLS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThemLS.setPreferredSize(new java.awt.Dimension(90, 50));
        btnThemLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLSActionPerformed(evt);
            }
        });
        pnlLoaiSach.add(btnThemLS, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 200, -1, -1));

        btnSuaLS.setText("Cập nhật");
        btnSuaLS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSuaLS.setPreferredSize(new java.awt.Dimension(90, 50));
        btnSuaLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLSActionPerformed(evt);
            }
        });
        pnlLoaiSach.add(btnSuaLS, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 200, -1, -1));

        btnXoaLS.setText("Xóa");
        btnXoaLS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoaLS.setPreferredSize(new java.awt.Dimension(90, 50));
        btnXoaLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLSActionPerformed(evt);
            }
        });
        pnlLoaiSach.add(btnXoaLS, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 200, -1, -1));

        btnMoiLS.setText("Mới");
        btnMoiLS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnMoiLS.setPreferredSize(new java.awt.Dimension(90, 50));
        btnMoiLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiLSActionPerformed(evt);
            }
        });
        pnlLoaiSach.add(btnMoiLS, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 200, -1, -1));

        tabs.addTab(" LOẠI SÁCH", pnlLoaiSach);

        pnlKeSach.setBackground(new java.awt.Color(255, 255, 255));
        pnlKeSach.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtKeSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtKeSach.setLabelText("Kệ sách");
        pnlKeSach.add(txtKeSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 119, 514, -1));

        lblKeSach.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        lblKeSach.setForeground(new java.awt.Color(102, 51, 0));
        lblKeSach.setText("KỆ SÁCH");
        pnlKeSach.add(lblKeSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        tblKeSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã kệ", "Vị trí"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKeSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKeSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKeSach);

        pnlKeSach.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 275, 701, 319));

        btnThemKS.setText("Thêm");
        btnThemKS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThemKS.setPreferredSize(new java.awt.Dimension(90, 50));
        btnThemKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKSActionPerformed(evt);
            }
        });
        pnlKeSach.add(btnThemKS, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 200, -1, -1));

        btnSuaKS.setText("Cập nhật");
        btnSuaKS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSuaKS.setPreferredSize(new java.awt.Dimension(90, 50));
        btnSuaKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKSActionPerformed(evt);
            }
        });
        pnlKeSach.add(btnSuaKS, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 200, -1, -1));

        btnXoaKS.setText("Xóa");
        btnXoaKS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoaKS.setPreferredSize(new java.awt.Dimension(90, 50));
        btnXoaKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKSActionPerformed(evt);
            }
        });
        pnlKeSach.add(btnXoaKS, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 200, -1, -1));

        btnMoiKS.setText("Mới");
        btnMoiKS.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnMoiKS.setPreferredSize(new java.awt.Dimension(90, 50));
        btnMoiKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiKSActionPerformed(evt);
            }
        });
        pnlKeSach.add(btnMoiKS, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 200, -1, -1));

        tabs.addTab("KỆ SÁCH", pnlKeSach);

        pnlNCC.setBackground(new java.awt.Color(255, 255, 255));
        pnlNCC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTenNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTenNCC.setLabelText("Tên nhà cung cấp");
        pnlNCC.add(txtTenNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 119, 514, -1));

        lblTenNCC.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        lblTenNCC.setForeground(new java.awt.Color(102, 51, 0));
        lblTenNCC.setText("TÊN NHÀ CUNG CẤP");
        pnlNCC.add(lblTenNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNCCMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblNCC);

        pnlNCC.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 275, 701, 319));

        btnThemNCC.setText("Thêm");
        btnThemNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThemNCC.setPreferredSize(new java.awt.Dimension(90, 50));
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });
        pnlNCC.add(btnThemNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 200, -1, -1));

        btnSuaNCC.setText("Cập nhật");
        btnSuaNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSuaNCC.setPreferredSize(new java.awt.Dimension(90, 50));
        btnSuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNCCActionPerformed(evt);
            }
        });
        pnlNCC.add(btnSuaNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 200, -1, -1));

        btnXoaNCC.setText("Xóa");
        btnXoaNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoaNCC.setPreferredSize(new java.awt.Dimension(90, 50));
        btnXoaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNCCActionPerformed(evt);
            }
        });
        pnlNCC.add(btnXoaNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 200, -1, -1));

        btnMoiNCC.setText("Mới");
        btnMoiNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnMoiNCC.setPreferredSize(new java.awt.Dimension(90, 50));
        btnMoiNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiNCCActionPerformed(evt);
            }
        });
        pnlNCC.add(btnMoiNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 200, -1, -1));

        tabs.addTab("NHÀ CUNG CẤP", pnlNCC);

        javax.swing.GroupLayout pnlChungLayout = new javax.swing.GroupLayout(pnlChung);
        pnlChung.setLayout(pnlChungLayout);
        pnlChungLayout.setHorizontalGroup(
            pnlChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        pnlChungLayout.setVerticalGroup(
            pnlChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlChung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlChung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1){
        clickTableLoai();
        }
    }//GEN-LAST:event_tblLoaiMouseClicked

    private void tblKeSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKeSachMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1){
            clickTableKeSach();
        }
    }//GEN-LAST:event_tblKeSachMouseClicked

    private void tblNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNCCMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1){
            clickTableNCC();
        }
    }//GEN-LAST:event_tblNCCMouseClicked

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtTenNCC)){
            insertNCC();
        }
    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void btnSuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNCCActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtTenNCC)){
            updateNCC();
        }
    }//GEN-LAST:event_btnSuaNCCActionPerformed

    private void btnXoaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNCCActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtTenNCC)){
            deleteNCC();
        }
    }//GEN-LAST:event_btnXoaNCCActionPerformed

    private void btnMoiNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiNCCActionPerformed
        // TODO add your handling code here:
        clearFormNCC();
    }//GEN-LAST:event_btnMoiNCCActionPerformed

    private void btnThemKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKSActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtKeSach)){
            insertKS();
        }
    }//GEN-LAST:event_btnThemKSActionPerformed

    private void btnSuaKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKSActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtTenNCC)){
            updateKS();
        }
    }//GEN-LAST:event_btnSuaKSActionPerformed

    private void btnXoaKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKSActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtTenNCC)){
            deleteKS();
        }
    }//GEN-LAST:event_btnXoaKSActionPerformed

    private void btnMoiKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiKSActionPerformed
        // TODO add your handling code here:
        clearFormKS();
    }//GEN-LAST:event_btnMoiKSActionPerformed

    private void btnThemLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLSActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtLoai)){
        insertLS();
        }
    }//GEN-LAST:event_btnThemLSActionPerformed

    private void btnSuaLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLSActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtLoai)){
                updateLS();
        }
    }//GEN-LAST:event_btnSuaLSActionPerformed

    private void btnXoaLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLSActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtTenNCC)){
            deleteLS();
        }
    }//GEN-LAST:event_btnXoaLSActionPerformed

    private void btnMoiLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiLSActionPerformed
        // TODO add your handling code here:
        clearFormLS();
    }//GEN-LAST:event_btnMoiLSActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.qltv.swing.Button btnMoiKS;
    private com.qltv.swing.Button btnMoiLS;
    private com.qltv.swing.Button btnMoiNCC;
    private com.qltv.swing.Button btnSuaKS;
    private com.qltv.swing.Button btnSuaLS;
    private com.qltv.swing.Button btnSuaNCC;
    private com.qltv.swing.Button btnThemKS;
    private com.qltv.swing.Button btnThemLS;
    private com.qltv.swing.Button btnThemNCC;
    private com.qltv.swing.Button btnXoaKS;
    private com.qltv.swing.Button btnXoaLS;
    private com.qltv.swing.Button btnXoaNCC;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblKeSach;
    private javax.swing.JLabel lblLoai;
    private javax.swing.JLabel lblTenNCC;
    private com.qltv.swing.PanelBorder pnlChung;
    private javax.swing.JPanel pnlKeSach;
    private javax.swing.JPanel pnlLoaiSach;
    private javax.swing.JPanel pnlNCC;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblKeSach;
    private javax.swing.JTable tblLoai;
    private javax.swing.JTable tblNCC;
    private com.qltv.swing.TextField txtKeSach;
    private com.qltv.swing.TextField txtLoai;
    private com.qltv.swing.TextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
}
