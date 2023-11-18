package com.qltv.ui;

import com.qltv.utils.MsgBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    /**
     * Creates new form Main
     */
    public Login() {
        initComponents();
    }
    int cong = 0;
    Timer tg;

//    public void chay() {
//
//        tg = new Timer(3500, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cong++;
//                if (cong == 1) {
//                    lblDoiMatKhau.setVisible(true);
//                }
//
//                cong = 0;
//                tg.stop();
//                ;
//            }
//
//        });
//        tg.start();
//    }

//    public void chay2() {
//        tg = new Timer(3300, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cong++;
//                if (cong == 1) {
//                    lblDangNhap.setVisible(true);
//                    cong = 0;
//                    tg.stop();
//                }
//
//            }
//
//        });
//        tg.start();
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSlide1 = new com.qltv.swing.PanelSlide();
        jLabel1 = new javax.swing.JLabel();
        viewPass = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        button2 = new com.qltv.swing.Button();
        jLabel3 = new javax.swing.JLabel();
        textField4 = new com.qltv.swing.TextField();
        textField1 = new com.qltv.swing.TextField();
        jLabel5 = new javax.swing.JLabel();

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
        viewPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qltv/icon/wiew.png"))); // NOI18N
        viewPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewPassMouseClicked(evt);
            }
        });
        panelSlide1.add(viewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 250, 30, 20));

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
        panelSlide1.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 150, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(215, 163, 90));
        jLabel3.setText("Quên mật khẩu ?");
        panelSlide1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 300, -1, -1));

        textField4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        textField4.setLabelText("Mật khẩu");
        textField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField4ActionPerformed(evt);
            }
        });
        panelSlide1.add(textField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 306, -1));

        textField1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        textField1.setLabelText("Tên đăng nhập");
        panelSlide1.add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, 310, -1));

        jLabel5.setForeground(new java.awt.Color(215, 163, 90));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qltv/icon/close_20px.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        panelSlide1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, -1));

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

        //        if (temp % 2 == 0) {
            //            String view = String.valueOf(txt_pass.getPassword()).trim();
            //            if (view.isEmpty()) {
                //                return;
                //            }
            //            txt_pass.setEchoChar((char) 0);
            ////            txt_pass.setFont(new Font("Arial", Font.PLAIN, 12));
            //            viewPass.setIcon(new ImageIcon("src//G7/fpoly/icon/dontshow.png"));
            //            temp++;
            //        } else {
            //            String view = String.valueOf(txt_pass.getPassword()).trim();
            //            if (view.isEmpty()) {
                //                return;
                //            }
            //            txt_pass.setEchoChar('\uf06c');
            ////            txt_pass.setFont(new Font("Caribi", Font.PLAIN, 12));
            //            viewPass.setIcon(new ImageIcon("src//G7/fpoly/icon/show.png"));
            //            temp++;
            //        }
    }//GEN-LAST:event_viewPassMouseClicked

    private void textField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField4ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
                boolean choose = MsgBox.confirm(this, "Bạn có muốn đóng không?");
        if(choose == true){
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        new Main().setVisible(true);
    }//GEN-LAST:event_button2ActionPerformed

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
    private com.qltv.swing.TextField textField1;
    private com.qltv.swing.TextField textField4;
    private javax.swing.JLabel viewPass;
    // End of variables declaration//GEN-END:variables
}
