package UI.PanelCustom;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import DAO.*;
import entity.Account;

public class pnAccount extends JPanel implements ActionListener, MouseListener {
    int widthPn = 770, heightPn = 500;
    private JPanel pnMain;
    private DefaultTableModel modelTable;
    private JTable table;
    private JButton btnTim, btnThem, btnXoa, btnXoaTrang, btnSua, btnXem;
    private JTextField txtTim, txtMa, txtTen;
    private JComboBox<String> cboLoaiMon;
    AccountDAO accountList = AccountDAO.getInstance();

    public pnAccount() {
        setSize(760, 440);
        // setResizable(false);
        // setLocationRelativeTo(null);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));

        pnMain = new JPanel();
        pnMain.setLayout(null);
        this.add(pnMain);

        JPanel pnBtn = new JPanel();
        pnBtn.setLayout(null);
        pnBtn.setBounds(0, 0, 467, 36);
        pnMain.add(pnBtn);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(0, 0, 75, 36);
        pnBtn.add(btnThem);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(87, 0, 75, 36);
        pnBtn.add(btnXoa);

        btnXoaTrang = new JButton("Xóa Trắng");
        btnXoaTrang.setBounds(174, 0, 117, 36);
        pnBtn.add(btnXoaTrang);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(303, 0, 75, 36);
        pnBtn.add(btnSua);

        btnXem = new JButton("Xem");
        btnXem.setBounds(390, 0, 75, 36);
        pnBtn.add(btnXem);

        JPanel pnListView = new JPanel();
        pnListView.setBounds(0, 37, 467, 392);
        pnMain.add(pnListView);
        pnListView.setLayout(new BorderLayout(0, 0));

        JScrollPane scp = new JScrollPane();
        pnListView.add(scp);

        String[] cols = { "Username", "Tên hiển thị", "Loại tài khoản" };
        modelTable = new DefaultTableModel(cols, 0) {
            // khóa không cho người dùng nhập trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table = new JTable(modelTable);
        scp.setViewportView(table);

        this.add(pnMain);

        JPanel pnSearchDV = new JPanel();
        pnSearchDV.setLayout(null);
        pnSearchDV.setBounds(467, 0, 282, 36);
        pnMain.add(pnSearchDV);

        btnTim = new JButton("Tìm");
        btnTim.setBounds(192, 0, 89, 36);
        pnSearchDV.add(btnTim);

        txtTim = new JTextField();
        txtTim.setBounds(2, 14, 185, 20);
        pnSearchDV.add(txtTim);
        txtTim.setColumns(10);

        JLabel lbTim = new JLabel("Username: ");
        lbTim.setBounds(2, 0, 100, 14);
        pnSearchDV.add(lbTim);

        JPanel pnInfo = new JPanel();
        pnInfo.setBorder(new TitledBorder(null, "Thông tin tài khoản"));
        pnInfo.setLayout(null);
        pnInfo.setBounds(467, 37, 282, 392);
        pnMain.add(pnInfo);

        JLabel lbMa = new JLabel("Username:");
        lbMa.setBounds(10, 17, 88, 20);
        pnInfo.add(lbMa);

        txtMa = new JTextField();
        txtMa.setEditable(false);
        txtMa.setBounds(97, 17, 175, 20);
        pnInfo.add(txtMa);
        txtMa.setColumns(10);

        JLabel lbTen = new JLabel("Tên hiển thị:");
        lbTen.setBounds(10, 48, 88, 20);
        pnInfo.add(lbTen);

        txtTen = new JTextField();
        txtTen.setBounds(97, 48, 175, 20);
        pnInfo.add(txtTen);
        txtTen.setColumns(10);

        JLabel lbLoaiMon = new JLabel("Loại tài khoản:");
        lbLoaiMon.setBounds(10, 79, 88, 20);
        pnInfo.add(lbLoaiMon);

        cboLoaiMon = new JComboBox<String>();
        cboLoaiMon.setBounds(97, 79, 175, 20);
        pnInfo.add(cboLoaiMon);

        JButton btnNewButton = new JButton("Đặt lại mật khẩu");
        btnNewButton.setBounds(143, 112, 127, 26);
        pnInfo.add(btnNewButton);

        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnSua.addActionListener(this);
        btnXem.addActionListener(this);

        table.addMouseListener(this);
        loadAccountList();
    }

    public static void main(String[] args) {
        new pnAccount().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // int row = table.getSelectedRow();

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

    private void loadAccountList() {
        ArrayList<Account> dataList = accountList.getListAccount();
        for (Account account : dataList) {
            String type = "Nhân viên";
            if (account.getType() == 1)
                type = "Admin";
            modelTable.addRow(new Object[] { account.getUsername(), account.getDisplayName(), type});
        }
    }
}
