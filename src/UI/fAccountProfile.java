package UI;

import javax.swing.*;

import DAO.AccountDAO;
import UI.PanelCustom.customUI;
import entity.Account;

import java.awt.Color;
// import javax.swing.event.*;
// import java.awt.*;
import java.awt.event.*;

public class fAccountProfile extends JDialog implements ActionListener, KeyListener, MouseListener {
    private JLabel lbUserName, lbDisplayName, lbPassWord, lbNewPassWord, lbReNewPassWord;
    private JTextField txtUsername, txtDisplayName, txtPassword, txtNewPassword, txtReNewPassWord;
    private JButton btnUpdate, btnClose;
    // 450
    int withPn = 400, heightPn = 315;
    Account loginAccount = null;
    fManagerSale f_ManagerSale = null;

    // chưa bắt sự kiện phím enter để submit
    public fAccountProfile(fManagerSale f_ManagerSale, Account account) {
        setTitle("Thông tin tài khoản");
        setSize(400, 222);
        setResizable(false);
        setLocationRelativeTo(null);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.loginAccount = account;
        this.f_ManagerSale = f_ManagerSale;
        createFormAccountProfile();
    }

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
        pnMain.setBackground(Color.WHITE);

        lbUserName = new JLabel("Tên đăng nhập: ");
        lbDisplayName = new JLabel("Tên nhân viên: ");
        lbPassWord = new JLabel("Mật khẩu: ");
        lbNewPassWord = new JLabel("Mật khẩu mới: ");
        lbReNewPassWord = new JLabel("Nhập lại: ");

        txtUsername = new JTextField();
        txtUsername.setEditable(false);
        txtUsername.setBackground(Color.decode("#f9f9f9"));
        txtDisplayName = new JTextField();
        txtPassword = new JPasswordField();
        txtNewPassword = new JPasswordField();
        txtReNewPassWord = new JPasswordField();

        btnUpdate = new JButton("Cập nhật");
        customUI.getInstance().setCustomBtn(btnUpdate);
        btnClose = new JButton("Thoát");
        customUI.getInstance().setCustomBtn(btnClose);

        pnMain.add(lbUserName);
        pnMain.add(txtUsername);
        pnMain.add(lbDisplayName);
        pnMain.add(txtDisplayName);
        pnMain.add(lbPassWord);
        pnMain.add(txtPassword);
        pnMain.add(lbNewPassWord);
        pnMain.add(txtNewPassword);
        pnMain.add(lbReNewPassWord);
        pnMain.add(txtReNewPassWord);
        pnMain.add(btnUpdate);
        pnMain.add(btnClose);

        int wlb = 120, wTxt = 250, h = 25, x1 = 10, x2 = 130;
        lbUserName.setBounds(x1, 5, wlb, h);
        txtUsername.setBounds(x2, 5, wTxt, h);
        lbDisplayName.setBounds(x1, 35, wlb, h);
        txtDisplayName.setBounds(x2, 35, wTxt, h);
        lbPassWord.setBounds(10, 67, wlb, h);
        txtPassword.setBounds(130, 67, wTxt, h);
        lbNewPassWord.setBounds(10, 97, wlb, h);
        txtNewPassword.setBounds(130, 97, wTxt, h);
        lbReNewPassWord.setBounds(10, 127, wlb, h);
        txtReNewPassWord.setBounds(130, 127, wTxt, h);
        btnUpdate.setBounds(175, 157, 100, h);
        btnClose.setBounds(280, 157, 100, h);

        getContentPane().add(pnMain);
        changeAccount(loginAccount);

        btnUpdate.addActionListener(this);
        btnClose.addActionListener(this);

        btnUpdate.addMouseListener(this);
        btnClose.addMouseListener(this);

        txtDisplayName.addKeyListener(this);
        txtPassword.addKeyListener(this);
        txtNewPassword.addKeyListener(this);
        txtReNewPassWord.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnUpdate)) {
            updateAccount();
        } else if (o.equals(btnClose)) {
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object o = e.getSource();
        // bắt sự kiện nhấn phím enter tự nhấn btnLogin
        if (o.equals(txtPassword) || o.equals(txtDisplayName) || o.equals(txtNewPassword)
                || o.equals(txtReNewPassWord)) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                btnUpdate.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
        Object o = e.getSource();
        if (o.equals(btnUpdate)) {
            customUI.getInstance().setCustomBtnHover(btnUpdate);
        } else if (o.equals(btnClose)) {
            customUI.getInstance().setCustomBtnHover(btnClose);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(btnUpdate)) {
            customUI.getInstance().setCustomBtn(btnUpdate);
        } else if (o.equals(btnClose)) {
            customUI.getInstance().setCustomBtn(btnClose);
        }
    }

    private void changeAccount(Account account) {
        txtUsername.setText(account.getUsername());
        txtDisplayName.setText(account.getDisplayName());
    }

    private void updateAccount() {
        String username = txtUsername.getText().trim();
        String displayName = txtDisplayName.getText().trim();
        String password = txtPassword.getText().trim();
        String reNewPassWord = txtReNewPassWord.getText().trim();
        String newPassword = txtNewPassword.getText().trim();
        if (!(newPassword.equals(reNewPassWord))) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới không khớp", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            if (AccountDAO.getInstance().updateAccount(username, displayName, password, newPassword)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                f_ManagerSale.setEmpName(displayName);
            } else if (password.equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu để cập nhật", "Thông báo",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu sai !!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}