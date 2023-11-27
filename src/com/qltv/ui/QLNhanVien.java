/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.qltv.ui;

import com.qltv.dao.NhanVienDAO;
import com.qltv.dao.TaiKhoanDAO;
import com.qltv.entity.NhanVien;
import com.qltv.entity.TaiKhoan;
import com.qltv.utils.Auth;
import com.qltv.utils.MsgBox;
import com.qltv.utils.XDate;
import com.qltv.utils.XValidate;
import java.awt.AlphaComposite;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QLNhanVien extends javax.swing.JPanel {

    NhanVienDAO nvdao = new NhanVienDAO();
    TaiKhoanDAO tkdao = new TaiKhoanDAO();
    /**
     * Creates new form QLNhanVien
     */
    public QLNhanVien() {
        initComponents();
        fillTableNV();
        fillTableTK();
        lblTen.setText("Tên đăng nhập: " + Auth.user.getUser());
        lblChucVu.setText("Chức vụ: " + String.valueOf(Auth.user.isQuyen() ? "Quản lý" : "Nhân viên"));
    }

    // -----------------------------NHÂN VIÊN---------------------------------- //
    
    // Đổ dữ liệu lên bảng
    public void fillTableNV(){
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try{
            List<NhanVien> list = nvdao.selectAll();
            for (NhanVien dg : list) {
                Object[] row = {
                    dg.getMa(),
                    dg.getTen(),
                    dg.getNam(),
                    dg.isGiotinh() ? "Nam" : "Nữ",
                    dg.getDiachi(),
                    dg.getSdt()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu sách!");
            e.printStackTrace();
        }
        
    }
    
    // click để lấy dữ liệu từ bảng lên form
    private void clickTableNV() {
        int i = tblNhanVien.getSelectedRow();
        if (i > -1) {
            try {
                txtHoTen.setText(tblNhanVien.getValueAt(i, 1) + "");
                txtNamSinh.setText(tblNhanVien.getValueAt(i, 2) + "");
                if (String.valueOf(tblNhanVien.getValueAt(i, 3)) == "Nam") {
                    rdoNam.setSelected(true);
                } else if (String.valueOf(tblNhanVien.getValueAt(i, 3)).equals("Nữ")) {
                    rdoNu.setSelected(true);
                }
                txtDiaChi.setText(tblNhanVien.getValueAt(i, 4) + "");
                txtSDT.setText(tblNhanVien.getValueAt(i, 5) + "");
                txtMaTK.setText(tblNhanVien.getValueAt(i, 0) + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn vào bảng");
        }
        
    }
    
    // Thêm
    private void insertNV(){
        NhanVien ls = this.getFormNV();
        nvdao.insert(ls);
        this.fillTableNV();
        this.clearFormNV();
        MsgBox.alert(this, "Thêm nhân viên thành công");
    }
    
    // Cập nhật loại sách
    private void updateNV(){
        NhanVien ls = this.getFormNV1();
        nvdao.update(ls);
        this.fillTableNV();
        this.clearFormNV();
        MsgBox.alert(this, "Cập nhật nhân viên thành công");
    }
    
    // Xóa
    private void deleteNV() {
        int c = tblNhanVien.getSelectedRow();
        int id = (int) tblNhanVien.getValueAt(c, 0);
        nvdao.delete(id);
        this.fillTableNV();
        this.clearFormNV();
        MsgBox.alert(this, "Xóa thành công");
    }
    
    // Làm mới
        public void clearFormNV(){
            txtHoTen.setText("");
            txtNamSinh.setText("");
            txtDiaChi.setText("");
            txtSDT.setText("");
            buttonGroup2.clearSelection();
    }
    
    // Lấy dữ liệu cho nút thêm
    private NhanVien getFormNV(){
        NhanVien nv = new NhanVien();
        nv.setTen(txtHoTen.getText());
        nv.setNam(txtNamSinh.getText());
        nv.setGiotinh(rdoNam.isSelected());
        nv.setDiachi(txtDiaChi.getText());
        nv.setSdt(txtSDT.getText());
        return nv;
    }
    
    // Lấy dữ liệu cho nút cập nhật
    private NhanVien getFormNV1(){
        NhanVien nv = new NhanVien();
        nv.setMa( Integer.parseInt(tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0) + ""));
        nv.setTen(txtHoTen.getText());
        nv.setNam(txtNamSinh.getText());
        nv.setGiotinh(rdoNam.isSelected());
        nv.setDiachi(txtDiaChi.getText());
        nv.setSdt(txtSDT.getText());
        return nv;
    }
    
    // ------------------------------ TÀI KHOẢN ------------------------------- //
    
    // Đổ dữ liệu lên bảng
    public void fillTableTK() {
        DefaultTableModel modelSP = (DefaultTableModel) tblTaiKhoan.getModel();
        modelSP.setRowCount(0);
        tabs.setSelectedIndex(1);
        List<TaiKhoan> listSP = new ArrayList<>();
        listSP = tkdao.SelectAll();
        try {
            for (TaiKhoan tv : listSP) {
                Object[] rows = new Object[]{
                    tv.getMatk(),
                    tv.getUser(),
                    tv.getPass(),
                    tv.isQuyen() ? "Quản lý" : "Nhân viên",
                    tv.getManv()
                };
                modelSP.addRow(rows);
            }
            
        } catch (Exception e) {
        }

    }
    
    // Lây dữ liệu từ bảng lên form
    private void clickTableTK() {
        int i = tblTaiKhoan.getSelectedRow();
        if (i > -1) {
            try {
                txtMaTK.setText(tblTaiKhoan.getValueAt(i, 0) + "");
                txtUser.setText(tblTaiKhoan.getValueAt(i, 1) + "");
                txtMatKhau.setText(tblTaiKhoan.getValueAt(i, 2) + "");
                if (String.valueOf(tblTaiKhoan.getValueAt(i, 3)) == "Quản lý") {
                    rdoQuanLy.setSelected(true);
                } else if (String.valueOf(tblTaiKhoan.getValueAt(i, 3)).equals("Nhân viên")) {
                    rdoNhanVien.setSelected(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn vào bảng");
        }

    }
    
    // Thêm
    private void insertTK(){
        TaiKhoan tk = this.getFormTK();
        tkdao.insert(tk);
        this.fillTableTK();
        this.clearFormTK();
        MsgBox.alert(this, "Thêm tài khoản thành công");
    }
    
    // Cập nhật loại sách
    private void updateTK(){
        TaiKhoan tk = this.getFormTK1();
        tkdao.update(tk);
        this.fillTableTK();
        this.clearFormTK();
        MsgBox.alert(this, "Cập nhật tài khoản thành công");
    }
    
    // Xóa
    private void deleteTK() {
        int c = tblTaiKhoan.getSelectedRow();
        int id = (int) tblTaiKhoan.getValueAt(c, 0);
        tkdao.delete(id);
        this.fillTableTK();
        this.clearFormTK();
        MsgBox.alert(this, "Xóa thành công");
    }
    
    // Làm mới
        public void clearFormTK(){
            txtMaTK.setText("");
            txtUser.setText("");
            txtMatKhau.setText("");
            buttonGroup1.clearSelection();
    }
    
    // Lấy dữ liệu cho nút thêm
    private TaiKhoan getFormTK(){
        TaiKhoan tk = new TaiKhoan();
       tk.setUser(txtUser.getText());
        tk.setPass(txtMatKhau.getText());
        tk.setQuyen(rdoQuanLy.isSelected());
        tk.setManv(Integer.parseInt(txtMaTK.getText()));
        return tk;
    }
    
    // Lấy dữ liệu cho nút cập nhật
    private TaiKhoan getFormTK1(){
        TaiKhoan tk = new TaiKhoan();
        tk.setMatk(Integer.parseInt(txtMaTK.getText()));
        tk.setUser(txtUser.getText());
        tk.setPass(txtMatKhau.getText());
        tk.setQuyen(rdoQuanLy.isSelected());
        tk.setManv(Integer.parseInt(txtMaTK.getText()));
        return tk;
    }
    
    private boolean kiemTraTrungMa(int ma) {
       List<TaiKhoan> dstk = tkdao.SelectAll();
        for (TaiKhoan item : dstk) {
            if (item.getMatk() == ma) {
                MsgBox.alert(this, "Mã đã tồn tại!");
                return false; // Mã đã tồn tại
            }
        }
        return true; // Mã chưa tồn tại
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnlQLNhanVien = new com.qltv.swing.PanelBorder();
        tabs = new javax.swing.JTabbedPane();
        pnlNhanVien = new javax.swing.JPanel();
        txtHoTen = new com.qltv.swing.TextField();
        txtNamSinh = new com.qltv.swing.TextField();
        txtDiaChi = new com.qltv.swing.TextField();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNu = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtTimKiemNV = new com.qltv.swing.TextField();
        btnThemNV = new com.qltv.swing.Button();
        btnSuaNV = new com.qltv.swing.Button();
        btnXoaNV = new com.qltv.swing.Button();
        btnMoiNV = new com.qltv.swing.Button();
        jLabel2 = new javax.swing.JLabel();
        txtSDT = new com.qltv.swing.TextField();
        pnlTaiKhoan = new javax.swing.JPanel();
        lblTen = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        txtMaTK = new com.qltv.swing.TextField();
        txtUser = new com.qltv.swing.TextField();
        txtMatKhau = new com.qltv.swing.TextField();
        jLabel5 = new javax.swing.JLabel();
        rdoQuanLy = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        btnMoiTK = new com.qltv.swing.Button();
        btnSuaTK = new com.qltv.swing.Button();
        btnThemTK = new com.qltv.swing.Button();
        btnXoaTK = new com.qltv.swing.Button();
        lblTaiKhoan = new javax.swing.JLabel();
        txtTimKiem = new com.qltv.swing.TextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();

        pnlQLNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        pnlQLNhanVien.setPreferredSize(new java.awt.Dimension(1100, 630));

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setForeground(new java.awt.Color(153, 102, 0));
        tabs.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tabs.setPreferredSize(new java.awt.Dimension(300, 80));

        pnlNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        pnlNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pnlNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtHoTen.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtHoTen.setLabelText("Họ & tên");
        pnlNhanVien.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 273, -1));

        txtNamSinh.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNamSinh.setLabelText("Năm sinh");
        pnlNhanVien.add(txtNamSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 273, -1));

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDiaChi.setLabelText("Địa chỉ");
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        pnlNhanVien.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 273, -1));

        lblGioiTinh.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(153, 153, 153));
        lblGioiTinh.setText("Giới tính");
        pnlNhanVien.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, -1, -1));

        buttonGroup2.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(153, 153, 153));
        rdoNu.setText("Nữ");
        pnlNhanVien.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, -1, -1));

        buttonGroup2.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(153, 153, 153));
        rdoNam.setText("Nam");
        pnlNhanVien.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, -1, -1));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Năm sinh", "Giới tính", "Địa chỉ", "Số điện thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        pnlNhanVien.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 570, 420));

        txtTimKiemNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTimKiemNV.setLabelText("Tìm kiếm");
        pnlNhanVien.add(txtTimKiemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 290, -1));

        btnThemNV.setText("Thêm");
        btnThemNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThemNV.setPreferredSize(new java.awt.Dimension(90, 50));
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });
        pnlNhanVien.add(btnThemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        btnSuaNV.setText("Cập nhật");
        btnSuaNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSuaNV.setPreferredSize(new java.awt.Dimension(90, 50));
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });
        pnlNhanVien.add(btnSuaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, -1, -1));

        btnXoaNV.setText("Xóa");
        btnXoaNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoaNV.setPreferredSize(new java.awt.Dimension(90, 50));
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });
        pnlNhanVien.add(btnXoaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 470, -1, -1));

        btnMoiNV.setText("Mới");
        btnMoiNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnMoiNV.setPreferredSize(new java.awt.Dimension(90, 50));
        btnMoiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiNVActionPerformed(evt);
            }
        });
        pnlNhanVien.add(btnMoiNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 102, 0));
        jLabel2.setText("NHÂN VIÊN");
        pnlNhanVien.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSDT.setLabelText("Số điện thoại");
        pnlNhanVien.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 270, -1));

        tabs.addTab("NHÂN VIÊN", pnlNhanVien);

        pnlTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        pnlTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pnlTaiKhoan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTen.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblTen.setForeground(new java.awt.Color(102, 51, 0));
        lblTen.setText("Tên nhân viên: ");
        pnlTaiKhoan.add(lblTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        lblChucVu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblChucVu.setForeground(new java.awt.Color(102, 51, 0));
        lblChucVu.setText("Chức vụ:  Quản lý");
        pnlTaiKhoan.add(lblChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        txtMaTK.setEditable(false);
        txtMaTK.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaTK.setLabelText("Mã tài khoản");
        pnlTaiKhoan.add(txtMaTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 193, 268, -1));

        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtUser.setLabelText("Tài khoản");
        pnlTaiKhoan.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 257, 268, -1));

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMatKhau.setLabelText("Mật khẩu");
        pnlTaiKhoan.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 321, 268, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Chức vụ");
        pnlTaiKhoan.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, -1, -1));

        buttonGroup1.add(rdoQuanLy);
        rdoQuanLy.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        rdoQuanLy.setForeground(new java.awt.Color(153, 153, 153));
        rdoQuanLy.setText("Quản lý");
        pnlTaiKhoan.add(rdoQuanLy, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, -1, -1));

        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        rdoNhanVien.setForeground(new java.awt.Color(153, 153, 153));
        rdoNhanVien.setText("Nhân viên");
        pnlTaiKhoan.add(rdoNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, -1, -1));

        btnMoiTK.setText("Mới");
        btnMoiTK.setPreferredSize(new java.awt.Dimension(75, 45));
        btnMoiTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiTKActionPerformed(evt);
            }
        });
        pnlTaiKhoan.add(btnMoiTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 510, -1, -1));

        btnSuaTK.setText("Cập nhật");
        btnSuaTK.setPreferredSize(new java.awt.Dimension(75, 45));
        btnSuaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTKActionPerformed(evt);
            }
        });
        pnlTaiKhoan.add(btnSuaTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 510, -1, -1));

        btnThemTK.setText("Thêm");
        btnThemTK.setPreferredSize(new java.awt.Dimension(75, 45));
        btnThemTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTKActionPerformed(evt);
            }
        });
        pnlTaiKhoan.add(btnThemTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, -1, -1));

        btnXoaTK.setText("Xóa");
        btnXoaTK.setPreferredSize(new java.awt.Dimension(75, 45));
        btnXoaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTKActionPerformed(evt);
            }
        });
        pnlTaiKhoan.add(btnXoaTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, -1, -1));

        lblTaiKhoan.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(153, 102, 0));
        lblTaiKhoan.setText("TÀI KHOẢN");
        pnlTaiKhoan.add(lblTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 200, -1));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTimKiem.setLabelText("Tìm kiếm");
        pnlTaiKhoan.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 290, -1));

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã tài khoản", "Username", "Password", "Quyền", "Mã nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTaiKhoan);

        pnlTaiKhoan.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 580, 370));

        tabs.addTab("TÀI KHOẢN", pnlTaiKhoan);

        javax.swing.GroupLayout pnlQLNhanVienLayout = new javax.swing.GroupLayout(pnlQLNhanVien);
        pnlQLNhanVien.setLayout(pnlQLNhanVienLayout);
        pnlQLNhanVienLayout.setHorizontalGroup(
            pnlQLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        pnlQLNhanVienLayout.setVerticalGroup(
            pnlQLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1){
            clickTableNV();
        }
        if(evt.getClickCount() == 2){
            clickTableNV();
            tabs.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1){
            clickTableTK();
        }
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtHoTen)
                && XValidate.checkNullText(txtNamSinh)
                && XValidate.checkNullText(txtSDT)
                && XValidate.checkNullText(txtDiaChi)){
            if(XValidate.checkName(txtHoTen)){
                if(XValidate.checkSDT(txtSDT)){
                    if(kiemTraTrungMa(Integer.parseInt(tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0)+""))){
                    insertNV();
                }
                }
            }
        }
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtHoTen)
                && XValidate.checkNullText(txtNamSinh)
                && XValidate.checkNullText(txtSDT)
                && XValidate.checkNullText(txtDiaChi)){
            if(XValidate.checkName(txtHoTen)){
                if(XValidate.checkSDT(txtSDT)){
                    
                    updateNV();
                    
                }
            }
        }
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        // TODO add your handling code here:
        deleteNV();
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnMoiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiNVActionPerformed
        // TODO add your handling code here:
        clearFormNV();
    }//GEN-LAST:event_btnMoiNVActionPerformed

    private void btnThemTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTKActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtMaTK)
                && XValidate.checkNullText(txtUser)
                && XValidate.checkNullText(txtMatKhau)){
            if(kiemTraTrungMa(Integer.parseInt(txtMaTK.getText()))){
                    insertTK();
            }
        }
    }//GEN-LAST:event_btnThemTKActionPerformed

    private void btnSuaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTKActionPerformed
        // TODO add your handling code here:
        if(XValidate.checkNullText(txtMaTK)
                && XValidate.checkNullText(txtUser)
                && XValidate.checkNullText(txtMatKhau)){
                    updateTK();
        }
    }//GEN-LAST:event_btnSuaTKActionPerformed

    private void btnXoaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTKActionPerformed
        // TODO add your handling code here:
        deleteTK();
    }//GEN-LAST:event_btnXoaTKActionPerformed

    private void btnMoiTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiTKActionPerformed
        // TODO add your handling code here:
        clearFormTK();
    }//GEN-LAST:event_btnMoiTKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.qltv.swing.Button btnMoiNV;
    private com.qltv.swing.Button btnMoiTK;
    private com.qltv.swing.Button btnSuaNV;
    private com.qltv.swing.Button btnSuaTK;
    private com.qltv.swing.Button btnThemNV;
    private com.qltv.swing.Button btnThemTK;
    private com.qltv.swing.Button btnXoaNV;
    private com.qltv.swing.Button btnXoaTK;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTen;
    private javax.swing.JPanel pnlNhanVien;
    private com.qltv.swing.PanelBorder pnlQLNhanVien;
    private javax.swing.JPanel pnlTaiKhoan;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblTaiKhoan;
    private com.qltv.swing.TextField txtDiaChi;
    private com.qltv.swing.TextField txtHoTen;
    private com.qltv.swing.TextField txtMaTK;
    private com.qltv.swing.TextField txtMatKhau;
    private com.qltv.swing.TextField txtNamSinh;
    private com.qltv.swing.TextField txtSDT;
    private com.qltv.swing.TextField txtTimKiem;
    private com.qltv.swing.TextField txtTimKiemNV;
    private com.qltv.swing.TextField txtUser;
    // End of variables declaration//GEN-END:variables
}
