package app;

import javax.swing.*;
import java.awt.event.*;
import DAO.*;

public class fAdmin extends JDialog implements ActionListener, MouseListener {
    private JTabbedPane tpTabMain;
    private JPanel pnMainDoanhThu, pnMainPhong, pnMainLPhong, pnMainDV, pnMainLDV, pnMainNV, pnMainKH, pnMainTK;
    int widthPn = 770, heightPn = 500;

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

        pnDoanhThu jDoanhThu = new pnDoanhThu();
        pnMainDoanhThu.add(jDoanhThu);
    }

    public void createTabPhong() {
        pnMainPhong = new JPanel();
        pnMainPhong.setBounds(0, 0, widthPn, heightPn);
        pnMainPhong.setLayout(null);

        pnPhong jPhong = new pnPhong();
        pnMainPhong.add(jPhong);
    }

    public void createTabLPhong() {
        pnMainLPhong = new JPanel();
        pnMainLPhong.setLayout(null);

        pnLoaiPhong jLoaiPhong = new pnLoaiPhong();
        pnMainLPhong.add(jLoaiPhong);
    }

    public void createTabDichVu() {
        pnMainDV = new JPanel();
        pnMainDV.setLayout(null);

        pnDichVu jDichVu = new pnDichVu();
        pnMainDV.add(jDichVu);
    }

    public void createTabLDichVu() {
        pnMainLDV = new JPanel();
        pnMainLDV.setLayout(null);

        pnLoaiDichVu jLDichVu = new pnLoaiDichVu();
        pnMainLDV.add(jLDichVu);
    }

    public void createTabNhanVien() {
        pnMainNV = new JPanel();
        pnMainNV.setLayout(null);

        pnNhanVien jNhanVien = new pnNhanVien();
        pnMainNV.add(jNhanVien);
    }

    public void createTabKH() {
        pnMainKH = new JPanel();
        pnMainKH.setLayout(null);

        pnKhachHang jKhachHang = new pnKhachHang();
        pnMainKH.add(jKhachHang);
    }

    public void createTabTK() {
        pnMainTK = new JPanel();
        pnMainTK.setLayout(null);
        pnMainTK.setBounds(0, 0, widthPn, heightPn);

        pnTaiKhoan fTaiKhoan = new pnTaiKhoan();
        pnMainTK.add(fTaiKhoan);
    }

    public static void main(String[] args) {
        new fAdmin().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
}
