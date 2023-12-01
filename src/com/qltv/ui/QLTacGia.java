/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qltv.ui;

import com.qltv.dao.TacGiaDAO;
import com.qltv.entity.TacGia;
import com.qltv.utils.MsgBox;
import com.qltv.utils.XImage;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class QLTacGia extends javax.swing.JPanel {

    List<TacGia> list;
    TacGiaDAO tgdao = new TacGiaDAO();
    int row = -1;
    String imagePath = "";
    /**
     * Creates new form Form_1
     */
    public QLTacGia() {
        initComponents();
        this.fillTable();
    }

    public void fillTable(){
        DefaultTableModel model = (DefaultTableModel) tblTacGia.getModel();
        model.setRowCount(0);
        try{
            List<TacGia> list = tgdao.SelectAll();
            for (TacGia dg : list) {
                Object[] row = {
                    dg.getMa(),
                    dg.getTen(),
                    dg.getNamsinh(),
                    dg.getQuequan()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu sách!");
            e.printStackTrace();
        }
        
    }
    
    void clickTable(){
        int i = tblTacGia.getSelectedRow();
        TacGia tg = list.get(i);
        if(i > -1){
            txtTen.setText(tblTacGia.getValueAt(i, 1)+"");
            txtNamSinh.setText(tblTacGia.getValueAt(i, 2)+"");
            txtQueQuan.setText(tblTacGia.getValueAt(i, 3)+"");
             this.hienThiHinhAnh(tg.getHinh());
                imagePath = tg.getHinh();
        }
    }
    
    private void insert() {
        TacGia model = getForm();
        try {
            tgdao.insert(model);
            this.fillTable();
            this.clearForm();
            MsgBox.alert(this, "Thêm tác giả mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm tác giả mới thất bại!");
            e.printStackTrace();
        }

    }

    private void update() {
        TacGia model = getForm1();
        try {
            tgdao.update(model);
            this.fillTable();
            MsgBox.alert(this, "Cập nhật tác giả thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật tác giả thất bại!");
            e.printStackTrace();
        }
    }

    private void delete() {

        int c = tblTacGia.getSelectedRow();
        int id = (int) tblTacGia.getValueAt(c, 0);
        tgdao.delete(id);
        this.fillTable();
        this.clearForm();
        MsgBox.alert(this, "Xóa thành công");
    }

    private void hienThiHinhAnh(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(imagePath)
                        .getImage()
                        .getScaledInstance(
                                lblHinh.getWidth(),
                                lblHinh.getHeight(),
                                Image.SCALE_DEFAULT));
        lblHinh.setIcon(imageIcon);
    }
    
    private void clearForm() {
        txtTen.setText("");
        txtNamSinh.setText("");
        txtQueQuan.setText("");
        this.hienThiHinhAnh(null);
    }

    private TacGia getForm() {
        TacGia cd = new TacGia();
        cd.setTen(txtTen.getText());
        cd.setNamsinh(Integer.parseInt(txtNamSinh.getText()+""));
        cd.setQuequan(txtQueQuan.getText());
        cd.setHinh(lblHinh.getToolTipText());
        return cd;
    }
    
    private TacGia getForm1() {
        TacGia cd = new TacGia();
        cd.setMa(Integer.parseInt(tblTacGia.getValueAt(tblTacGia.getSelectedRow(), 0)+""));
        cd.setTen(txtTen.getText());
        cd.setNamsinh(Integer.parseInt(txtNamSinh.getText()+""));
        cd.setQuequan(txtQueQuan.getText());
        cd.setHinh(lblHinh.getToolTipText());
        return cd;
    }
    
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
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(file.getName()); // giữ tên hình trong tooltip
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

        txtNamSinh = new com.qltv.swing.TextField();
        txtQueQuan = new com.qltv.swing.TextField();
        txtTen = new com.qltv.swing.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTacGia = new javax.swing.JTable();
        btnCapNhat = new com.qltv.swing.Button();
        btnMoi = new com.qltv.swing.Button();
        btnXoa = new com.qltv.swing.Button();
        btnThem = new com.qltv.swing.Button();
        txtTimKiem = new com.qltv.swing.TextField();
        lblTacGia = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNamSinh.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNamSinh.setLabelText("Năm sinh");
        add(txtNamSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 270, 50));

        txtQueQuan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtQueQuan.setLabelText("Quê quán");
        add(txtQueQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 270, 50));

        txtTen.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTen.setLabelText("Tên tác giả");
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });
        add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 270, 50));

        tblTacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã tác giả", "Tên tác giả", "Năm sinh", "Quê quán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTacGiaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTacGia);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, 410, 420));

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCapNhat.setPreferredSize(new java.awt.Dimension(90, 50));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, -1, -1));

        btnMoi.setText("Mới");
        btnMoi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnMoi.setPreferredSize(new java.awt.Dimension(90, 50));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, -1, -1));

        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoa.setPreferredSize(new java.awt.Dimension(90, 50));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, -1, -1));

        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem.setPreferredSize(new java.awt.Dimension(90, 50));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, -1, -1));

        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTimKiem.setLabelText("Tìm kiếm");
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 410, -1));

        lblTacGia.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        lblTacGia.setForeground(new java.awt.Color(153, 102, 0));
        lblTacGia.setText("TÁC GIẢ");
        add(lblTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        lblHinh.setText("jLabel2");
        lblHinh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 220, 280));
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void tblTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTacGiaMouseClicked
        // TODO add your handling code here:
        clickTable();
    }//GEN-LAST:event_tblTacGiaMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        JFileChooser filePane = new JFileChooser("logos");
        FileFilter filter = new FileNameExtensionFilter("Image Files", "gif", "jpeg", "jpg", "png");
        filePane.setFileFilter(filter);
        int ketQua = filePane.showSaveDialog(this);
        if (ketQua == JFileChooser.APPROVE_OPTION) {
            File file = filePane.getSelectedFile();
            imagePath = file.getPath();
            this.hienThiHinhAnh(imagePath);
        }
    }//GEN-LAST:event_lblHinhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.qltv.swing.Button btnCapNhat;
    private com.qltv.swing.Button btnMoi;
    private com.qltv.swing.Button btnThem;
    private com.qltv.swing.Button btnXoa;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblTacGia;
    private javax.swing.JTable tblTacGia;
    private com.qltv.swing.TextField txtNamSinh;
    private com.qltv.swing.TextField txtQueQuan;
    private com.qltv.swing.TextField txtTen;
    private com.qltv.swing.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
