/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qltv.ui;

import com.qltv.dao.DocGiaDAO;
import com.qltv.dao.TheThuVienDAO;
import com.qltv.entity.DocGia;
import com.qltv.entity.TheThuVien;
import com.qltv.utils.MsgBox;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class QLDocGia extends javax.swing.JPanel {

    DocGiaDAO dgdao;
    TheThuVienDAO ttvdao;
    int row = -1;
    /**
     * Creates new form Form_1
     */
    public QLDocGia() {
        initComponents();
        this.fillTableDG();
        this.fillTableTV();
    }

    // Form ĐỘC GIẢ
    
    //Tải dữ liệu
    private void fillTableDG() {
        DefaultTableModel model = (DefaultTableModel) tblDSDocGia.getModel();
        model.setRowCount(0);
        try {
            List<DocGia> list = dgdao.selectAll();
            for (DocGia dg : list) {
                Object[] row = {
                    dg.getMaDG(),
                    dg.getTenDG(),
                    dg.isGioiTinh() ? "Nam" : "Nữ",
                    dg.getDiaChi(),
                    dg.getSoDT()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu nhân viên!");
            System.out.println(e);
        }
    }
    
    //Tải dữ liệu lên bảng Thẻ thư viện
    private void fillTableTV() {
        DefaultTableModel model = (DefaultTableModel) tblDSTheTV.getModel();
        model.setRowCount(0);
        try {
            List<TheThuVien> list = ttvdao.selectAll();
            for (TheThuVien tv : list) {
                Object[] row = {
                    tv.getMaTheThuVien(),
                    tv.getNgayBatDau(),
                    tv.getNgayKetThuc(),
                    tv.getGhiChu(),
                    tv.getMadocgia()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu nhân viên!");
            e.printStackTrace();
        }
    }
    
//    private void insert() {
//        DocGia dg = this.getForm();
////        String mk2 = new String(txtMatKhau2.getText());
////        if (!mk2.equals(dg.getMatKhau())) {
////            MsgBox.alert(this, "Xác nhận mật khẩu không đúng!");
////        } else {
//            try {
//                dgdao.insert(dg); // thêm mới
//                this.fillTableDG(); // đỗ lại bảng
//                this.clearForm(); // xóa trắng form
//                MsgBox.alert(this, "Thêm độc giả mới thành công!");
//            } catch (Exception e) {
//                MsgBox.alert(this, "Thêm độc giả mới thất bại!");
//                e.printStackTrace();
//            }
////        }
//    }
//    
//    private void update() {
//        DocGia nv = this.getForm();
////        String mk2 = new String(txtMatKhau2.getText());
////        if (!mk2.equals(nv.getMatKhau())) {
////            MsgBox.alert(this, "Xác nhận mật khẩu không đúng!");
////        } else {
//            try {
//                dgdao.update(nv); // cập nhật
//                this.fillTableDG();// đổ lại bảng
//                MsgBox.alert(this, "Cập nhật nhân viên thành công!");
//            } catch (Exception e) {
//                MsgBox.alert(this, "Cập nhật nhân viên thất bại!");
//                e.printStackTrace();
//            }
////        }
//    }
//    
//    private void delete() {
////        if (!Auth.isManager()) {
////            MsgBox.alert(this, "Chỉ trưởng phòng mới được phép xóa nhân viên!");
////        } else {
//            String manv = txtMaDG.getText();
////            if (manv.equals(Auth.user.getMaNV())) {
////                MsgBox.alert(this, "Bạn không được phép xóa chính mình!");
////            } else if (MsgBox.confirm(this, "Bạn có thực sự muốn xóa nhân viên này không?")) {
//                try {
//                    dgdao.delete(manv);
//                    this.fillTableDG();
//                    this.clearForm();
//                    MsgBox.alert(this, "Xóa độc giả thành công!");
//                } catch (Exception e) {
//                    MsgBox.alert(this, "Xóa độc giả thất bại!");
//                    e.printStackTrace();
//                }
////            }
////        }
//    }
//    
//    private void clearForm() {
//        DocGia nv = new DocGia();
//        this.setForm(nv);
//        this.row = -1;
////        this.updateStatus();
//    }
//    
//    private void edit() {
//        try{
//        String manv = (String) tblDSDocGia.getValueAt(this.row, 0);
//        DocGia nv = dgdao.selectById(manv);
//        
//        if(nv != null){
//        this.setForm(nv);
//        jTabbedPane1.setSelectedIndex(0);
////        this.updateStatus();
//            
//        }
//        }catch(Exception e){
//            System.out.println(e);
//        }
//    }
//    
//    private void setForm(DocGia dg) {
//        txtTenDG.setText(dg.getTenDG());
//        rdoNu.setSelected(!dg.isGioiTinh());
//        rdoNam.setSelected(dg.isGioiTinh());
//        txtDiaChi.setText(dg.getDiaChi());
//        txtSoDT.setText(dg.getSoDT());
//
//    }
//    
//    private DocGia getForm() {
//        DocGia nv = new DocGia();
//        nv.setTenDG(txtTenDG.getText());
//        nv.setGioiTinh(rdoNam.isSelected());
//        nv.setDiaChi(txtDiaChi.getText());
//        nv.setSoDT(txtSoDT.getText());
//        return nv;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGioiTinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtMaDG = new com.qltv.swing.TextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        button8 = new com.qltv.swing.Button();
        button7 = new com.qltv.swing.Button();
        button5 = new com.qltv.swing.Button();
        button6 = new com.qltv.swing.Button();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtTenDG = new com.qltv.swing.TextField();
        txtDiaChi = new com.qltv.swing.TextField();
        txtSoDT = new com.qltv.swing.TextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        button1 = new com.qltv.swing.Button();
        button2 = new com.qltv.swing.Button();
        button3 = new com.qltv.swing.Button();
        button4 = new com.qltv.swing.Button();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSDocGia = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDSTheTV = new javax.swing.JTable();
        textField5 = new com.qltv.swing.TextField();

        setPreferredSize(new java.awt.Dimension(1100, 650));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 650));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(153, 102, 0));
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thẻ thư viện", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(153, 102, 0))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooser2.setForeground(new java.awt.Color(153, 153, 153));
        jDateChooser2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel4.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 300, 40));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Ngày kết thúc");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 130, -1));

        jDateChooser3.setForeground(new java.awt.Color(153, 153, 153));
        jDateChooser3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel4.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 310, 40));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Ngày bắt đầu");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 120, -1));

        txtMaDG.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMaDG.setLabelText("Mã độc giả");
        jPanel4.add(txtMaDG, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 310, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 300, 40));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Ghi chú");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 70, -1));

        button8.setText("Thêm");
        button8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button8.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel4.add(button8, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, -1, -1));

        button7.setText("Cập nhật");
        button7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button7.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel4.add(button7, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, -1, -1));

        button5.setText("Mới");
        button5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button5.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel4.add(button5, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, -1, -1));

        button6.setText("Xóa");
        button6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button6.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel4.add(button6, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 102, 0));
        jLabel5.setText("ĐỘC GIẢ");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Độc giả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(153, 102, 0))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTenDG.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTenDG.setLabelText("Tên độc giả");
        txtTenDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDGActionPerformed(evt);
            }
        });
        jPanel10.add(txtTenDG, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 317, -1));

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDiaChi.setLabelText(" Địa chỉ");
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        jPanel10.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 153, 317, -1));

        txtSoDT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSoDT.setLabelText("Số điện thoại");
        jPanel10.add(txtSoDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 57, 317, -1));

        rdoNam.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(153, 153, 153));
        rdoNam.setText("Nam");
        jPanel10.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 176, 60, -1));

        rdoNu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(153, 153, 153));
        rdoNu.setText("Nữ");
        jPanel10.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 176, -1, -1));

        button1.setText("Thêm");
        button1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel10.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, -1, -1));

        button2.setText("Cập nhật");
        button2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button2.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel10.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 80, -1, -1));

        button3.setText("Xóa");
        button3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button3.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel10.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 130, -1, -1));

        button4.setText("Mới");
        button4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button4.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel10.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Giới tính");
        jPanel10.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 153, 70, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(416, 416, 416))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("ĐỘC GIẢ", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách độc giả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(153, 102, 0))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDSDocGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã độc giả", "Họ & tên", "Giới tính", "Địa chỉ", "Số điện thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDSDocGia);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 900, 177));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 960, 230));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách thẻ thư viện", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(153, 102, 0))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDSTheTV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã thẻ", "Ngày bắt đầu", "Ngày kết thúc", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblDSTheTV);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 900, 177));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 960, 237));

        textField5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        textField5.setLabelText("Tìm kiếm");
        jPanel5.add(textField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 340, -1));

        jTabbedPane1.addTab("DANH SÁCH", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtTenDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDGActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinh;
    private com.qltv.swing.Button button1;
    private com.qltv.swing.Button button2;
    private com.qltv.swing.Button button3;
    private com.qltv.swing.Button button4;
    private com.qltv.swing.Button button5;
    private com.qltv.swing.Button button6;
    private com.qltv.swing.Button button7;
    private com.qltv.swing.Button button8;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblDSDocGia;
    private javax.swing.JTable tblDSTheTV;
    private com.qltv.swing.TextField textField5;
    private com.qltv.swing.TextField txtDiaChi;
    private com.qltv.swing.TextField txtMaDG;
    private com.qltv.swing.TextField txtSoDT;
    private com.qltv.swing.TextField txtTenDG;
    // End of variables declaration//GEN-END:variables
}
