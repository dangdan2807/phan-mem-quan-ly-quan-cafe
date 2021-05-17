package UI.PanelCustom;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class pnNhanVien extends JPanel implements ActionListener {
    int widthPn = 770, heightPn = 500;
    private JPanel pnMain;
    private JTextField txtLuong, txtSdt, txtTimNV, txtMaNV, txtTenNV, txtCccd, txtEmail;
    private kPnDataListView pnNhanVien;
    private JButton btnTimNV;

    public pnNhanVien() {
        setLayout(null);
        setSize(widthPn, heightPn);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, widthPn, heightPn);
        this.add(pnMain);

        String[] cols = { "Mã nhân viên", "Tên nhân viên", "CCCD", "Email", "SDT", "Lương" };
        pnNhanVien = new kPnDataListView(cols);
        pnNhanVien.setBounds(0, 0, pnNhanVien.getWidth(), pnNhanVien.getHeight());
        pnMain.add(pnNhanVien);

        JPanel pnSearchNV = new JPanel();
        pnSearchNV.setLayout(null);
        pnSearchNV.setBounds(467, 0, 282, 36);
        pnMain.add(pnSearchNV);

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
        pnMain.add(pnInfoNV);

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
