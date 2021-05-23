package UI;

import javax.swing.*;

import UI.PanelCustom.*;

import java.awt.event.*;

public class fAdmin extends JDialog implements ActionListener, MouseListener {
    private JTabbedPane tpTabMain;
    int widthPn = 770, heightPn = 500;

    public fAdmin() {
        setTitle("Quản lý");
        setSize(widthPn, heightPn);
        setResizable(false);
        setLocationRelativeTo(null);

        createTabControl();
    }

    public void createTabControl() {
        tpTabMain = new JTabbedPane();
        pnMonAn pnMonAn = new pnMonAn();
        pnDoanhThu pnDoanhThu = new pnDoanhThu();
        pnDanhMuc pnDanhMuc = new pnDanhMuc();
        pnBan pnBan = new pnBan();
        pnTaiKhoan pnTaiKhoan = new pnTaiKhoan();
        tpTabMain.addTab("Doanh thu", null, pnDoanhThu, "Quản lý doanh thu");
        tpTabMain.addTab("Món ăn", null, pnMonAn, "Quản lý món ăn");
        tpTabMain.addTab("Danh Mục", null, pnDanhMuc, "Quản lý danh mục");
        tpTabMain.addTab("Bàn", null, pnBan, "Quản lý bàn");
        tpTabMain.addTab("Tài Khoản", null, pnTaiKhoan, "Quản lý tài khoản");
        getContentPane().add(tpTabMain);
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
