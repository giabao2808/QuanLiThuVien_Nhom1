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
    /**
     * Creates new form QLChung
     */
    public QLChung() {
        initComponents();
        this.fillTableLoai();
        this.fillTableKeSach();
        this.fillTableNCC();
    }

    //LOẠI SÁCH
    private void fillTableLoai() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
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
    
    private void clickTableLoai(){
        int i = jTable1.getSelectedRow();
        if(i > -1){
            textField1.setText((String) jTable1.getValueAt(i, 1));
        }
    }
    
    private void insertLS(){
        LoaiSach ls = this.getFormLS();
        lsdao.insert(ls);
        this.fillTableLoai();
        this.clearFormLS();
        MsgBox.alert(this, "Thêm loại sách thành công");
    }
    
    private void updateLS(){
        LoaiSach ks = this.getFormLS();
        lsdao.update(ks);
        this.fillTableLoai();
        this.clearFormLS();
        MsgBox.alert(this, "Cập nhật loại sách thành công");
    }
    
    private void deleteLS() {
        int c = jTable1.getSelectedRow();
        int id = (int) jTable1.getValueAt(c, 0);
        lsdao.delete(id);
        this.fillTableLoai();
        this.clearFormLS();
        MsgBox.alert(this, "Xóa thành công");
    }
    
        public void clearFormLS(){
            textField1.setText("");
    }
    
    private LoaiSach getFormLS(){
        LoaiSach ls = new LoaiSach();
        ls.setMaLoai((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        ls.setTenLoai(textField1.getText());
        return ls;
    }
    
    
    //KỆ SÁCH
    public void fillTableKeSach(){
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
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
    
    private void clickTableKeSach(){
        int i = jTable2.getSelectedRow();
        if(i > -1){
            textField2.setText((String) jTable2.getValueAt(i, 1));
        }
    }
    
    private void insertKS(){
        KeSach ks = this.getFormKS();
        ksdao.insert(ks);
        this.fillTableKeSach();
        this.clearFormKS();
        MsgBox.alert(this, "Thêm kệ sách thành công");
    }
    
    private void updateKS(){
        KeSach ks = this.getFormKS();
        ksdao.update(ks);
        this.fillTableKeSach();
        this.clearFormKS();
        MsgBox.alert(this, "Cập nhật kệ sách thành công");
    }
    
    private void deleteKS() {
        int c = jTable2.getSelectedRow();
        int id = (int) jTable2.getValueAt(c, 0);
        ksdao.delete(id);
        this.fillTableKeSach();
        this.clearFormKS();
        MsgBox.alert(this, "Xóa thành công");
    }
    
        public void clearFormKS(){
            textField2.setText("");
    }
    
    private KeSach getFormKS(){
        KeSach ncc = new KeSach();
        ncc.setMaKe((int) jTable2.getValueAt(jTable2.getSelectedRow(), 0));
        ncc.setVitri(textField2.getText());
        return ncc;
    }
     
    
    //NHÀ CUNG CẤP
    public void fillTableNCC(){
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
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
    
    private void clickTableNCC(){
        int i = jTable3.getSelectedRow();
        if(i > -1){
            textField3.setText((String) jTable3.getValueAt(i, 1));
        }
    }
    
    private void insertNCC(){
        NhaCungCap ncc = this.getFormNCC();
        nccdao.insert(ncc);
        this.fillTableNCC();
        this.clearFormNCC();
        MsgBox.alert(this, "Thêm nhà cung cấp thành công");
    }
    
    private void updateNCC(){
        NhaCungCap ncc = this.getFormNCC();
        nccdao.update(ncc);
        this.fillTableNCC();
        this.clearFormNCC();
        MsgBox.alert(this, "Cập nhật nhà cung cấp thành công");
    }
    
    private void deleteNCC() {
        int c = jTable3.getSelectedRow();
        int id = (int) jTable3.getValueAt(c, 0);
        nccdao.delete(id);
        this.fillTableNCC();
        this.clearFormNCC();
        MsgBox.alert(this, "Xóa thành công");
    }
    
        public void clearFormNCC(){
            textField3.setText("");
    }
    
    private NhaCungCap getFormNCC(){
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNCC((int) jTable3.getValueAt(jTable3.getSelectedRow(), 0));
        ncc.setTenNCC(textField3.getText());
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

        panelBorder1 = new com.qltv.swing.PanelBorder();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        textField1 = new com.qltv.swing.TextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button1 = new com.qltv.swing.Button();
        button2 = new com.qltv.swing.Button();
        button3 = new com.qltv.swing.Button();
        button4 = new com.qltv.swing.Button();
        jPanel4 = new javax.swing.JPanel();
        textField2 = new com.qltv.swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        button5 = new com.qltv.swing.Button();
        button6 = new com.qltv.swing.Button();
        button7 = new com.qltv.swing.Button();
        button8 = new com.qltv.swing.Button();
        jPanel5 = new javax.swing.JPanel();
        textField3 = new com.qltv.swing.TextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        button9 = new com.qltv.swing.Button();
        button10 = new com.qltv.swing.Button();
        button11 = new com.qltv.swing.Button();
        button12 = new com.qltv.swing.Button();

        panelBorder1.setPreferredSize(new java.awt.Dimension(1100, 650));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(153, 102, 0));
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        textField1.setLabelText("Tên loại");
        jPanel1.add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 119, 514, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 51, 0));
        jLabel1.setText("LOẠI SÁCH");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 44, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 275, 701, 319));

        button1.setText("Thêm");
        button1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(90, 50));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel1.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 200, -1, -1));

        button2.setText("Cập nhật");
        button2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button2.setPreferredSize(new java.awt.Dimension(90, 50));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel1.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 200, -1, -1));

        button3.setText("Xóa");
        button3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button3.setPreferredSize(new java.awt.Dimension(90, 50));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel1.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 200, -1, -1));

        button4.setText("Mới");
        button4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button4.setPreferredSize(new java.awt.Dimension(90, 50));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        jPanel1.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 200, -1, -1));

        jTabbedPane1.addTab(" LOẠI SÁCH", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        textField2.setLabelText("Kệ sách");
        jPanel4.add(textField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 119, 514, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 51, 0));
        jLabel2.setText("KỆ SÁCH");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 275, 701, 319));

        button5.setText("Thêm");
        button5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button5.setPreferredSize(new java.awt.Dimension(90, 50));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });
        jPanel4.add(button5, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 200, -1, -1));

        button6.setText("Cập nhật");
        button6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button6.setPreferredSize(new java.awt.Dimension(90, 50));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });
        jPanel4.add(button6, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 200, -1, -1));

        button7.setText("Xóa");
        button7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button7.setPreferredSize(new java.awt.Dimension(90, 50));
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        jPanel4.add(button7, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 200, -1, -1));

        button8.setText("Mới");
        button8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button8.setPreferredSize(new java.awt.Dimension(90, 50));
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });
        jPanel4.add(button8, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 200, -1, -1));

        jTabbedPane1.addTab("KỆ SÁCH", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        textField3.setLabelText("Tên nhà cung cấp");
        jPanel5.add(textField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 119, 514, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 51, 0));
        jLabel3.setText("TÊN NHÀ CUNG CẤP");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 275, 701, 319));

        button9.setText("Thêm");
        button9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button9.setPreferredSize(new java.awt.Dimension(90, 50));
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });
        jPanel5.add(button9, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 200, -1, -1));

        button10.setText("Cập nhật");
        button10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button10.setPreferredSize(new java.awt.Dimension(90, 50));
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });
        jPanel5.add(button10, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 200, -1, -1));

        button11.setText("Xóa");
        button11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button11.setPreferredSize(new java.awt.Dimension(90, 50));
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });
        jPanel5.add(button11, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 200, -1, -1));

        button12.setText("Mới");
        button12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button12.setPreferredSize(new java.awt.Dimension(90, 50));
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });
        jPanel5.add(button12, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 200, -1, -1));

        jTabbedPane1.addTab("NHÀ CUNG CẤP", jPanel5);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
        clickTableLoai();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            clickTableKeSach();
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            clickTableNCC();
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        // TODO add your handling code here:
        insertNCC();
    }//GEN-LAST:event_button9ActionPerformed

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
        // TODO add your handling code here:
        updateNCC();
    }//GEN-LAST:event_button10ActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        // TODO add your handling code here:
        deleteNCC();
    }//GEN-LAST:event_button11ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        // TODO add your handling code here:
        clearFormNCC();
    }//GEN-LAST:event_button12ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        // TODO add your handling code here:
        insertKS();
    }//GEN-LAST:event_button5ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
        updateKS();
    }//GEN-LAST:event_button6ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        // TODO add your handling code here:
        deleteKS();
    }//GEN-LAST:event_button7ActionPerformed

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
        // TODO add your handling code here:
        clearFormKS();
    }//GEN-LAST:event_button8ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        insertLS();
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        updateLS();
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        deleteLS();
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        clearFormLS();
    }//GEN-LAST:event_button4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.qltv.swing.Button button1;
    private com.qltv.swing.Button button10;
    private com.qltv.swing.Button button11;
    private com.qltv.swing.Button button12;
    private com.qltv.swing.Button button2;
    private com.qltv.swing.Button button3;
    private com.qltv.swing.Button button4;
    private com.qltv.swing.Button button5;
    private com.qltv.swing.Button button6;
    private com.qltv.swing.Button button7;
    private com.qltv.swing.Button button8;
    private com.qltv.swing.Button button9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private com.qltv.swing.PanelBorder panelBorder1;
    private com.qltv.swing.TextField textField1;
    private com.qltv.swing.TextField textField2;
    private com.qltv.swing.TextField textField3;
    // End of variables declaration//GEN-END:variables
}
