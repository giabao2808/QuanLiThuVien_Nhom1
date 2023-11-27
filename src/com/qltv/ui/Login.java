package com.qltv.ui;

import com.qltv.dao.NhanVienDAO;
import com.qltv.dao.TaiKhoanDAO;
import com.qltv.entity.NhanVien;
import com.qltv.entity.TaiKhoan;
import com.qltv.utils.Auth;
import com.qltv.utils.MsgBox;
import com.qltv.utils.XValidate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RAVEN
 */
public class Login extends javax.swing.JFrame {

    int temp;
    TaiKhoanDAO nvdao = new TaiKhoanDAO();

    /**
     * Creates new form Main
     */
    public Login() {
        initComponents();
    }

    private void login() {
        String manv = txtMaNV.getText();
        String mk = String.valueOf(txtMatKhau.getPassword());
        System.out.println("User = " + manv);
        System.out.println("Pass = " + mk);
        TaiKhoan nv = nvdao.selectByIds(manv);
        if (nv == null) {
            MsgBox.alert(this, "Sai tên đăng nhập!");
        } else if (!mk.equals(nv.getPass())) {
            MsgBox.alert(this, "Sai mật khẩu!");
        } else {
            System.out.println("Logged in successfully...");
            MsgBox.alert(this, "Đăng nhập thành công!");
            Auth.user = nv;
            this.dispose();
        }

    }

    private void exit() {
        if (MsgBox.confirm(this, "Bạn muốn kết thúc ứng dụng?")) {
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSlide1 = new com.qltv.swing.PanelSlide();
        jLabel1 = new javax.swing.JLabel();
        viewPass = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        button2 = new com.qltv.swing.Button();
        jLabel3 = new javax.swing.JLabel();
        txtMaNV = new com.qltv.swing.TextField();
        jLabel5 = new javax.swing.JLabel();
        txtMatKhau = new com.qltv.swing.PasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        panelSlide1.setBackground(new java.awt.Color(255, 255, 255));
        panelSlide1.setMinimumSize(new java.awt.Dimension(1000, 500));
        panelSlide1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(215, 163, 90));
        jLabel1.setText("Đăng nhập");
        panelSlide1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, -1, -1));

        viewPass.setBackground(new java.awt.Color(255, 255, 102));
        viewPass.setForeground(new java.awt.Color(204, 204, 204));
        viewPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qltv/icon/show.png"))); // NOI18N
        viewPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewPassMouseClicked(evt);
            }
        });
        panelSlide1.add(viewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 260, 30, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qltv/icon/hinhnenlogin.jpg"))); // NOI18N
        panelSlide1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 500));

        button2.setBackground(new java.awt.Color(215, 163, 90));
        button2.setText("Đăng nhập");
        button2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        panelSlide1.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, 150, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(215, 163, 90));
        jLabel3.setText("Quên mật khẩu ?");
        panelSlide1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 310, -1, -1));

        txtMaNV.setText("baohg");
        txtMaNV.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaNV.setLabelText("Tên đăng nhập");
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        panelSlide1.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, 310, -1));

        jLabel5.setForeground(new java.awt.Color(215, 163, 90));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qltv/icon/close_20px.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        panelSlide1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, -1));

        txtMatKhau.setText("1234");
        txtMatKhau.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMatKhau.setLabelText("Mật khẩu");
        panelSlide1.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 310, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSlide1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSlide1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void viewPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewPassMouseClicked
        if (temp % 2 == 0) {
            String view = String.valueOf(txtMatKhau.getPassword()).trim();
            if (view.isEmpty()) {
                return;
            }
            txtMatKhau.setEchoChar((char) 0);
            viewPass.setIcon(new ImageIcon("D:\\quanlythuvien\\src\\com\\qltv\\icon\\dontshow.png"));
            temp++;
        } else {
            String view = String.valueOf(txtMatKhau.getPassword()).trim();
            if (view.isEmpty()) {
                return;
            }
            txtMatKhau.setEchoChar('\uf06c');
            viewPass.setIcon(new ImageIcon("D:\\quanlythuvien\\src\\com\\qltv\\icon\\show.png"));
            temp++;
        }
    }//GEN-LAST:event_viewPassMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        if (txtMaNV.getText().trim().length() > 0) {
            if (txtMatKhau.getPassword().length > 0){
                this.login();
                new Main().setVisible(true);
            } else {
                MsgBox.alert(this, "Không được để trống tên mật khẩu.");
            }
        } else {
            MsgBox.alert(this, "Không được để trống tên đăng nhập.");
        }
        
    }//GEN-LAST:event_button2ActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.qltv.swing.Button button2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private com.qltv.swing.PanelSlide panelSlide1;
    private com.qltv.swing.TextField txtMaNV;
    private com.qltv.swing.PasswordField txtMatKhau;
    private javax.swing.JLabel viewPass;
    // End of variables declaration//GEN-END:variables
}
