/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.qltv.ui;

import com.qltv.dao.ChiTietPhieuNhapDAO;
import com.qltv.dao.NhaCungCapDAO;
import com.qltv.dao.NhanVienDAO;
import com.qltv.dao.PhieuNhapDAO;
import com.qltv.entity.ChiTietPhieuNhap;
import com.qltv.entity.NhaCungCap;
import com.qltv.entity.NhanVien;
import com.qltv.entity.PhieuNhap;
import com.qltv.utils.Auth;
import com.qltv.utils.MsgBox;
import com.qltv.utils.XDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class QLPhieuNhap extends javax.swing.JPanel {

    PhieuNhapDAO pndao = new PhieuNhapDAO();
    NhaCungCapDAO nccdao = new NhaCungCapDAO();
    NhanVienDAO nvdao = new NhanVienDAO();
    ChiTietPhieuNhapDAO ctdao = new ChiTietPhieuNhapDAO();

    /**
     * Creates new form QLPhieuNhap
     */
    public QLPhieuNhap() {
        initComponents();
        init();
        this.fillTablePN();
        this.fillComboBoxNCC();
        this.fillComboBoxNV();
        this.fillComboBoxLoc();
        

    }

    private void fillTablePN() {

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        try {
            List<PhieuNhap> list = pndao.selectAll();
            for (PhieuNhap dg : list) {
                Object[] row = {
                    dg.getMa(),
                    nccdao.convertToTenNCC(dg.getMancc()),
                    nvdao.convertToTenNV(dg.getManv()),
                    //                    dg.getMancc(),
                    //                    dg.getManv(),
                    dg.getNgaynhap(),
                    dg.getTong()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu phiếu nhập!");
            e.printStackTrace();
        }

    }

    private void fillTableCT() {

        DefaultTableModel model = (DefaultTableModel) tblCTPN.getModel();
        model.setRowCount(0);
        try {
            List<ChiTietPhieuNhap> list = ctdao.selectAll();
            for (ChiTietPhieuNhap tv : list) {
                Object[] row = {
                    tv.getMa(),
                    tv.getMapn(),
                    ctdao.convertToTenSach(tv.getMasach()),
                    tv.getGia(),
                    tv.getSoluong(),
                    tv.getThanhtien()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu phiếu nhập!");
            e.printStackTrace();
        }

    }

    private void fillComboBoxNCC() {
        cboNCC.removeAllItems();
        List<String> data = nccdao.selectNXB();
        for (String item : data) {
            cboNCC.addItem(item);
        }
    }

    private void fillComboBoxLoc() {
        combobox3.removeAllItems();
        List<String> data = nccdao.selectNXB();
        for (String item : data) {
            combobox3.addItem(item);
        }
    }

    private void fillComboBoxNV() {
        cboNV.removeAllItems();
        List<String> data = pndao.selectNXB();
        for (String item : data) {
            cboNV.addItem(item);
        }
    }

    private void insert() {
        PhieuNhap model = getForm();
        try {
            pndao.insert(model);
            this.fillTablePN();
//            this.clearForm();
            MsgBox.alert(this, "Thêm phiếu nhập mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm phiếu nhập mới thất bại!");
            e.printStackTrace();
        }

    }

    private void update() {
        PhieuNhap model = getFormPN1();
        try {
            pndao.update(model);
            this.fillTablePN();
//            this.clearForm();
            MsgBox.alert(this, "Cập nhật phiếu nhập mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật phiếu nhập mới thất bại!");
            e.printStackTrace();
        }

    }

    private void delete() {
        int c = jTable2.getSelectedRow();
        int id = (int) jTable2.getValueAt(c, 0);
        pndao.delete(id);
        this.fillTablePN();
        this.clearForm();
        MsgBox.alert(this, "Xóa thành công");
    }

    private void clearForm() {
        cboNCC.setSelectedIndex(0);
        cboNV.setSelectedIndex(0);
        txtTong.setText("");
        dateNgayNhap.setDate(XDate.now());
    }

    private PhieuNhap getForm() {
        PhieuNhap s = new PhieuNhap();
        try {
            Object selectedNV = cboNV.getSelectedItem();
            Object selectedNCC = cboNCC.getSelectedItem();

            if (selectedNV != null) {
                s.setManv(nvdao.convertToMaNV(selectedNV.toString()));
            }

            if (selectedNCC != null) {
                s.setMancc(nccdao.convertToMaNV(selectedNCC.toString()));
            }

            s.setNgaynhap(dateNgayNhap.getDate());
            s.setTong(Integer.parseInt(txtTong.getText()));
        } catch (NumberFormatException ex) {
            // Xử lý ngoại lệ khi parse không thành công
            ex.printStackTrace();
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác mà bạn quan tâm
            e.printStackTrace();
        }
        return s;
    }

    private PhieuNhap getFormPN1() {
        PhieuNhap s = new PhieuNhap();
        try {
            Object selectedNV = cboNV.getSelectedItem();
            Object selectedNCC = cboNCC.getSelectedItem();

            if (selectedNV != null) {
                s.setManv(nvdao.convertToMaNV(selectedNV.toString()));
            }

            if (selectedNCC != null) {
                s.setMancc(nccdao.convertToMaNV(selectedNCC.toString()));
            }
            s.setMa((int) jTable2.getValueAt(jTable2.getSelectedRow(), 0));
            s.setNgaynhap(dateNgayNhap.getDate());
            s.setTong(Integer.parseInt(txtTong.getText()));
        } catch (NumberFormatException ex) {
            // Xử lý ngoại lệ khi parse không thành công
            ex.printStackTrace();
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác mà bạn quan tâm
            e.printStackTrace();
        }
        return s;
    }

    private void clickTablePN() {
        int i = jTable2.getSelectedRow();
        if (i > -1) {
            try {
                cboNCC.setSelectedItem(jTable2.getValueAt(i, 1));
                cboNV.setSelectedItem(jTable2.getValueAt(i, 2));
                dateNgayNhap.setDate((java.util.Date) jTable2.getValueAt(i, 3));
                txtTong.setText(jTable2.getValueAt(i, 4).toString());
                txtMa.setText(jTable2.getValueAt(i, 0).toString());
                txtMa.setText(tblCTPN.getValueAt(0, 1).toString());
                txtSach.setText(tblCTPN.getValueAt(0, 2).toString());
                txtGia.setText(tblCTPN.getValueAt(0, 3).toString());
                txtSL.setText(tblCTPN.getValueAt(0, 4).toString());
                txtThanhTien.setText(tblCTPN.getValueAt(0, 5).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            jTabbedPane1.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn vào bảng");
        }

    }

    private void clickTableCTPN() {
        PhieuNhap pn = new PhieuNhap();
        int i = tblCTPN.getSelectedRow();
        if (i > -1) {
            try {
                
                txtMa.setText(tblCTPN.getValueAt(i, 1).toString());
                txtSach.setText(tblCTPN.getValueAt(i, 2).toString());
                txtGia.setText(tblCTPN.getValueAt(i, 3).toString());
                txtSL.setText(tblCTPN.getValueAt(i, 4).toString());
                txtThanhTien.setText(tblCTPN.getValueAt(i, 5).toString());
                System.out.println(jTable2.getValueAt(i, 0).toString()+"");
                    System.out.println(String.valueOf(nccdao.convertToTenNCC(pn.getMancc())));
                    System.out.println(String.valueOf(nvdao.convertToTenNV(pn.getManv())));
                    System.out.println(pn.getTong());
                    System.out.println(pn.getNgaynhap());
            } catch (Exception e) {
                e.printStackTrace();
            }
            jTabbedPane1.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn vào bảng");
        }

    }
    
//    private void hienThiDuLieuPhieuNhap(Object maPhieuNhap) {
//    // Tìm kiếm và hiển thị dữ liệu của phiếu nhập có mã tương ứng
//    PhieuNhap phieuNhap = timPhieuNhapTheoMa(maPhieuNhap);
//    if (phieuNhap != null) {
//        // Hiển thị dữ liệu lên form
//        hienThiDuLieuPhieuNhapLenForm(phieuNhap);
//    }
//}
//private void hienThiDuLieuPhieuNhapTuChiTietPhieuNhap() {
//    int selectedRowChiTiet = tblCTPN.getSelectedRow();
//    if (selectedRowChiTiet >= 0) {
//        // Lấy mã phiếu nhập từ bảng chi tiết phiếu nhập
//        Object maPhieuNhap = tblCTPN.getValueAt(selectedRowChiTiet, 1);
//
//        // Hiển thị dữ liệu của phiếu nhập có mã tương ứng
//        hienThiDuLieuPhieuNhap(maPhieuNhap);
//    }
//}
//
//    
//    private PhieuNhap timPhieuNhapTheoMa(Object maPhieuNhap) {
//    // Viết logic để tìm kiếm phiếu nhập theo mã từ dữ liệu của bạn
//    // ...
//
//    // Giả sử có một danh sách danh sách phiếu nhập
//    List<PhieuNhap> danhSachPhieuNhap = pndao.selectAll(); // Lấy danh sách từ dữ liệu của bạn
//
//    // Gọi hàm tìm kiếm trong danh sách
//    return timPhieuNhapTrongDanhSach(danhSachPhieuNhap, (int) maPhieuNhap);
//}
//
//    private void hienThiDuLieuPhieuNhapLenForm(PhieuNhap phieuNhap) {
//    // Kiểm tra giá trị trước khi đặt vào ComboBox
//    System.out.println("TenNCC: " + nccdao.convertToTenNCC(phieuNhap.getMancc()));
//    System.out.println("TenNV: " + nvdao.convertToTenNV(phieuNhap.getManv()));
//
//    // Đặt giá trị vào ComboBox
//    cboNCC.setSelectedItem(nccdao.convertToTenNCC(phieuNhap.getMancc()));
//    cboNV.setSelectedItem(nvdao.convertToTenNV(phieuNhap.getManv()));
//    dateNgayNhap.setDate(phieuNhap.getNgaynhap());
//    txtTong.setText(String.valueOf(phieuNhap.getTong()));
//}
//    
//private PhieuNhap timPhieuNhapTrongDanhSach(List<PhieuNhap> danhSachPhieuNhap, int maPhieuNhap) {
//    // Thực hiện tìm kiếm trong danh sách phiếu nhập và trả về đối tượng PhieuNhap
//    for (PhieuNhap phieuNhap : danhSachPhieuNhap) {
//        if (phieuNhap.getMa() == maPhieuNhap) {
//            return phieuNhap;
//        }
//    }
//    return null; // Trả về null nếu không tìm thấy
//}


    public void getTableCT() {
        DefaultTableModel modelSP = (DefaultTableModel) tblCTPN.getModel();
        modelSP.setRowCount(0);
        int rown = jTable2.getSelectedRow();
        int ma = (int) jTable2.getValueAt(rown, 0);
        List<ChiTietPhieuNhap> listSP = new ArrayList<>();
        listSP = ctdao.selectByIds(ma);
        try {
            for (ChiTietPhieuNhap tv : listSP) {
                Object[] rows = new Object[]{
                    tv.getMa(),
                    tv.getMapn(),
                    ctdao.convertToTenSach(tv.getMasach()),
                    tv.getGia(),
                    tv.getSoluong(),
                    tv.getThanhtien()
                };
                modelSP.addRow(rows);
            }
        } catch (Exception e) {
        }

    }

    // Phương thức cập nhật chi tiết phiếu nhập
private void updateCT() {
    ChiTietPhieuNhap model = getFormCT1();
    try {
        ctdao.update(model);
        this.getTableCT();
        
        // Gọi phương thức tính tổng thành tiền và hiển thị kết quả
//        updateTongThanhTien();
        
        MsgBox.alert(this, "Cập nhật chi tiết phiếu nhập mới thành công!");
    } catch (Exception e) {
        MsgBox.alert(this, "Cập nhật chi tiết phiếu nhập mới thất bại!");
        e.printStackTrace();
    }
}

//// Phương thức xóa chi tiết phiếu nhập
//private void deleteCT() {
//    int maCT = Integer.parseInt(txtMaCT.getText());
//    try {
//        ctdao.delete(maCT);
//        this.getTableCT();
//        
//        // Gọi phương thức tính tổng thành tiền và hiển thị kết quả
//        updateTongThanhTien();
//        
//        MsgBox.alert(this, "Xóa chi tiết phiếu nhập thành công!");
//    } catch (Exception e) {
//        MsgBox.alert(this, "Xóa chi tiết phiếu nhập thất bại!");
//        e.printStackTrace();
//    }
//}

// Phương thức tính tổng thành tiền và hiển thị kết quả
//private void updateTongThanhTien() {
//    int tongThanhTien = tinhTongThanhTien(ctdao.selectAll());
//    // Sử dụng giá trị tongThanhTien theo nhu cầu của bạn, có thể hiển thị trên giao diện người dùng
//}

    private ChiTietPhieuNhap getFormCT1() {
    ChiTietPhieuNhap s = new ChiTietPhieuNhap();
    
    // Kiểm tra xem có hàng được chọn hay không
    int selectedRow = tblCTPN.getSelectedRow();
    if (selectedRow >= 0) {
        s.setMa((int) tblCTPN.getValueAt(selectedRow, 0));
        s.setMapn(Integer.parseInt(txtMa.getText()));
        s.setMasach(ctdao.convertToMaSach(txtSach.getText()));
        s.setGia(Integer.valueOf(txtGia.getText()));
        s.setSoluong(Integer.valueOf(txtSL.getText()));
        s.setThanhtien(Integer.valueOf(txtThanhTien.getText()));
    } else {
        // Xử lý trường hợp không có hàng nào được chọn
        MsgBox.alert(this, "Vui lòng chọn một hàng trong bảng chi tiết phiếu nhập!");
    }
    
    return s;
}


    public int thanhTienGia() {
        int gia = Integer.parseInt(txtGia.getText());
        int soluong = Integer.parseInt(txtSL.getText());
        int thanhtien = gia * soluong;
        txtThanhTien.setText("" + thanhtien);
        return thanhtien;
    }

    public int thanhTienSoLuong() {
        int gia = Integer.parseInt(txtGia.getText());
        int soluong = Integer.parseInt(txtSL.getText());
        int thanhtien = gia * soluong;
        txtThanhTien.setText("" + thanhtien);
        return thanhtien;
    }

    public int tinhTongThanhTienTheoPhieuNhap(int maPhieuNhap) {
    int tongThanhTien = 0;
    List<ChiTietPhieuNhap> chiTietList = ctdao.selectByIds(maPhieuNhap);

    for (ChiTietPhieuNhap chiTiet : chiTietList) {
        tongThanhTien += chiTiet.getThanhtien();
    }

    return tongThanhTien;
}

    private void filterByNCC(int selectedNCC) {
        // Gọi phương thức của DAO hoặc thực hiện các truy vấn để lấy dữ liệu đã lọc
        List<PhieuNhap> filteredData = pndao.getPhieuNhapByNCC(selectedNCC);

        // Xóa tất cả dữ liệu hiện tại trong bảng
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        // Thêm dữ liệu đã lọc vào bảng
        for (PhieuNhap phieuNhap : filteredData) {
            model.addRow(new Object[]{
                phieuNhap.getMa(),
                nccdao.convertToTenNCC(phieuNhap.getMancc()),
                nvdao.convertToTenNV(phieuNhap.getManv()),
                phieuNhap.getNgaynhap(),
                phieuNhap.getTong()
            });
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

        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblTen1 = new javax.swing.JLabel();
        lblChucVu1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        dateNgayNhap = new com.toedter.calendar.JDateChooser();
        button1 = new com.qltv.swing.Button();
        button2 = new com.qltv.swing.Button();
        button3 = new com.qltv.swing.Button();
        button4 = new com.qltv.swing.Button();
        cboNCC = new com.qltv.swing.Combobox();
        txtTong = new com.qltv.swing.TextField();
        button10 = new com.qltv.swing.Button();
        button7 = new com.qltv.swing.Button();
        cboNV = new com.qltv.swing.Combobox();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtSach = new com.qltv.swing.TextField();
        txtSL = new com.qltv.swing.TextField();
        txtMa = new com.qltv.swing.TextField();
        button6 = new com.qltv.swing.Button();
        button8 = new com.qltv.swing.Button();
        button5 = new com.qltv.swing.Button();
        txtGia = new com.qltv.swing.TextField();
        txtThanhTien = new com.qltv.swing.TextField();
        button9 = new com.qltv.swing.Button();
        button11 = new com.qltv.swing.Button();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTPN = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtTimKiem = new com.qltv.swing.TextField();
        combobox3 = new com.qltv.swing.Combobox();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(153, 102, 0));

        jPanel8.setBackground(new java.awt.Color(153, 102, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTen1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        lblTen1.setText("Tên đăng nhập:");

        lblChucVu1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        lblChucVu1.setText("Chức vụ: ");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(153, 102, 0));
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1100, 650));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 650));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(153, 102, 0))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        dateNgayNhap.setBackground(new java.awt.Color(255, 255, 255));
        dateNgayNhap.setDateFormatString("yyyy-MM-dd");
        dateNgayNhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        button1.setText("Thêm");
        button1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(90, 50));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setText("Cập nhật");
        button2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button2.setPreferredSize(new java.awt.Dimension(90, 50));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setText("Xóa");
        button3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button3.setPreferredSize(new java.awt.Dimension(90, 50));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setText("Mới");
        button4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button4.setPreferredSize(new java.awt.Dimension(90, 50));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        cboNCC.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cboNCC.setLabeText("Nhà cung cấp");

        txtTong.setText("0");
        txtTong.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTong.setLabelText("Tổng tiền");
        txtTong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTongFocusLost(evt);
            }
        });
        txtTong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTongMouseEntered(evt);
            }
        });
        txtTong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTongKeyTyped(evt);
            }
        });

        button10.setText(" In");
        button10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button10.setPreferredSize(new java.awt.Dimension(90, 50));

        button7.setText("Xuất");
        button7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button7.setPreferredSize(new java.awt.Dimension(90, 50));

        cboNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cboNV.setLabeText("Nhân Viên");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Ngày nhập");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTong, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(102, 51, 0))); // NOI18N

        txtSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSach.setLabelText("Sách");

        txtSL.setText("0");
        txtSL.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSL.setLabelText("Số lượng");
        txtSL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtSLMouseEntered(evt);
            }
        });

        txtMa.setEditable(false);
        txtMa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMa.setLabelText("Mã phiếu nhập");

        button6.setText("Cập nhật");
        button6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button6.setPreferredSize(new java.awt.Dimension(90, 50));
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        button8.setText("Mới");
        button8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button8.setPreferredSize(new java.awt.Dimension(90, 50));

        button5.setText("Thêm");
        button5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button5.setPreferredSize(new java.awt.Dimension(90, 50));

        txtGia.setText("0");
        txtGia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtGia.setLabelText("Giá");
        txtGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtGiaMouseEntered(evt);
            }
        });

        txtThanhTien.setEditable(false);
        txtThanhTien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtThanhTien.setLabelText("Thành tiền");

        button9.setText("Xóa");
        button9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button9.setPreferredSize(new java.awt.Dimension(90, 50));

        button11.setText("Chọn sách");
        button11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        button11.setPreferredSize(new java.awt.Dimension(90, 50));
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSach, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 51, 0));
        jLabel3.setText("PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(458, 458, 458)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PHIẾU MƯỢN", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(102, 51, 0))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã chi tiết", "Mã phiếu nhập", "Sách", "Giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPNMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTPN);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 345));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 510, 410));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(102, 51, 0))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên", "Ngày nhập", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
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
        jScrollPane3.setViewportView(jTable2);

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 345));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 510, 410));

        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTimKiem.setLabelText("Tìm kiếm");
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseEntered(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });
        jPanel4.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 310, -1));

        combobox3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        combobox3.setLabeText("Nhà cung cấp");
        combobox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox3ActionPerformed(evt);
            }
        });
        jPanel4.add(combobox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 300, 40));

        jTabbedPane1.addTab("DANH SÁCH", jPanel4);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(615, 615, 615)
                .addComponent(lblTen1)
                .addGap(127, 127, 127)
                .addComponent(lblChucVu1)
                .addGap(163, 163, 163)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTen1)
                            .addComponent(lblChucVu1))))
                .addContainerGap(611, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_button1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            clickTablePN();
        }
        getTableCT();
    }//GEN-LAST:event_jTable2MouseClicked

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_button4ActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        // TODO add your handling code here:
        new ChonSach().setVisible(true);
    }//GEN-LAST:event_button11ActionPerformed

    private void tblCTPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPNMouseClicked
        // TODO add your handling code here:
        clickTableCTPN();
//        hienThiDuLieuPhieuNhapTuChiTietPhieuNhap();
    }//GEN-LAST:event_tblCTPNMouseClicked

    private void txtSLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSLMouseEntered
        // TODO add your handling code here:
        thanhTienSoLuong();
    }//GEN-LAST:event_txtSLMouseEntered

    private void txtGiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaMouseEntered
        // TODO add your handling code here:
        thanhTienGia();
    }//GEN-LAST:event_txtGiaMouseEntered

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
        updateCT();
    }//GEN-LAST:event_button6ActionPerformed

    private void txtTimKiemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemMouseEntered

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped
        // TODO add your handling code here:
        String searchTerm = txtTimKiem.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable2.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + searchTerm);
        sorter.setRowFilter(rowFilter);
    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void combobox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox3ActionPerformed
        // TODO add your handling code here:
        // Lấy giá trị được chọn trong cboNCC
//        int selectedNCC = (int) nccdao.convertToMaNV((String) cboNCC.getSelectedItem());
//
//        // Thực hiện lọc và cập nhật bảng PhieuNhap
//        filterByNCC(selectedNCC);
//        
//        
    }//GEN-LAST:event_combobox3ActionPerformed

    private void txtTongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTongMouseEntered
        // TODO add your handling code here:
int maPhieuNhap = Integer.parseInt(txtMa.getText()); // Thay bằng mã phiếu nhập thực tế của bạn
int tongThanhTien = tinhTongThanhTienTheoPhieuNhap(maPhieuNhap);
System.out.println("Tổng thành tiền của phiếu nhập " + maPhieuNhap + ": " + tongThanhTien);
    }//GEN-LAST:event_txtTongMouseEntered

    private void txtTongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTongKeyReleased

    private void txtTongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTongMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTongMouseClicked

    private void txtTongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTongKeyTyped

    private void txtTongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTongFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTongFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.qltv.swing.Button button1;
    private com.qltv.swing.Button button10;
    private com.qltv.swing.Button button11;
    private com.qltv.swing.Button button2;
    private com.qltv.swing.Button button3;
    private com.qltv.swing.Button button4;
    private com.qltv.swing.Button button5;
    private com.qltv.swing.Button button6;
    private com.qltv.swing.Button button7;
    private com.qltv.swing.Button button8;
    private com.qltv.swing.Button button9;
    private com.qltv.swing.Combobox cboNCC;
    private com.qltv.swing.Combobox cboNV;
    private com.qltv.swing.Combobox combobox3;
    private com.toedter.calendar.JDateChooser dateNgayNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblChucVu1;
    private javax.swing.JLabel lblTen1;
    private javax.swing.JTable tblCTPN;
    private com.qltv.swing.TextField txtGia;
    private com.qltv.swing.TextField txtMa;
    private com.qltv.swing.TextField txtSL;
    private com.qltv.swing.TextField txtSach;
    private com.qltv.swing.TextField txtThanhTien;
    private com.qltv.swing.TextField txtTimKiem;
    private com.qltv.swing.TextField txtTong;
    // End of variables declaration//GEN-END:variables

    void init() {
        try {
            lblTen1.setText("Tên đăng nhập: " + Auth.user.getUser());
            lblChucVu1.setText("Chức vụ: " + String.valueOf(Auth.user.isQuyen() ? "Nhân viên" : "Quản lý"));
        } catch (Exception e) {
            MsgBox.alert(this, "Bạn phải đăng nhập trước khi sử dụng!");
        }
        dateNgayNhap.setDate(XDate.now());
    }
}
