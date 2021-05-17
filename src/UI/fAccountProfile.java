package UI;

import javax.swing.*;
// import javax.swing.event.*;
// import java.awt.*;
import java.awt.event.*;

public class fAccountProfile extends JDialog implements ActionListener {
    private JLabel lbUserName, lbDisplayName, lbPassWord, lbNewPassWord, lbReNewPassWord, lbEmail, lbSdt, lbCCCD;
    private JTextField txtUserName, txtDisplayName, txtPassWord, txtNewPassWord, txtReNewPassWord, txtEmail, txtSdt,
            txtCCCD;
    private JButton btnUpdate, btnClose;
    private JCheckBox chkHienCCCD;
    // 450
    int withPn = 400, heightPn = 315;

    // chưa bắt sự kiện phím enter để submit
    public fAccountProfile() {
        setTitle("Thông tin tài khoản");
        setSize(withPn, heightPn);
        setResizable(false);
        setLocationRelativeTo(null);

        createFormAccountProfile();
    }

    public void createFormAccountProfile() {
        JPanel pnMain = new JPanel();
        pnMain.setBounds(0, 0, withPn, heightPn);
        pnMain.setLayout(null);

        lbUserName = new JLabel("Tên đăng nhập: ");
        lbDisplayName = new JLabel("Tên nhân viên: ");
        lbCCCD = new JLabel("Căn cước công dân:");
        lbEmail = new JLabel("Email: ");
        lbSdt = new JLabel("Số điện thoại: ");
        lbPassWord = new JLabel("Mật khẩu: ");
        lbNewPassWord = new JLabel("Mật khẩu mới: ");
        lbReNewPassWord = new JLabel("Nhập lại: ");

        txtUserName = new JTextField();
        txtUserName.setEditable(false);
        txtDisplayName = new JTextField();
        txtDisplayName.setEditable(false);
        txtCCCD = new JTextField();
        txtCCCD.setEditable(false);
        txtEmail = new JTextField();
        txtSdt = new JTextField();
        txtPassWord = new JTextField();
        txtNewPassWord = new JTextField();
        txtReNewPassWord = new JTextField();

        btnUpdate = new JButton("Cập nhật");
        btnClose = new JButton("Thoát");

        chkHienCCCD = new JCheckBox("Hiện");

        pnMain.add(lbUserName);
        pnMain.add(txtUserName);
        pnMain.add(lbDisplayName);
        pnMain.add(txtDisplayName);
        pnMain.add(lbCCCD);
        pnMain.add(txtCCCD);
        pnMain.add(chkHienCCCD);
        pnMain.add(lbEmail);
        pnMain.add(txtEmail);
        pnMain.add(lbSdt);
        pnMain.add(txtSdt);
        pnMain.add(lbPassWord);
        pnMain.add(txtPassWord);
        pnMain.add(lbNewPassWord);
        pnMain.add(txtNewPassWord);
        pnMain.add(lbReNewPassWord);
        pnMain.add(txtReNewPassWord);
        pnMain.add(btnUpdate);
        pnMain.add(btnClose);

        int wlb = 120, wtxt = 250, h = 25, x1 = 10, x2 = 130;
        lbUserName.setBounds(x1, 5, wlb, h);
        txtUserName.setBounds(x2, 5, wtxt, h);
        lbDisplayName.setBounds(x1, 35, wlb, h);
        txtDisplayName.setBounds(x2, 35, wtxt, h);
        lbCCCD.setBounds(x1, 65, wlb, h);
        txtCCCD.setBounds(x2, 65, wtxt - 50, h);
        chkHienCCCD.setBounds(330, 65, 100, h);
        lbEmail.setBounds(x1, 95, wlb, h);
        txtEmail.setBounds(x2, 95, wtxt, h);
        lbSdt.setBounds(x1, 125, wlb, h);
        txtSdt.setBounds(x2, 125, wtxt, h);
        lbPassWord.setBounds(x1, 155, wlb, h);
        txtPassWord.setBounds(x2, 155, wtxt, h);
        lbNewPassWord.setBounds(x1, 185, wlb, h);
        txtNewPassWord.setBounds(x2, 185, wtxt, h);
        lbReNewPassWord.setBounds(x1, 215, wlb, h);
        txtReNewPassWord.setBounds(x2, 215, wtxt, h);
        btnUpdate.setBounds(175, 245, 100, h);
        btnClose.setBounds(280, 245, 100, h);

        this.add(pnMain);
    }

    public static void main(String[] args) {
        new fAccountProfile().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}