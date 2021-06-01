package UI;

import javax.swing.*;

import UI.PanelCustom.*;

import java.awt.event.*;

public class fAdmin extends JFrame implements ActionListener, MouseListener {
    private JTabbedPane tpTabMain;
    // private ImageIcon userIcon = new ImageIcon("img/user_16.png");

    public fAdmin() {
        setTitle("Quản lý Hệ Thống");
        setSize(1280, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createTabControl();
    }

    public void createTabControl() {
        tpTabMain = new JTabbedPane();
        pnRevenue pnRevenue = new pnRevenue();
        // pnProduct pnProduct = new pnProduct();
        // pnCategory pnCategory = new pnCategory();
        // pnTable pnTable = new pnTable();
        // pnAccount pnAccount = new pnAccount();
        tpTabMain.addTab("Doanh thu", null, pnRevenue, "Quản lý doanh thu");
        // tpTabMain.addTab("Sản phẩm", null, pnProduct, "Quản lý sản phẩm");
        // tpTabMain.addTab("Loại sản phẩm", null, pnCategory, "Quản lý loại sản phẩm");
        // tpTabMain.addTab("Bàn", null, pnTable, "Quản lý bàn");
        // tpTabMain.addTab("Tài Khoản", userIcon, pnAccount, "Quản lý tài khoản");
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
