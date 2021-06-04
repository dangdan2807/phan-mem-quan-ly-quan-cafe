package UI;

import javax.swing.*;
import java.awt.event.*;

import DAO.AccountDAO;
import UI.PanelCustom.*;
import entity.Account;

public class fAdmin extends JFrame implements ActionListener {
    private static fAdmin instance;

    public static fAdmin getInstance(Account loginAccount) {
        if (instance == null)
            instance = new fAdmin(loginAccount);
        return instance;
    }

    private JTabbedPane tpTabMain;
    // private ImageIcon userIcon = new ImageIcon("img/user_16.png");
    private Account loginAccount = null;

    private pnRevenue pnRevenue;
    private pnProduct pnProduct;

    public fAdmin(Account account) {
        setTitle("Quản Lý Hệ Thống");
        setSize(1280, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.loginAccount = account;
        createTabControl();
    }

    public void createTabControl() {
        tpTabMain = new JTabbedPane();
        pnRevenue = new pnRevenue();
        pnProduct = new pnProduct();
        // pnCategory pnCategory = new pnCategory();
        // pnTable pnTable = new pnTable();
        // pnAccount pnAccount = new pnAccount();
        tpTabMain.addTab("Doanh thu", null, pnRevenue, "Quản lý doanh thu");
        tpTabMain.addTab("Sản phẩm", null, pnProduct, "Quản lý sản phẩm");
        // tpTabMain.addTab("Loại sản phẩm", null, pnCategory, "Quản lý loại sản phẩm");
        // tpTabMain.addTab("Bàn", null, pnTable, "Quản lý bàn");
        // tpTabMain.addTab("Tài Khoản", userIcon, pnAccount, "Quản lý tài khoản");
        this.add(tpTabMain);

        pnRevenue.getBtnLogOut().addActionListener(this);
        pnRevenue.getBtnBack().addActionListener(this);
        pnProduct.getBtnLogOut().addActionListener(this);
        pnProduct.getBtnBack().addActionListener(this);
    }

    public static void main(String[] args) {
        Account loginAccount = AccountDAO.getInstance().getAccountByUsername("admin");
        new fAdmin(loginAccount).setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(pnRevenue.getBtnLogOut()) || o.equals(pnProduct.getBtnLogOut())) {
            EventLogOut();
        } else if (o.equals(pnRevenue.getBtnBack()) || o.equals(pnProduct.getBtnBack())) {
            EventExit();
        }
    }

    public void EventLogOut() {
        fLogin f = new fLogin();
        setVisible(false);
        f.setVisible(true);
    }

    public void EventExit() {
        fPageNavigation f = new fPageNavigation(loginAccount);
        setVisible(false);
        f.setVisible(true);
    }
}
