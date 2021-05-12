package app;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import DAO.*;
import entity.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class fQLKhachSan extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenuItem itemDangXuat, itemThongTinTK;
    private JComboBox<String> cboDanhMucDV, cboDV, cboPhong;
    private JPanel pnMain, pnShowPhong;
    private JButton btnThemDV, btnChuyenPhong, btnGiamGia, btnThanhToan;
    private SpinnerNumberModel spinSLModel, spinGiamGiaModel;
    private JSpinner spinSoLuong, spinGiamGia;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtThanhToan;
    private JMenu menuAdmin;
    private JMenuItem itemAdmin;
    JButton[] btnPhongList;

    PhongDAO phongDAO = new PhongDAO();
    int heightPhong = 108;

    public fQLKhachSan() {
        setTitle("Phần Mềm Quản Lý Khách Sạn");
        setSize(1040, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        createMenuBar();
        createFromQLKS();
        setCloseAction(this);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu menuTK = new JMenu("Tài khoản");
        itemThongTinTK = new JMenuItem("Thông tin tài khoản");
        itemDangXuat = new JMenuItem("Đăng xuất");
        menuTK.add(itemThongTinTK);
        menuTK.add(itemDangXuat);

        menuAdmin = new JMenu("Admin");
        itemAdmin = new JMenuItem("Quản lý");
        menuAdmin.add(itemAdmin);

        menuBar.add(menuTK);
        menuBar.add(menuAdmin);

        itemAdmin.addActionListener(this);
    }

    public void createFromQLKS() {
        pnMain = new JPanel();
        pnMain.setLayout(null);

        pnShowPhong = new JPanel();
        FlowLayout flowLeft = new FlowLayout(FlowLayout.LEFT);
        flowLeft.setHgap(10);
        flowLeft.setVgap(10);
        pnShowPhong.setLayout(flowLeft);
        pnShowPhong.setPreferredSize(new Dimension(550, heightPhong));
        JScrollPane scpShowPhong = new JScrollPane(pnShowPhong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scpShowPhong.setBounds(0, 0, 555, 540);
        scpShowPhong.setBorder(new TitledBorder(null, "Danh sách phòng"));
        scpShowPhong.getVerticalScrollBar().setUnitIncrement(10);

        int wRight = 460;
        JPanel pnRight = new JPanel();
        pnRight.setBounds(560, 0, wRight, 540);
        pnRight.setLayout(null);

        JPanel pnRow1 = new JPanel();
        pnRow1.setBounds(0, 0, wRight, 60);
        pnRow1.setLayout(null);

        cboDanhMucDV = new JComboBox<String>();
        cboDV = new JComboBox<String>();
        btnThemDV = new JButton("Thêm DV");
        JLabel lbSoLuong = new JLabel("Số lượng: ");
        spinSLModel = new SpinnerNumberModel(1, -100, 100, 1);
        spinSoLuong = new JSpinner(spinSLModel);

        cboDanhMucDV.setBounds(0, 5, 190, 25);
        cboDV.setBounds(0, 31, 190, 25);
        btnThemDV.setBounds(195, 5, 100, 50);
        lbSoLuong.setBounds(340, 5, 110, 25);
        spinSoLuong.setBounds(300, 31, 130, 25);

        pnRow1.add(cboDanhMucDV);
        pnRow1.add(cboDV);
        pnRow1.add(btnThemDV);
        pnRow1.add(lbSoLuong);
        pnRow1.add(spinSoLuong);

        JPanel pnRow2 = new JPanel();
        pnRow2.setBounds(0, 60, wRight, 415);
        pnRow2.setLayout(null);

        String[] cols = { "Tên Dịch Vụ", "Giá", "Số Lượng", "Thành tiền" };
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);

        JScrollPane scp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scp.setBounds(0, 0, wRight, 415);
        pnRow2.add(scp);

        JPanel pnRow3 = new JPanel();
        pnRow3.setBounds(0, 480, wRight, 55);
        pnRow3.setLayout(null);

        btnChuyenPhong = new JButton("Chuyển phòng");
        cboPhong = new JComboBox<String>();
        btnGiamGia = new JButton("Giảm giá");
        spinGiamGiaModel = new SpinnerNumberModel(0, 0, 100, 1);
        spinGiamGia = new JSpinner(spinGiamGiaModel);
        txtThanhToan = new JTextField();
        txtThanhToan.setEditable(false);
        txtThanhToan.setText("0");
        txtThanhToan.setHorizontalAlignment(JTextField.RIGHT);
        btnThanhToan = new JButton("Thanh toán");
        JLabel lbThanhTien = new JLabel("Thành tiền: ");

        pnRow3.add(cboPhong);
        pnRow3.add(btnChuyenPhong);
        pnRow3.add(btnGiamGia);
        pnRow3.add(spinGiamGia);
        pnRow3.add(lbThanhTien);
        pnRow3.add(txtThanhToan);
        pnRow3.add(btnThanhToan);

        int hRow3 = 27, y2Row3 = 28, y1Row3 = 0, wRow3 = 100;
        cboPhong.setBounds(0, y1Row3, 120, hRow3);
        btnChuyenPhong.setBounds(0, y2Row3, 120, hRow3);
        spinGiamGia.setBounds(130, 0, wRow3, hRow3);
        btnGiamGia.setBounds(130, 28, wRow3, hRow3);
        lbThanhTien.setBounds(260, 0, 82, 27);
        txtThanhToan.setBounds(235, 28, 125, 27);
        btnThanhToan.setBounds(360, 0, wRow3, 55);

        pnRight.add(pnRow1);
        pnRight.add(pnRow2);
        pnRight.add(pnRow3);

        pnMain.add(scpShowPhong);
        pnMain.add(pnRight);
        this.add(pnMain);

        itemDangXuat.addActionListener(this);
        itemThongTinTK.addActionListener(this);

        LoadPhong();
    }

    public static void main(String[] args) {
        new fQLKhachSan().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(itemDangXuat)) {
            fLogin f = new fLogin();
            this.setVisible(false);
            f.setVisible(true);
        } else if (o.equals(itemThongTinTK)) {
            fAccountProfile f = new fAccountProfile();
            f.setModal(true);
            f.setVisible(true);
        } else if (o.equals(itemAdmin)) {
            fAdmin f = new fAdmin();
            f.setModal(true);
            f.setVisible(true);
        }
    }

    // mô tả: Bắt sự kiện khi click btn close(x), sẽ show 1 form xác nhận đăng xuất
    // hay thoát chương trình
    public void setCloseAction(JFrame jframe) {
        jframe.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Object[] options = { "Đăng xuất", "Thoát" };
                int select = JOptionPane.showOptionDialog(null, "Bạn muốn đăng xuất hay thoát chương trình ?",
                        "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
                if (select == JOptionPane.OK_OPTION) {
                    // đăng xuất
                    fLogin f = new fLogin();
                    jframe.setVisible(false);
                    f.setVisible(true);
                } else if (select == JOptionPane.NO_OPTION) {
                    // thoát
                    System.exit(0);
                }
            }
        });
    }

    public void LoadPhong() {
        ArrayList<Phong> PhongList = phongDAO.getAllPhong();
        int sizePhongList = PhongList.size();
        btnPhongList = new JButton[sizePhongList];
        for (int i = 0; i < sizePhongList; i++) {
            String tenPhong = PhongList.get(i).getTenPhong();
            String tinhTrang = PhongList.get(i).getTinhTrang();
            String nameBtn = "<html>" + tenPhong + "<br><p style='text-align: center;'>" + tinhTrang + "</p></html>";
            btnPhongList[i] = new JButton(nameBtn);
            btnPhongList[i].setPreferredSize(new Dimension(PhongDAO.phongWidth, PhongDAO.phongHeight));
            switch (tinhTrang) {
                case "Trống":
                    btnPhongList[i].setBackground(Color.CYAN);
                    break;
                default:
                    btnPhongList[i].setBackground(Color.YELLOW);
                    break;
            }
            if ((i + 1) % 5 == 0) {
                heightPhong += 108;
                pnShowPhong.setPreferredSize(new Dimension(550, heightPhong));
            }
            pnShowPhong.add(btnPhongList[i]);
        }
    }
}
