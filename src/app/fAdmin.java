package app;

import javax.swing.*;
// import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class fAdmin extends JDialog implements ActionListener {

    private JTabbedPane tpTabMain;
    private JPanel pnMainDoanhThu, pnMainPhong, pnMainDMPhong, pnMainDV, pnMainDMDV, pnMainNV, pnMainKH;
    private JComboBox<String> cboTuNgay, cboDenNgay;

    public fAdmin() {
        setTitle("Quản lý");
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        createTabDoanhThu();
        createTabPhong();
        createTabDMPhong();
        createTabDichVu();
        createTabDMDichVu();
        createTabNhanVien();
        createTabKH();
        createTabControl();
    }

    public void createTabControl() {
        tpTabMain = new JTabbedPane();
        tpTabMain.addTab("Doanh thu", null, pnMainDoanhThu, "Quản lý doanh thu");
        tpTabMain.addTab("Phòng", null, pnMainPhong, "Quản lý phòng");
        tpTabMain.addTab("Danh Mục Phòng", null, pnMainDMPhong, "Quản lý danh mục phòng");
        tpTabMain.addTab("Dịch Vụ", null, pnMainDV, "Quản lý dịch vụ");
        tpTabMain.addTab("Danh Mục Dịch Vụ", null, pnMainDMDV, "Quản lý danh mục dịch vụ");
        tpTabMain.addTab("Nhân viên", null, pnMainNV, "Quản lý nhân viên");
        tpTabMain.addTab("Khách hàng", null, pnMainKH, "Quản lý Khách hàng");

        this.add(tpTabMain);
    }

    public void createTabDoanhThu() {
        pnMainDoanhThu = new JPanel();
        // pnMainDoanhThu.setBounds(0, 0, 300, 300);
        pnMainDoanhThu.setLayout(null);
        pnMainDoanhThu.setBackground(Color.BLUE);

        JLabel lbTu = new JLabel("Từ: ");
        JLabel lbDen = new JLabel("Đến: ");

        
    }

    public void createTabPhong() {
        pnMainPhong = new JPanel();
        // pnMainPhong.setBounds(0, 0, 300, 300);
        pnMainPhong.setLayout(null);
        pnMainPhong.setBackground(Color.RED);
    }

    public void createTabDMPhong() {
        pnMainDMPhong = new JPanel();
        // pnMainPhong.setBounds(0, 0, 300, 300);
        pnMainPhong.setLayout(null);
        pnMainPhong.setBackground(Color.pink);
    }

    public void createTabDichVu() {
        pnMainDV = new JPanel();
        // pnMainDV.setBounds(0, 0, 300, 300);
        pnMainDV.setLayout(null);
        pnMainDV.setBackground(Color.CYAN);
    }

    public void createTabDMDichVu() {
        pnMainDMDV = new JPanel();
        // pnMainDMDV.setBounds(0, 0, 300, 300);
        pnMainDV.setLayout(null);
        pnMainDV.setBackground(Color.CYAN);
    }

    public void createTabNhanVien() {
        pnMainNV = new JPanel();
        // pnMainNhanVien.setBounds(0, 0, 300, 300);
        pnMainNV.setLayout(null);
        pnMainNV.setBackground(Color.GREEN);
    }

    public void createTabKH() {
        pnMainKH = new JPanel();
        // pnMainKH.setBounds(0, 0, 300, 300);
        pnMainKH.setLayout(null);
        pnMainKH.setBackground(Color.YELLOW);
    }

    public static void main(String[] args) {
        new fAdmin().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
