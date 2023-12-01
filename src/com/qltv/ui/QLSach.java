/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qltv.ui;

import com.qltv.dao.*;
import com.qltv.entity.*;
import com.qltv.utils.Auth;
import com.qltv.utils.MsgBox;
import com.qltv.utils.XDate;
import com.qltv.utils.XImage;
import java.io.File;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class QLSach extends javax.swing.JPanel {

    SachDAO sdao = new SachDAO();
    LoaiSachDAO lsdao = new LoaiSachDAO();
    KeSachDAO ksdao = new KeSachDAO();
    TacGiaDAO tgdao = new TacGiaDAO();
    NhaXuatBanDAO nxbdao = new NhaXuatBanDAO();
    int row= -1;
    /**
     * Creates new form Form_1
     */
    public QLSach() {
        initComponents();
        this.fillTable();
        init();
    }

    void init(){
        this.fillComboBoxLoaiSach();
        this.fillComboBoxKeSach();
        this.fillComboBoxNXB();
        this.fillComboBoxTacGia();
    }
    
    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        try {
            List<Sach> list = sdao.selectAll();
            for (Sach dg : list) {
                Object[] row = {
                    dg.getMa(),
                    dg.getTen(),
                    dg.getMaLoai(),
                    dg.getMaNXB(),
                    dg.getMaTG(),
                    dg.getNam(),
                    dg.getSoluong(),
                    dg.getMaKe(),
                    dg.getGhichu()
                };
                model.addRow(row);
        
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu sách!");
            e.printStackTrace();
        }
        
    }
    
    private void fillComboBoxLoaiSach() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoai.getModel();
        model.removeAllElements();
//        cboLoai.removeAllItems();
            List<String> data = lsdao.selectById();
            for (String item : data) {
                cboLoai.addItem(item);
        }
    }
    
    private void fillComboBoxKeSach() {
        cboKeSach.removeAllItems();
            List<String> data = ksdao.selectById();
            for (String item : data) {
                cboKeSach.addItem(item);
        }
    }
    
    private void fillComboBoxTacGia() {
        cboTacGia.removeAllItems();
            List<String> data = tgdao.selectById();
            for (String item : data) {
                cboTacGia.addItem(item);
        }
    }
    
    private void fillComboBoxNXB() {
        cboNXB.removeAllItems();
            List<String> data = nxbdao.selectById();
            for (String item : data) {
                cboNXB.addItem(item);
        }
    }
    
    private void clickTableSach() {
        int i = tblSach.getSelectedRow();
        if (i > -1) {
            try {
                txtMa.setText(tblSach.getValueAt(i, 0) + "");
                txtTen.setText(tblSach.getValueAt(i, 1) + "");
                cboLoai.setSelectedItem(tblSach.getValueAt(i, 2));
                cboNXB.setSelectedItem(tblSach.getValueAt(i, 3));
                cboTacGia.setSelectedItem(tblSach.getValueAt(i, 4));
                txtNam.setText(tblSach.getValueAt(i, 5) + "");
                txtSoLuong.setText(tblSach.getValueAt(i,6 )+ "");
                cboKeSach.setSelectedItem(tblSach.getValueAt(i, 7));
                txtGhiChu.setToolTipText(tblSach.getValueAt(i, 8)+"");
            } catch (Exception e) {
                e.printStackTrace();
            }
            tabs.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọnvào bảng");
        }

    }
//    private void clickTable(){
//        int i = tblSach.getSelectedRow();
//        if(i > -1){
//            txt.setText(tblSach.getValueAt(i, 1));
//            
//        }
//    }
    
    private void insert() {
        Sach model = getForm();
        try {
            sdao.insert(model);
            this.fillTable();
            this.clearForm();
            MsgBox.alert(this, "Thêm sách mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm sách mới thất bại!");
            e.printStackTrace();
        }

    }

    private void update() {
        Sach model = getForm();
        try {
            sdao.update(model);
            this.fillTable();
            MsgBox.alert(this, "Cập nhật sách thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật sách thất bại!");
            e.printStackTrace();
        }
    }

    private void delete() {
        int c = tblSach.getSelectedRow();
        int id = (int) tblSach.getValueAt(c, 0);
        sdao.delete(id);
        this.fillTable();
        this.clearForm();
        MsgBox.alert(this, "Xóa thành công");
    }

    private void clearForm() {
        this.setForm(new Sach());
        this.row = -1;
//        this.updateStatus();
    }

    private void edit() {
        String macd = (String) tblSach.getValueAt(this.row, 0);
        Sach cd = sdao.selectById(macd);
        this.setForm(cd);
        tabs.setSelectedIndex(0);
//        this.updateStatus();
        
    }
    
    private void setForm(Sach cd) {
        txtMa.setText(cd.getMa());
        txtTen.setText(cd.getTen());
        cboLoai.setSelectedItem(lsdao.selectByLoai(cd.getMaLoai()));
        cboNXB.setSelectedItem(cd.getMaNXB());
        cboTacGia.setSelectedItem(cd.getMaTG());
        txtNam.setText(String.valueOf(cd.getNam()));
        txtSoLuong.setText(String.valueOf(cd.getSoluong()));
        cboKeSach.setSelectedItem(cd.getMaKe());
        txtGhiChu.setToolTipText(cd.getGhichu());
        lblAnh.setIcon(XImage.readIconCD("NoImage.png"));
        if (cd.getHinh() != null) {
            lblAnh.setToolTipText(cd.getHinh());
            lblAnh.setIcon(XImage.readIconCD(cd.getHinh()));
        }
    }

    private Sach getForm() {
        Sach s = new Sach();
        s.setMa(txtMa.getText());
        s.setTen(txtTen.getText());
        s.setMaLoai(lsdao.selectByLoai(String.valueOf(cboLoai.getSelectedItem()))+"");
        s.setMaNXB((String) cboNXB.getSelectedItem());
        s.setMaTG((String) cboTacGia.getSelectedItem());
        s.setNam(Integer.parseInt(txtNam.getText()));
        s.setSoluong(Integer.parseInt(txtSoLuong.getText()));
        s.setMaKe((String) cboKeSach.getSelectedItem());
        s.setGhichu(txtGhiChu.getToolTipText());
        s.setHinh(lblAnh.getToolTipText());
        return s;
    }

    private void first() {
        this.row = 0;
        this.edit();
    }

    private void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
        }
    }

    private void next() {
        if (this.row < tblSach.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
    }

    private void last() {
        this.row = tblSach.getRowCount() - 1;
        this.edit();
    }

//    private void updateStatus() {
//        boolean edit = (this.row >= 0);
//        boolean first = (this.row == 0);
//        boolean last = (this.row == tblSach.getRowCount() - 1);
//        // Trạng thái form
//        txtMaCD.setEditable(!edit);
//        btnThem.setEnabled(!edit);
//        btnSua.setEnabled(edit);
//        btnXoa.setEnabled(edit);
//
//        // Trạng thái điều hướng
//        btnFirst.setEnabled(edit && !first);
//        btnPrev.setEnabled(edit && !first);
//        btnNext.setEnabled(edit && !last);
//        btnLast.setEnabled(edit && !last);
//    }

    private void selectIcon() {
        JFileChooser fc = new JFileChooser("logos");
        FileFilter filter = new FileNameExtensionFilter("Image Files", "gif", "jpeg", "jpg", "png");
        fc.setFileFilter(filter);
        fc.setMultiSelectionEnabled(false);
        int kq = fc.showOpenDialog(fc);
        if (kq == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            XImage.saveIconCD(file); // lưu hình vào thư mục logos
            ImageIcon icon = XImage.readIconCD(file.getName()); // đọc hình từ logos
            lblAnh.setIcon(icon);
            lblAnh.setToolTipText(file.getName()); // giữ tên hình trong tooltip
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        pnlSach = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnAnh = new com.qltv.swing.Button();
        txtTen = new com.qltv.swing.TextField();
        txtNam = new com.qltv.swing.TextField();
        txtSoLuong = new com.qltv.swing.TextField();
        btnSua = new com.qltv.swing.Button();
        btnXoa = new com.qltv.swing.Button();
        btnMoi = new com.qltv.swing.Button();
        btnThem = new com.qltv.swing.Button();
        jLabel1 = new javax.swing.JLabel();
        cboLoai = new com.qltv.swing.Combobox();
        cboNXB = new com.qltv.swing.Combobox();
        cboKeSach = new com.qltv.swing.Combobox();
        cboTacGia = new com.qltv.swing.Combobox();
        txtGhiChu = new com.qltv.swing.TextAreaScroll();
        txtMa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new com.qltv.swing.TextField();
        btnFirst = new com.qltv.swing.Button();
        btnLast = new com.qltv.swing.Button();
        btnNext = new com.qltv.swing.Button();
        btnPre = new com.qltv.swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setForeground(new java.awt.Color(153, 102, 0));
        tabs.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tabs.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        pnlSach.setBackground(new java.awt.Color(255, 255, 255));
        pnlSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pnlSach.setPreferredSize(new java.awt.Dimension(1100, 608));
        pnlSach.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAnh.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAnh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ảnh", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 0, 18), new java.awt.Color(153, 102, 0))); // NOI18N
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });
        pnlSach.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 440, 440));

        btnAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qltv/icon/camera.png"))); // NOI18N
        btnAnh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAnh.setPreferredSize(new java.awt.Dimension(90, 50));
        pnlSach.add(btnAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, -1, -1));

        txtTen.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTen.setLabelText("Tên sách");
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });
        pnlSach.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 570, -1));

        txtNam.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtNam.setLabelText("Năm sản xuất");
        pnlSach.add(txtNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 270, 50));

        txtSoLuong.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSoLuong.setLabelText("Số lượng");
        pnlSach.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 270, 50));

        btnSua.setText("Cập nhật");
        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSua.setPreferredSize(new java.awt.Dimension(90, 50));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlSach.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 510, -1, -1));

        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoa.setPreferredSize(new java.awt.Dimension(90, 50));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlSach.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 510, -1, -1));

        btnMoi.setText("Mới");
        btnMoi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnMoi.setPreferredSize(new java.awt.Dimension(90, 50));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        pnlSach.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 510, -1, -1));

        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThem.setPreferredSize(new java.awt.Dimension(90, 50));
        btnThem.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlSach.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, -1, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 102, 0));
        jLabel1.setText("SÁCH");
        pnlSach.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        cboLoai.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboLoai.setLabeText("Loại sách");
        cboLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiActionPerformed(evt);
            }
        });
        pnlSach.add(cboLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 270, -1));

        cboNXB.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboNXB.setLabeText("Nhà xuất bản");
        pnlSach.add(cboNXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 270, 50));

        cboKeSach.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboKeSach.setLabeText("Kệ sách");
        pnlSach.add(cboKeSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 270, 50));

        cboTacGia.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboTacGia.setLabeText("Tác giả");
        pnlSach.add(cboTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, 270, -1));

        txtGhiChu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtGhiChu.setLabelText("Ghi chú");
        pnlSach.add(txtGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, 570, 80));

        txtMa.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMa.setForeground(new java.awt.Color(153, 153, 153));
        txtMa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMa.setText("Mã");
        pnlSach.add(txtMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        tabs.addTab("THÔNG TIN", pnlSach);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKiem.setForeground(new java.awt.Color(153, 102, 0));
        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTimKiem.setLabelText("Tìm kiếm");
        jPanel2.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 21, 415, -1));

        btnFirst.setText("|<");
        btnFirst.setPreferredSize(new java.awt.Dimension(60, 41));
        jPanel2.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, -1, -1));

        btnLast.setText(">|");
        btnLast.setPreferredSize(new java.awt.Dimension(60, 41));
        jPanel2.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 100, -1, -1));

        btnNext.setText(">>");
        btnNext.setPreferredSize(new java.awt.Dimension(60, 41));
        jPanel2.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 100, -1, -1));

        btnPre.setText("<<");
        btnPre.setPreferredSize(new java.awt.Dimension(60, 41));
        jPanel2.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, -1, -1));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(153, 102, 0))); // NOI18N
        jScrollPane2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Mã loại", "Mã NXB", "Mã tác giả", "Năm xuất bản", "Số lượng", "Mã kệ", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSach.setGridColor(new java.awt.Color(153, 153, 153));
        tblSach.setSelectionBackground(new java.awt.Color(102, 102, 255));
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSach);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 1020, 400));

        tabs.addTab("DANH SÁCH", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseClicked
        // TODO add your handling code here:
        clickTableSach();
    }//GEN-LAST:event_tblSachMouseClicked

    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiActionPerformed

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        // TODO add your handling code here:
        selectIcon();
    }//GEN-LAST:event_lblAnhMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.qltv.swing.Button btnAnh;
    private com.qltv.swing.Button btnFirst;
    private com.qltv.swing.Button btnLast;
    private com.qltv.swing.Button btnMoi;
    private com.qltv.swing.Button btnNext;
    private com.qltv.swing.Button btnPre;
    private com.qltv.swing.Button btnSua;
    private com.qltv.swing.Button btnThem;
    private com.qltv.swing.Button btnXoa;
    private com.qltv.swing.Combobox cboKeSach;
    private com.qltv.swing.Combobox cboLoai;
    private com.qltv.swing.Combobox cboNXB;
    private com.qltv.swing.Combobox cboTacGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JPanel pnlSach;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblSach;
    private com.qltv.swing.TextAreaScroll txtGhiChu;
    private javax.swing.JTextField txtMa;
    private com.qltv.swing.TextField txtNam;
    private com.qltv.swing.TextField txtSoLuong;
    private com.qltv.swing.TextField txtTen;
    private com.qltv.swing.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
