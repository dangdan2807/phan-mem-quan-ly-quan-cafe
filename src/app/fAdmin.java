package app;

import javax.swing.*;
// import javax.swing.event.*;
import javax.swing.table.*;

import DAO.*;

import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class fAdmin extends JDialog implements ActionListener, MouseListener{
    private JTabbedPane tpTabMain;
    private JPanel pnMainDoanhThu, pnMainPhong, pnMainLPhong, pnMainDV, pnMainLDV, pnMainNV, pnMainKH, pnMainTK;
    private JTable tableDT, tableTK;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JComboBox<String> cboLoaiPhong, cboLoaiDV, cboTinhTrang, cboLoaiTK, cboLoaiKH;
    private JButton btnThongKe, btnTimDV, btnTimNV, btnTimTK, btnTimPhong, btnTimKH;
    private JTextField txtDonGiaLP, txtTimDV, txtTimNV, txtTimTK, txtMaDV, txtMaLDV, txtMaNV, txtUserName, txtMaLP,
            txtTenDV, txtTenLDV, txtTenNV, txtTenLP, txtDonGiaDV, txtTimPhong, txtMaPhong, txtTenPhong, txtMaNVTK,
            txtEmail, txtSdt, txtLuong, txtTenNVTK, txtCccd, txtTimKH, txtMaKH, txtTenKH;
    private SpinnerNumberModel modelSpinNumber;
    private JSpinner spinSucChua, spinSoGiuong;
    int widthPn = 770, heightPn = 500;
    private kPnDataListView pnPhong, pnLPhong, pnDichVu, pnLDichVu, pnNhanVien, pnKhachHang, pnTaiKhoan;
    private JPasswordField txtPassWord;

    TaiKhoanDAO tkDao = new TaiKhoanDAO();

    public fAdmin() {
        setTitle("Quản lý");
        setSize(widthPn, heightPn);
        setResizable(false);
        setLocationRelativeTo(null);

        createTabDoanhThu();
        createTabPhong();
        createTabLPhong();
        createTabDichVu();
        createTabLDichVu();
        createTabNhanVien();
        createTabKH();
        createTabTK();
        createTabControl();
    }

    public void createTabControl() {
        tpTabMain = new JTabbedPane();
        tpTabMain.addTab("Doanh thu", null, pnMainDoanhThu, "Quản lý doanh thu");
        tpTabMain.addTab("Phòng", null, pnMainPhong, "Quản lý phòng");
        tpTabMain.addTab("Loại Phòng", null, pnMainLPhong, "Quản lý loại phòng");
        tpTabMain.addTab("Dịch Vụ", null, pnMainDV, "Quản lý dịch vụ");
        tpTabMain.addTab("Loại Dịch Vụ", null, pnMainLDV, "Quản lý loại dịch vụ");
        tpTabMain.addTab("Nhân viên", null, pnMainNV, "Quản lý nhân viên");
        tpTabMain.addTab("Khách hàng", null, pnMainKH, "Quản lý Khách hàng");
        tpTabMain.addTab("Tài Khoản", null, pnMainTK, "Quản lý tài khoản");

        getContentPane().add(tpTabMain);
    }

    public void createTabDoanhThu() {
        pnMainDoanhThu = new JPanel();
        pnMainDoanhThu.setBounds(0, 0, widthPn, heightPn);
        pnMainDoanhThu.setLayout(null);

        JLabel lbTuNgay = new JLabel("Từ ngày: ");
        JLabel lbDenNgay = new JLabel("Đến ngày: ");

        dpTuNgay = new kDatePicker();
        dpDenNgay = new kDatePicker();
        btnThongKe = new JButton("Thống kê");

        String[] cols = { "" };
        DefaultTableModel modelTableDT = new DefaultTableModel(cols, 0);
        tableDT = new JTable(modelTableDT);

        JScrollPane scpDT = new JScrollPane(tableDT, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel pnTableDT = new JPanel();
        pnTableDT.setLayout(new BoxLayout(pnTableDT, BoxLayout.Y_AXIS));
        pnTableDT.add(scpDT, BorderLayout.CENTER);

        pnMainDoanhThu.add(lbTuNgay);
        pnMainDoanhThu.add(dpTuNgay);
        pnMainDoanhThu.add(lbDenNgay);
        pnMainDoanhThu.add(dpDenNgay);
        pnMainDoanhThu.add(btnThongKe);
        pnMainDoanhThu.add(pnTableDT);

        int h = 20, wRow1 = 2;
        lbTuNgay.setBounds(10, wRow1, 70, h);
        dpTuNgay.setBounds(75, wRow1, 150, h);
        lbDenNgay.setBounds(240, wRow1, 70, h);
        dpDenNgay.setBounds(315, wRow1, 150, h);
        btnThongKe.setBounds(480, wRow1, 100, h);
        pnTableDT.setBounds(10, 25, widthPn - 35, heightPn - 95);

        btnThongKe.addActionListener(this);
    }

    public void createTabPhong() {
        pnMainPhong = new JPanel();
        pnMainPhong.setBounds(0, 0, widthPn, heightPn);
        pnMainPhong.setLayout(null);

        String[] cols = { "Mã phòng", "Tên phòng", "Sức chứa", "Số Giường", "Tình Trạng", "Loại Phòng" };
        pnPhong = new kPnDataListView(cols);
        pnPhong.setBounds(0, 0, pnPhong.getWidth(), pnPhong.getHeight());
        pnMainPhong.add(pnPhong);

        JPanel pnSearchPhong = new JPanel();
        pnSearchPhong.setLayout(null);
        pnSearchPhong.setBounds(467, 0, 282, 36);
        pnMainPhong.add(pnSearchPhong);

        btnTimPhong = new JButton("Tìm");
        btnTimPhong.setBounds(192, 0, 89, 36);
        pnSearchPhong.add(btnTimPhong);

        txtTimPhong = new JTextField();
        txtTimPhong.setBounds(2, 15, 185, 20);
        pnSearchPhong.add(txtTimPhong);
        txtTimPhong.setColumns(10);

        JLabel lbTimPhong = new JLabel("Tên Phòng: ");
        lbTimPhong.setBounds(2, 0, 100, 14);
        pnSearchPhong.add(lbTimPhong);

        JPanel pnInfoPhong = new JPanel();
        pnInfoPhong.setBorder(new TitledBorder(null, "Thông tin phòng"));
        pnInfoPhong.setLayout(null);
        pnInfoPhong.setBounds(467, 37, 282, 407);
        pnMainPhong.add(pnInfoPhong);

        JLabel lbMaPhong = new JLabel("Mã phòng: ");
        lbMaPhong.setBounds(10, 17, 70, 20);
        pnInfoPhong.add(lbMaPhong);

        txtMaPhong = new JTextField();
        txtMaPhong.setBounds(80, 17, 192, 20);
        pnInfoPhong.add(txtMaPhong);
        txtMaPhong.setColumns(10);

        JLabel lbTenPhong = new JLabel("Tên phòng: ");
        lbTenPhong.setBounds(10, 42, 70, 20);
        pnInfoPhong.add(lbTenPhong);

        txtTenPhong = new JTextField();
        txtTenPhong.setBounds(80, 42, 192, 20);
        pnInfoPhong.add(txtTenPhong);
        txtTenPhong.setColumns(10);

        JLabel lbSucChua = new JLabel("Sức chứa:");
        lbSucChua.setBounds(10, 67, 70, 20);
        pnInfoPhong.add(lbSucChua);

        modelSpinNumber = new SpinnerNumberModel(1, 1, 100, 1);
        spinSucChua = new JSpinner(modelSpinNumber);
        spinSucChua.setBounds(80, 67, 93, 20);
        pnInfoPhong.add(spinSucChua);

        JLabel lbSoGiuong = new JLabel("Số giường:");
        lbSoGiuong.setBounds(10, 92, 70, 20);
        pnInfoPhong.add(lbSoGiuong);

        spinSoGiuong = new JSpinner(modelSpinNumber);
        spinSoGiuong.setBounds(80, 92, 93, 20);
        pnInfoPhong.add(spinSoGiuong);

        JLabel lbTinhTrang = new JLabel("Tình trạng:");
        lbTinhTrang.setBounds(10, 117, 70, 20);
        pnInfoPhong.add(lbTinhTrang);

        cboTinhTrang = new JComboBox<String>();
        cboTinhTrang.setBounds(80, 117, 192, 20);
        cboTinhTrang.addItem("Trống");
        cboTinhTrang.addItem("Đã cho thuê");
        pnInfoPhong.add(cboTinhTrang);

        JLabel lbLoaiPhong = new JLabel("Loại phòng:");
        lbLoaiPhong.setBounds(10, 144, 70, 20);
        pnInfoPhong.add(lbLoaiPhong);

        cboLoaiPhong = new JComboBox<String>();
        cboLoaiPhong.setBounds(80, 142, 192, 20);
        pnInfoPhong.add(cboLoaiPhong);
    }

    public void createTabLPhong() {
        pnMainLPhong = new JPanel();
        pnMainLPhong.setLayout(null);

        String[] cols = { "Mã loại phòng", "Tên loại phòng", "Đơn giá" };
        pnLPhong = new kPnDataListView(cols);
        pnLPhong.setBounds(0, 0, pnLPhong.getWidth(), pnLPhong.getHeight());
        pnMainLPhong.add(pnLPhong);

        JPanel pnSearchLP = new JPanel();
        pnSearchLP.setLayout(null);
        pnSearchLP.setBounds(467, 0, 282, 36);
        pnMainLPhong.add(pnSearchLP);

        JPanel pnInfoLP = new JPanel();
        pnInfoLP.setBorder(new TitledBorder(null, "Thông tin loại phòng"));
        pnInfoLP.setLayout(null);
        pnInfoLP.setBounds(467, 37, 282, 407);
        pnMainLPhong.add(pnInfoLP);

        JLabel lbMaLP = new JLabel("Mã L.Phòng: ");
        lbMaLP.setBounds(10, 17, 85, 20);
        pnInfoLP.add(lbMaLP);

        txtMaLP = new JTextField();
        txtMaLP.setBounds(90, 17, 182, 20);
        pnInfoLP.add(txtMaLP);
        txtMaLP.setColumns(10);

        JLabel lbTenLP = new JLabel("Tên L.Phòng: ");
        lbTenLP.setBounds(10, 48, 85, 20);
        pnInfoLP.add(lbTenLP);

        txtTenLP = new JTextField();
        txtTenLP.setBounds(90, 48, 182, 20);
        pnInfoLP.add(txtTenLP);
        txtTenLP.setColumns(10);

        JLabel lbDonGiaLP = new JLabel("Đơn giá:");
        lbDonGiaLP.setBounds(10, 79, 85, 20);
        pnInfoLP.add(lbDonGiaLP);

        txtDonGiaLP = new JTextField();
        txtDonGiaLP.setBounds(90, 79, 182, 20);
        pnInfoLP.add(txtDonGiaLP);
        txtDonGiaLP.setColumns(10);
    }

    public void createTabDichVu() {
        pnMainDV = new JPanel();
        pnMainDV.setLayout(null);

        String[] cols = { "Mã dịch vụ", "Tên dịch vụ", "Mã loại dịch vụ", "Đơn giá" };
        pnDichVu = new kPnDataListView(cols);
        pnDichVu.setBounds(0, 0, pnDichVu.getWidth(), pnDichVu.getHeight());
        pnMainDV.add(pnDichVu);

        JPanel pnSearchDV = new JPanel();
        pnSearchDV.setLayout(null);
        pnSearchDV.setBounds(467, 0, 282, 36);
        pnMainDV.add(pnSearchDV);

        btnTimDV = new JButton("Tìm");
        btnTimDV.setBounds(192, 0, 89, 36);
        pnSearchDV.add(btnTimDV);

        txtTimDV = new JTextField();
        txtTimDV.setBounds(2, 14, 185, 20);
        pnSearchDV.add(txtTimDV);
        txtTimDV.setColumns(10);

        JLabel lbTimDV = new JLabel("Tên dịch vụ: ");
        lbTimDV.setBounds(2, 0, 100, 14);
        pnSearchDV.add(lbTimDV);

        JPanel pnInfoDV = new JPanel();
        pnInfoDV.setBorder(new TitledBorder(null, "Thông tin dịch vụ"));
        pnInfoDV.setLayout(null);
        pnInfoDV.setBounds(467, 37, 282, 395);
        pnMainDV.add(pnInfoDV);

        JLabel lbMaDV = new JLabel("Mã DV: ");
        lbMaDV.setBounds(10, 17, 70, 20);
        pnInfoDV.add(lbMaDV);

        txtMaDV = new JTextField();
        txtMaDV.setBounds(80, 17, 192, 20);
        pnInfoDV.add(txtMaDV);
        txtMaDV.setColumns(10);

        JLabel lbTenDV = new JLabel("Tên DV: ");
        lbTenDV.setBounds(10, 48, 70, 20);
        pnInfoDV.add(lbTenDV);

        txtTenDV = new JTextField();
        txtTenDV.setBounds(80, 48, 192, 20);
        pnInfoDV.add(txtTenDV);
        txtTenDV.setColumns(10);

        JLabel lbSucChua = new JLabel("Đơn giá:");
        lbSucChua.setBounds(10, 110, 70, 20);
        pnInfoDV.add(lbSucChua);

        JLabel lbLoaiDV = new JLabel("Loại DV: ");
        lbLoaiDV.setBounds(10, 79, 70, 20);
        pnInfoDV.add(lbLoaiDV);

        cboLoaiDV = new JComboBox<String>();
        cboLoaiDV.setBounds(80, 79, 192, 20);
        pnInfoDV.add(cboLoaiDV);

        txtDonGiaDV = new JTextField();
        txtDonGiaDV.setBounds(80, 110, 192, 20);
        pnInfoDV.add(txtDonGiaDV);
        txtDonGiaDV.setColumns(10);
    }

    public void createTabLDichVu() {
        pnMainLDV = new JPanel();
        pnMainLDV.setLayout(null);

        String[] cols = { "Mã loại dịch vụ", "Tên loại dịch vụ" };
        pnLDichVu = new kPnDataListView(cols);
        pnLDichVu.setBounds(0, 0, pnLDichVu.getWidth(), pnLDichVu.getHeight());
        pnMainLDV.add(pnLDichVu);

        JPanel pnSearchLDV = new JPanel();
        pnSearchLDV.setLayout(null);
        pnSearchLDV.setBounds(467, 0, 282, 36);
        pnMainLDV.add(pnSearchLDV);

        JPanel pnInfoLDV = new JPanel();
        pnInfoLDV.setBorder(new TitledBorder(null, "Thông tin loại dịch vụ"));
        pnInfoLDV.setLayout(null);
        pnInfoLDV.setBounds(467, 37, 282, 406);
        pnMainLDV.add(pnInfoLDV);

        JLabel lbMaLDV = new JLabel("Mã loại DV: ");
        lbMaLDV.setBounds(10, 17, 80, 20);
        pnInfoLDV.add(lbMaLDV);

        txtMaLDV = new JTextField();
        txtMaLDV.setBounds(90, 17, 180, 20);
        pnInfoLDV.add(txtMaLDV);
        txtMaLDV.setColumns(10);

        JLabel lbTenLDV = new JLabel("Tên loại DV: ");
        lbTenLDV.setBounds(10, 48, 80, 20);
        pnInfoLDV.add(lbTenLDV);

        txtTenLDV = new JTextField();
        txtTenLDV.setBounds(92, 48, 180, 20);
        pnInfoLDV.add(txtTenLDV);
        txtTenLDV.setColumns(10);
    }

    public void createTabNhanVien() {
        pnMainNV = new JPanel();
        pnMainNV.setLayout(null);

        String[] cols = { "Mã nhân viên", "Tên nhân viên", "CCCD", "Email", "SDT", "Lương" };
        pnNhanVien = new kPnDataListView(cols);
        pnNhanVien.setBounds(0, 0, pnNhanVien.getWidth(), pnNhanVien.getHeight());
        pnMainNV.add(pnNhanVien);

        JPanel pnSearchNV = new JPanel();
        pnSearchNV.setLayout(null);
        pnSearchNV.setBounds(467, 0, 282, 36);
        pnMainNV.add(pnSearchNV);

        btnTimNV = new JButton("Tìm");
        btnTimNV.setBounds(192, 0, 89, 36);
        pnSearchNV.add(btnTimNV);

        txtTimNV = new JTextField();
        txtTimNV.setBounds(2, 15, 185, 20);
        pnSearchNV.add(txtTimNV);
        txtTimNV.setColumns(10);

        JLabel lbTimNV = new JLabel("Tên nhân viên: ");
        lbTimNV.setBounds(2, 0, 100, 14);
        pnSearchNV.add(lbTimNV);

        JPanel pnInfoNV = new JPanel();
        pnInfoNV.setBorder(new TitledBorder(null, "Thông tin nhân viên"));
        pnInfoNV.setLayout(null);
        pnInfoNV.setBounds(467, 37, 282, 393);
        pnMainNV.add(pnInfoNV);

        JLabel lbMaNV = new JLabel("Mã nhân viên: ");
        lbMaNV.setBounds(10, 17, 90, 20);
        pnInfoNV.add(lbMaNV);

        txtMaNV = new JTextField();
        txtMaNV.setBounds(102, 17, 170, 20);
        pnInfoNV.add(txtMaNV);
        txtMaNV.setColumns(10);

        JLabel lbTenNV = new JLabel("Tên nhân viên: ");
        lbTenNV.setBounds(10, 48, 95, 20);
        pnInfoNV.add(lbTenNV);

        txtTenNV = new JTextField();
        txtTenNV.setBounds(102, 48, 170, 20);
        pnInfoNV.add(txtTenNV);
        txtTenNV.setColumns(10);

        JLabel lbCccd = new JLabel("CCCD:");
        lbCccd.setBounds(12, 80, 90, 16);
        pnInfoNV.add(lbCccd);

        txtCccd = new JTextField();
        txtCccd.setBounds(102, 78, 170, 20);
        pnInfoNV.add(txtCccd);
        txtCccd.setColumns(10);

        JLabel lbEmail = new JLabel("Email:");
        lbEmail.setBounds(10, 108, 90, 16);
        pnInfoNV.add(lbEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(102, 106, 170, 20);
        pnInfoNV.add(txtEmail);
        txtEmail.setColumns(10);

        JLabel lbSdt = new JLabel("SDT:");
        lbSdt.setBounds(10, 136, 90, 16);
        pnInfoNV.add(lbSdt);

        txtSdt = new JTextField();
        txtSdt.setBounds(102, 134, 170, 20);
        pnInfoNV.add(txtSdt);
        txtSdt.setColumns(10);

        JLabel lbLuong = new JLabel("Lương:");
        lbLuong.setBounds(10, 164, 90, 16);
        pnInfoNV.add(lbLuong);

        txtLuong = new JTextField();
        txtLuong.setBounds(102, 162, 170, 20);
        pnInfoNV.add(txtLuong);
        txtLuong.setColumns(10);
    }

    public void createTabKH() {
        pnMainKH = new JPanel();
        pnMainKH.setLayout(null);

        String[] cols = { "Mã khách hàng", "Tên khách hàng", "CCCD", "Loại Khách" };
        pnKhachHang = new kPnDataListView(cols);
        pnKhachHang.setBounds(0, 0, pnKhachHang.getWidth(), pnKhachHang.getHeight());
        pnMainKH.add(pnKhachHang);

        JPanel pnSearchKH = new JPanel();
        pnSearchKH.setLayout(null);
        pnSearchKH.setBounds(467, 0, 282, 36);
        pnMainKH.add(pnSearchKH);

        btnTimKH = new JButton("Tìm");
        btnTimKH.setBounds(192, 0, 89, 36);
        pnSearchKH.add(btnTimKH);

        txtTimKH = new JTextField();
        txtTimKH.setBounds(2, 15, 185, 20);
        pnSearchKH.add(txtTimKH);
        txtTimKH.setColumns(10);

        JLabel lbTimKH = new JLabel("Tên khách hàng: ");
        lbTimKH.setBounds(2, 0, 100, 14);
        pnSearchKH.add(lbTimKH);

        JPanel pnInfoKH = new JPanel();
        pnInfoKH.setBorder(new TitledBorder(null, "Thông tin khách hàng"));
        pnInfoKH.setLayout(null);
        pnInfoKH.setBounds(467, 37, 282, 393);
        pnMainKH.add(pnInfoKH);

        JLabel lbMaKH = new JLabel("Mã khách hàng: ");
        lbMaKH.setBounds(10, 17, 95, 20);
        pnInfoKH.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setBounds(105, 17, 167, 20);
        pnInfoKH.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTenKH = new JLabel("Tên KH: ");
        lbTenKH.setBounds(10, 48, 92, 20);
        pnInfoKH.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(105, 48, 167, 20);
        pnInfoKH.add(txtTenKH);
        txtTenKH.setColumns(10);

        JLabel lbCccd = new JLabel("CCCD:");
        lbCccd.setBounds(12, 80, 92, 16);
        pnInfoKH.add(lbCccd);

        txtCccd = new JTextField();
        txtCccd.setBounds(105, 78, 167, 20);
        pnInfoKH.add(txtCccd);
        txtCccd.setColumns(10);

        JLabel lbEmail = new JLabel("Loại KH:");
        lbEmail.setBounds(10, 108, 92, 16);
        pnInfoKH.add(lbEmail);

        cboLoaiKH = new JComboBox<String>();
        cboLoaiKH.setBounds(105, 106, 167, 20);
        cboLoaiKH.addItem("Việt Nam");
        cboLoaiKH.addItem("Nước ngoài");
        pnInfoKH.add(cboLoaiKH);
    }

    public void createTabTK() {
        pnMainTK = new JPanel();
        pnMainTK.setLayout(null);

        String[] cols = { "Username", "Tên nhân viên", "Mã nhân viên", "Loại tài khoản" };
        pnTaiKhoan = new kPnDataListView(cols);
        pnTaiKhoan.setBounds(0, 0, pnTaiKhoan.getWidth(), pnTaiKhoan.getHeight());
        pnMainTK.add(pnTaiKhoan);

        JPanel pnSearchTK = new JPanel();
        pnSearchTK.setLayout(null);
        pnSearchTK.setBounds(467, 0, 282, 36);
        pnMainTK.add(pnSearchTK);

        btnTimTK = new JButton("Tìm");
        btnTimTK.setBounds(192, 0, 89, 36);
        pnSearchTK.add(btnTimTK);

        txtTimTK = new JTextField();
        txtTimTK.setBounds(2, 15, 185, 20);
        pnSearchTK.add(txtTimTK);
        txtTimTK.setColumns(10);

        JLabel lbTimTK = new JLabel("Tên nhân viên: ");
        lbTimTK.setBounds(2, 0, 100, 14);
        pnSearchTK.add(lbTimTK);

        JPanel pnInfoTK = new JPanel();
        pnInfoTK.setBorder(new TitledBorder(null, "Thông tin tài khoản"));
        pnInfoTK.setLayout(null);
        pnInfoTK.setBounds(467, 37, 282, 393);
        pnMainTK.add(pnInfoTK);

        JLabel lbUserName = new JLabel("Username:");
        lbUserName.setBounds(10, 17, 92, 20);
        pnInfoTK.add(lbUserName);

        txtUserName = new JTextField();
        txtUserName.setBounds(100, 17, 172, 20);
        pnInfoTK.add(txtUserName);
        txtUserName.setColumns(10);

        JLabel lbPassWord = new JLabel("Password:");
        lbPassWord.setBounds(10, 48, 92, 20);
        pnInfoTK.add(lbPassWord);

        txtPassWord = new JPasswordField();
        txtPassWord.setBounds(100, 48, 172, 20);
        pnInfoTK.add(txtPassWord);
        txtPassWord.setColumns(10);

        JLabel lbMaNVTK = new JLabel("Mã NV:");
        lbMaNVTK.setBounds(12, 80, 92, 16);
        pnInfoTK.add(lbMaNVTK);

        txtMaNVTK = new JTextField();
        txtMaNVTK.setBounds(100, 78, 172, 20);
        pnInfoTK.add(txtMaNVTK);
        txtMaNVTK.setColumns(10);

        JLabel lbLoaiTK = new JLabel("Loại TK:");
        lbLoaiTK.setBounds(10, 136, 92, 16);
        pnInfoTK.add(lbLoaiTK);

        cboLoaiTK = new JComboBox<String>();
        cboLoaiTK.setBounds(100, 134, 172, 20);
        cboLoaiTK.addItem("Nhân viên");
        cboLoaiTK.addItem("Admin");
        pnInfoTK.add(cboLoaiTK);

        JLabel lbTenNVTK = new JLabel("Tên NV:");
        lbTenNVTK.setBounds(10, 108, 55, 16);
        pnInfoTK.add(lbTenNVTK);

        txtTenNVTK = new JTextField();
        txtTenNVTK.setBounds(100, 106, 172, 20);
        pnInfoTK.add(txtTenNVTK);
        txtTenNVTK.setColumns(10);

        JButton btnResetPass = new JButton("Đặt lại mật khẩu");
        btnResetPass.setBounds(134, 166, 136, 26);
        pnInfoTK.add(btnResetPass);

        docDSTaiKhoan();
        tableTK = pnTaiKhoan.getTable();
        tableTK.addMouseListener(this);
    }

    public static void main(String[] args) {
        new fAdmin().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Object o = e.getSource();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String[] data = pnTaiKhoan.getSelectedRowData();
        txtUserName.setText(data[0]);
        // txt 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void docDSTaiKhoan() {
        ArrayList<Vector<String>> accountList = tkDao.getAllAccounts();
        for (int i = 0; i < accountList.size(); i++) {
            Vector<String> tk = accountList.get(i);
            DefaultTableModel modelTK = pnTaiKhoan.getDefaultTableModel();
            String loaiTK = Integer.parseInt(tk.get(3)) == 1 ? "Admin" : "Nhân viên";
            modelTK.addRow(new Object[] { tk.get(0), tk.get(1), tk.get(2), loaiTK });
        }
    }
}
