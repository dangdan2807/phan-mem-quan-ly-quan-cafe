package UI;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import DAO.AccountDAO;

public class fLogin extends JFrame implements ActionListener, KeyListener, FocusListener, MouseListener {
    private JTextField txtUsername, txtPassword;
    private JButton btnLogin;
    private JLabel lbShowMessage;
    private ImageIcon background = new ImageIcon(
            new ImageIcon("img/background.jpg").getImage().getScaledInstance(790, 500, Image.SCALE_SMOOTH));
    private ImageIcon errorIcon = new ImageIcon("img/cancel_16.png");
    private ImageIcon LogoIcon = new ImageIcon(
            new ImageIcon("img/user_512.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
    Border borderBottomFocus = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#1a66e3"));
    Border borderBottomUnFocus = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#d0e1fd"));
    Border borderBottomError = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED);
    int w1 = 110, w2 = 170, h = 20;
    AccountDAO accountDAO = AccountDAO.getInstance();

    public fLogin() {
        setTitle("Đăng nhập");
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createFormLogin();
    }

    public void createFormLogin() {
        JPanel pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBackground(Color.decode("#ffffff"));

        JLabel thumb = new JLabel();
        thumb.setBounds(0, 0, 430, 465);
        thumb.setIcon(background);
        pnMain.add(thumb);

        JLabel lbUsername, lbPassword;
        lbUsername = new JLabel("Tên đăng nhập: ");
        lbUsername.setFont(new Font("Dialog", Font.BOLD, 14));
        lbUsername.setForeground(Color.decode("#1a66e3"));
        lbUsername.setBounds(469, 140, 285, 25);
        pnMain.add(lbUsername);

        lbPassword = new JLabel("Mật khẩu: ");
        lbPassword.setFont(new Font("Dialog", Font.BOLD, 14));
        lbPassword.setForeground(Color.decode("#1a66e3"));
        lbPassword.setBounds(469, 214, 285, 25);
        pnMain.add(lbPassword);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Dialog", Font.PLAIN, 14));
        txtUsername.setBounds(469, 177, 285, 25);
        txtUsername.setBorder(borderBottomFocus);
        pnMain.add(txtUsername);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
        txtPassword.setBounds(469, 251, 285, 25);
        txtPassword.setBorder(borderBottomUnFocus);
        pnMain.add(txtPassword);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Dialog", Font.BOLD, 14));
        btnLogin.setBorder(new LineBorder(Color.decode("#1a66e3")));
        btnLogin.setBackground(Color.decode("#d0e1fd"));
        btnLogin.setForeground(Color.decode("#1a66e3"));
        btnLogin.setBounds(469, 325, 285, 30);
        pnMain.add(btnLogin);

        getContentPane().add(pnMain);

        JLabel lbWelcome = new JLabel("Sign In!");
        lbWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lbWelcome.setFont(new Font("Dialog", Font.BOLD, 18));
        lbWelcome.setBounds(469, 93, 285, 35);
        lbWelcome.setForeground(Color.decode("#1a66e3"));
        pnMain.add(lbWelcome);

        lbShowMessage = new JLabel("");
        lbShowMessage.setFont(new Font("Dialog", Font.BOLD, 14));
        lbShowMessage.setBounds(469, 288, 285, 25);
        pnMain.add(lbShowMessage);

        JLabel lbLogo = new JLabel(LogoIcon);
        lbLogo.setText("");
        lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lbLogo.setBounds(469, 12, 285, 82);
        pnMain.add(lbLogo);
        btnLogin.addActionListener(this);
        txtUsername.addKeyListener(this);
        txtPassword.addKeyListener(this);

        txtUsername.addFocusListener(this);
        txtPassword.addFocusListener(this);

        btnLogin.addMouseListener(this);
    }

    public static void main(String[] args) {
        new fLogin().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLogin)) {
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            if (validData()) {
                if (login(username, password)) {
                    fPageNavigation f = new fPageNavigation();
                    this.setVisible(false);
                    f.setVisible(true);
                } else {
                    showMessage("Sai tài khoản hoặc mật khẩu");
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object o = e.getSource();
        // bắt sự kiện nhấn phím enter tự nhấn btnLogin
        if (o.equals(txtUsername) || o.equals(txtPassword)) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                btnLogin.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        Object o = e.getSource();
        if (o.equals(txtUsername)) {
            txtUsername.setBorder(borderBottomFocus);
        } else if (o.equals(txtPassword)) {
            txtPassword.setBorder(borderBottomFocus);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object o = e.getSource();
        if (o.equals(txtUsername)) {
            txtUsername.setBorder(borderBottomUnFocus);
        } else if (o.equals(txtPassword)) {
            txtPassword.setBorder(borderBottomUnFocus);
        }
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
        if (o.equals(btnLogin)) {
            btnLogin.setBackground(Color.BLUE);
            btnLogin.setForeground(Color.WHITE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLogin)) {
            btnLogin.setBackground(Color.decode("#d0e1fd"));
            btnLogin.setForeground(Color.decode("#1a66e3"));
        }
    }

    private void showMessage(String message) {
        lbShowMessage.setForeground(Color.RED);
        lbShowMessage.setText(message);
        lbShowMessage.setIcon(errorIcon);
    }

    private boolean validData() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if (!(username.length() > 4 && username.matches("^[a-zA-Z0-9_@#]{5,}$"))) {
            if (username.length() < 5)
                showMessage("Tên đăng nhập phải tối thiểu 5 ký tự");
            else
                showMessage("Tên đăng nhập phải chứa các kỳ tự, số, @, #, _");
            txtUsername.setBorder(borderBottomError);
            return false;
        }
        if (!(password.length() > 0 && password.matches("^[a-zA-Z0-9_@#]{5,}$"))) {
            if (password.length() < 5)
                showMessage("Mật khẩu phải tối thiểu 5 ký tự");
            else
                showMessage("Mật khẩu phải chứa các kỳ tự, số, @, #, _");
            txtPassword.setBorder(borderBottomError);
            return false;
        }
        return true;
    }

    private boolean login(String userName, String passWord) {
        boolean result = accountDAO.Login(userName, passWord);
        return result;
    }
}
