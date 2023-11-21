/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qltv.utils;

import com.qltv.entity.NhanVien;
import com.qltv.entity.TaiKhoan;


/**
 *
 * @author Admin
 */
public class Auth {
    
    // Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
    public static TaiKhoan user = null;
    public static NhanVien user1 = null;
    //Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
    public static void clear() {
        Auth.user = null;
    }

    //Kiểm tra xem đã đăng nhập hay chưa
    public static boolean isLogin() {
        return Auth.user != null;
    }

    //Kiểm tra xem có phải là trưởng phòng hay không
    public static boolean isManager() {
        return Auth.isLogin() && user.isQuyen();
    }
}
