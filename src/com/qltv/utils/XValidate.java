/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.utils;

import com.qltv.dao.NhanVienDAO;
import com.qltv.entity.TaiKhoan;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class XValidate {
    
    static NhanVienDAO nvdao = new NhanVienDAO();
    TaiKhoan dstk = new TaiKhoan();
    
    // --- ĐĂNG NHẬP ----
    public static boolean checkNullText(JTextField txt) {
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            MsgBox.alert(txt.getRootPane(), "Không được để trống.");
            return false;
        }
    }

    /*
     * Kiểm tra mật khẩu rỗng
     */
    public static boolean checkNullPass(JTextField txt) {
        if (txt.getText().length() > 0) {
            return true;
        } else {
            MsgBox.alert(txt.getRootPane(), "Không được để trống.");
            return false;
        }
    }
    
    /*
     * Kiểm thử tên Nhân Viên, Người Học
     */
    public static boolean checkName(JTextField txt) {
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,25}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            MsgBox.alert(txt.getRootPane(), "Tên phải là tên có dấu hoặc không và từ 3-25 kí tự.");
            return false;
        }
    }
    /*
    * Kiểm thử số điện thoại
    */
    public static boolean checkSDT(JTextField txt) {
        String id = txt.getText();
        String rgx = "(086|096|097|098|032|033|034|035|036|037|038|039|089|090|093|070|079|077|078|076|088|091|094|083|084|085|081|082|092|056|058|099|059)[0-9]{7}";
        if (id.matches(rgx)) {
            return true;
        } else {
            MsgBox.alert(txt.getRootPane(), "SDT phải gồm 10 số\nĐúng đầu số của các nhà mạng");
            return false;
        }
    }
    public static boolean isNumber(JTextField str){
        try {
            double number = Double.parseDouble(str.getText());
                return true;
                
        } catch (Exception e) {
            return false;
        }
      
    }
    
    
}
    

