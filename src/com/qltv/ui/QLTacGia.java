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
import javax.swing.JTable;
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
        viewTable();
    }

    
     void viewTable() {
        tblTacGia.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblTacGia.getColumnModel().getColumn(1).setPreferredWidth(160);
        tblTacGia.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblTacGia.getColumnModel().getColumn(3).setPreferredWidth(50);
//        tblTacGia.getColumnModel().getColumn(4).setPreferredWidth(80);
//        tblTacGia.getColumnModel().getColumn(5).setPreferredWidth(60);
//        tblTacGia.getColumnModel().getColumn(6).setPreferredWidth(60);
//        tblTacGia.getColumnModel().getColumn(7).setPreferredWidth(60);
        tblTacGia.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tblTacGia = new javax.swing.JTable();
        lblTacGia = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTen = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNamSinh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtQueQuan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tblTacGia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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
        tblTacGia.setGridColor(new java.awt.Color(255, 255, 255));
        tblTacGia.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblTacGia.setSelectionForeground(new java.awt.Color(204, 153, 0));
        tblTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTacGiaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTacGia);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 370, 460));

        lblTacGia.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        lblTacGia.setForeground(new java.awt.Color(153, 102, 0));
        lblTacGia.setText("TÁC GIẢ");
        add(lblTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 250, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel1.setText("Tên tác giả");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 110, -1));

        txtNamSinh.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        txtNamSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtNamSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 250, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel2.setText("Năm sinh");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 110, -1));

        txtQueQuan.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        txtQueQuan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtQueQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 250, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel3.setText("Quê quán");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 110, -1));

        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 300, 330));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel4.setText("Ảnh");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 630, 400));

        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        add(txtTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 370, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel5.setText("Thông tin");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 100, -1));

        jButton1.setBackground(new java.awt.Color(153, 102, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jButton1.setText("Sửa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, 90, 30));

        jButton2.setBackground(new java.awt.Color(153, 102, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jButton2.setText("Xóa");
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, 90, 30));

        jButton3.setBackground(new java.awt.Color(153, 102, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jButton3.setText("Mới");
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 570, 90, 30));

        jButton4.setBackground(new java.awt.Color(153, 102, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jButton4.setText("Thêm");
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 570, 90, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel6.setText("Tìm kiếm");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 230, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTacGiaMouseClicked
        // TODO add your handling code here:
        clickTable();
    }//GEN-LAST:event_tblTacGiaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblTacGia;
    private javax.swing.JTable tblTacGia;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
