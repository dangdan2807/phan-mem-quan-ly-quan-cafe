package UI;

import javax.swing.*;
// import javax.swing.event.*;
// import java.awt.*;
import java.awt.event.*;

public class fAccountProfile extends JDialog implements ActionListener {
    private JLabel lbUserName, lbDisplayName, lbPassWord, lbNewPassWord, lbReNewPassWord;
    private JTextField txtUserName, txtDisplayName, txtPassWord, txtNewPassWord, txtReNewPassWord;
    private JButton btnUpdate, btnClose;
    // 450
    int withPn = 400, heightPn = 315;

    // chưa bắt sự kiện phím enter để submit
    public fAccountProfile() {
        setTitle("Thông tin tài khoản");
        setSize(400, 222);
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
        lbPassWord = new JLabel("Mật khẩu: ");
        lbNewPassWord = new JLabel("Mật khẩu mới: ");
        lbReNewPassWord = new JLabel("Nhập lại: ");

        txtUserName = new JTextField();
        txtUserName.setEditable(false);
        txtDisplayName = new JTextField();
        txtPassWord = new JTextField();
        txtNewPassWord = new JTextField();
        txtReNewPassWord = new JTextField();

        btnUpdate = new JButton("Cập nhật");
        btnClose = new JButton("Thoát");

        pnMain.add(lbUserName);
        pnMain.add(txtUserName);
        pnMain.add(lbDisplayName);
        pnMain.add(txtDisplayName);
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
        lbPassWord.setBounds(10, 67, wlb, h);
        txtPassWord.setBounds(130, 67, wtxt, h);
        lbNewPassWord.setBounds(10, 97, wlb, h);
        txtNewPassWord.setBounds(130, 97, wtxt, h);
        lbReNewPassWord.setBounds(10, 127, wlb, h);
        txtReNewPassWord.setBounds(130, 127, wtxt, h);
        btnUpdate.setBounds(175, 157, 100, h);
        btnClose.setBounds(280, 157, 100, h);

        getContentPane().add(pnMain);
    }

    public static void main(String[] args) {
        new fAccountProfile().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}