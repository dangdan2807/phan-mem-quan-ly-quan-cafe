package UI;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class fPageNavigation extends JFrame implements ActionListener, MouseListener {

    private JButton btnLogOut, btnQLBanHang, btnQLHeThong;
    // private String username = "";

    public fPageNavigation() {
        setTitle("Điều hướng quản lý");
        setSize(600, 375);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        createFormManage();
        setCloseAction(this);
    }

    public void createFormManage() {
        JPanel pnMain = new JPanel();
        pnMain.setBackground(Color.WHITE);
        getContentPane().add(pnMain, BorderLayout.CENTER);
        pnMain.setLayout(new BorderLayout(0, 0));

        JPanel pnTitle = new JPanel();
        pnTitle.setBackground(Color.decode("#d0e1fd"));
        pnMain.add(pnTitle, BorderLayout.NORTH);

        JLabel lbTitle = new JLabel("CHÀO MỪNG BẠN ĐẾN VỚI PHẦN MỀM QUẢN LÝ QUÁN CAFE ");
        lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbTitle.setForeground(Color.decode("#1a66e3"));
        pnTitle.add(lbTitle);

        JPanel pnLeft = new JPanel();
        pnLeft.setBackground(Color.WHITE);
        pnLeft.setPreferredSize(new Dimension(295, 110));
        pnMain.add(pnLeft, BorderLayout.WEST);

        btnQLHeThong = new JButton("QUẢN TRỊ HỆ THỐNG");
        btnQLHeThong.setForeground(Color.BLUE);
        btnQLHeThong.setFont(new Font("Dialog", Font.BOLD, 20));
        btnQLHeThong.setPreferredSize(new Dimension(280, 250));
        btnQLHeThong.setBorder(new LineBorder(Color.BLUE, 2));
        btnQLHeThong.setBackground(Color.decode("#d0e1fd"));
        pnLeft.add(btnQLHeThong);

        JPanel pnRight = new JPanel();
        pnRight.setBackground(Color.WHITE);
        pnRight.setPreferredSize(new Dimension(295, 110));
        pnMain.add(pnRight, BorderLayout.EAST);

        btnQLBanHang = new JButton("QUẢN LÝ BÁN HÀNG");
        btnQLBanHang.setForeground(new Color(255, 153, 0));
        btnQLBanHang.setFont(new Font("Dialog", Font.BOLD, 20));
        btnQLBanHang.setBorder(new LineBorder(new Color(255, 140, 0), 2));
        btnQLBanHang.setPreferredSize(new Dimension(280, 250));
        btnQLBanHang.setBackground(Color.decode("#d0e1fd"));
        pnRight.add(btnQLBanHang);

        JPanel pnBottom = new JPanel();
        pnBottom.setBackground(Color.WHITE);
        pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.X_AXIS));
        pnBottom.setPreferredSize(new Dimension(280, 40));
        pnMain.add(pnBottom, BorderLayout.SOUTH);

        Component horizontalGlue = Box.createHorizontalGlue();
        pnBottom.add(horizontalGlue);

        btnLogOut = new JButton("Đăng xuất");
        btnLogOut.setBackground(Color.decode("#d0e1fd"));
        btnLogOut.setForeground(Color.BLUE);
        pnBottom.add(btnLogOut);

        Component horizontalStrut = Box.createHorizontalStrut(9);
        pnBottom.add(horizontalStrut);

        btnLogOut.addActionListener(this);
        btnQLBanHang.addActionListener(this);
        btnQLHeThong.addActionListener(this);

        btnLogOut.addMouseListener(this);
        btnQLBanHang.addMouseListener(this);
        btnQLHeThong.addMouseListener(this);
    }

    public static void main(String[] args) {
        new fPageNavigation().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLogOut)) {
            fLogin f = new fLogin();
            this.setVisible(false);
            f.setVisible(true);
        } else if (o.equals(btnQLBanHang)) {
            fManagerSale f = new fManagerSale();
            this.setVisible(false);
            f.setVisible(true);
        } else if (o.equals(btnQLHeThong)) {
            fAdmin f = new fAdmin();
            this.setVisible(false);
            f.setVisible(true);
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
        if (o.equals(btnLogOut)) {
            btnLogOut.setBackground(Color.decode("#73cdf5"));
            btnLogOut.setForeground(Color.WHITE);
        } else if (o.equals(btnQLBanHang)) {
            btnQLBanHang.setBackground(Color.decode("#73cdf5"));
            btnQLBanHang.setForeground(Color.WHITE);
        } else if (o.equals(btnQLHeThong)) {
            btnQLHeThong.setBackground(Color.decode("#73cdf5"));
            btnQLHeThong.setForeground(Color.WHITE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLogOut)) {
            btnLogOut.setBackground(Color.decode("#d0e1fd"));
            btnLogOut.setForeground(Color.BLUE);
        } else if (o.equals(btnQLBanHang)) {
            btnQLBanHang.setBackground(Color.decode("#d0e1fd"));
            btnQLBanHang.setForeground(new Color(255, 153, 0));
        } else if (o.equals(btnQLHeThong)) {
            btnQLHeThong.setBackground(Color.decode("#d0e1fd"));
            btnQLHeThong.setForeground(Color.BLUE);
        }
    }

    // mô tả: Bắt sự kiện khi click btn close(x), sẽ show 1 form xác nhận đăng xuất
    // hay thoát chương trình
    public void setCloseAction(JFrame jframe) {
        jframe.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fLogin f = new fLogin();
                jframe.setVisible(false);
                f.setVisible(true);
            }
        });
    }
}
