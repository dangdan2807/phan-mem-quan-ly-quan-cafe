package UI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import DAO.*;
import entity.*;

public class fQLQuanCafe extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menuAdmin;
    private JMenuItem itemDangXuat, itemThongTinTK, itemAdmin;
    private JComboBox<String> cboDanhMucDV, cboDV, cboPhong;
    private JPanel pnMain, pnShowPhong;
    private JButton btnThemDV, btnChuyenPhong, btnGiamGia, btnThanhToan;
    private SpinnerNumberModel spinSLModel, spinGiamGiaModel;
    private JSpinner spinSoLuong, spinGiamGia;
    private DefaultTableModel tableModelDV;
    private JTable tableDV;
    private JTextField txtThanhToan;
    JButton[] btnPhongList;

    PhongDAO phongDAO = new PhongDAO();
    int heightPhong = 95;

    public fQLQuanCafe() {
        setTitle("Phần Mềm Quản Lý Khách Sạn");
        setSize(1095, 600);
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
        itemDangXuat.addActionListener(this);
        itemThongTinTK.addActionListener(this);
    }

    public void createFromQLKS() {
        pnMain = new JPanel();
        pnMain.setBorder(new TitledBorder(null, "T", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
        scpShowPhong.setBorder(new TitledBorder(null, "Danh sách bàn"));
        scpShowPhong.getVerticalScrollBar().setUnitIncrement(10);

        JPanel pnInfo = new JPanel();
        pnInfo.setBorder(new TitledBorder(null, "Thông tin bàn", TitledBorder.LEADING, TitledBorder.TOP,
                null, new Color(51, 51, 51)));
        pnInfo.setBounds(560, 0, 531, 540);
        pnInfo.setLayout(null);

        JPanel pnAddDV = new JPanel();
        pnAddDV.setBounds(5, 21, 510, 60);
        pnAddDV.setLayout(null);

        cboDanhMucDV = new JComboBox<String>();
        cboDV = new JComboBox<String>();
        btnThemDV = new JButton("Thêm DV");
        JLabel lbSoLuong = new JLabel("Số lượng: ");
        lbSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
        spinSLModel = new SpinnerNumberModel(1, -100, 100, 1);
        spinSoLuong = new JSpinner(spinSLModel);

        cboDanhMucDV.setBounds(0, 5, 190, 20);
        cboDV.setBounds(0, 31, 190, 20);
        btnThemDV.setBounds(195, 5, 137, 44);
        lbSoLuong.setBounds(334, 5, 114, 20);
        spinSoLuong.setBounds(334, 29, 114, 22);

        pnAddDV.add(cboDanhMucDV);
        pnAddDV.add(cboDV);
        pnAddDV.add(btnThemDV);
        pnAddDV.add(lbSoLuong);
        pnAddDV.add(spinSoLuong);

        JPanel pnTableDV = new JPanel();
        pnTableDV.setBounds(5, 87, 510, 388);

        String[] colsDV = { "Tên Dịch Vụ", "Giá", "Số Lượng", "Thành tiền" };
        tableModelDV = new DefaultTableModel(colsDV, 0);
        pnTableDV.setLayout(new BorderLayout(0, 0));
        tableDV = new JTable(tableModelDV);

        JScrollPane scpTableDV = new JScrollPane(tableDV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnTableDV.add(scpTableDV, BorderLayout.CENTER);

        JPanel pnThanhToan = new JPanel();
        pnThanhToan.setBounds(5, 480, 510, 55);
        pnThanhToan.setLayout(null);

        btnChuyenPhong = new JButton("Chuyển bàn");
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
        lbThanhTien.setHorizontalAlignment(SwingConstants.CENTER);

        pnThanhToan.add(cboPhong);
        pnThanhToan.add(btnChuyenPhong);
        pnThanhToan.add(btnGiamGia);
        pnThanhToan.add(spinGiamGia);
        pnThanhToan.add(lbThanhTien);
        pnThanhToan.add(txtThanhToan);
        pnThanhToan.add(btnThanhToan);

        int hRow3 = 27, y2Row3 = 28, y1Row3 = 0, wRow3 = 100;
        cboPhong.setBounds(0, y1Row3, 120, hRow3);
        btnChuyenPhong.setBounds(0, y2Row3, 120, hRow3);
        spinGiamGia.setBounds(132, 0, wRow3, hRow3);
        btnGiamGia.setBounds(132, 28, wRow3, hRow3);
        lbThanhTien.setBounds(242, 0, 153, 27);
        txtThanhToan.setBounds(245, 31, 104, 23);
        btnThanhToan.setBounds(405, 0, 105, 55);

        pnInfo.add(pnAddDV);
        pnInfo.add(pnTableDV);
        pnInfo.add(pnThanhToan);

        JLabel lblVND = new JLabel("VND");
        lblVND.setBounds(359, 33, 46, 16);
        pnThanhToan.add(lblVND);

        pnMain.add(scpShowPhong);
        pnMain.add(pnInfo);
        getContentPane().add(pnMain);

        // LoadPhong();
    }

    public static void main(String[] args) {
        new fQLQuanCafe().setVisible(true);
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

    // public void LoadPhong() {
    //     ArrayList<Phong> PhongList = phongDAO.getAllPhong();
    //     int sizePhongList = PhongList.size();
    //     btnPhongList = new JButton[sizePhongList];
    //     for (int i = 0; i < sizePhongList; i++) {
    //         Phong item = PhongList.get(i);
    //         String tenPhong = item.getTenPhong();
    //         String tinhTrang = item.getTinhTrang();
    //         String nameBtn = "<html>" + tenPhong + "<br><p style='text-align: center;'>" + tinhTrang + "</p></html>";
    //         btnPhongList[i] = new JButton(nameBtn);
    //         btnPhongList[i].setPreferredSize(new Dimension(PhongDAO.phongWidth, PhongDAO.phongHeight));
    //         switch (tinhTrang) {
    //             case "Trống":
    //                 btnPhongList[i].setBackground(Color.CYAN);
    //                 break;
    //             default:
    //                 btnPhongList[i].setBackground(Color.YELLOW);
    //                 break;
    //         }
    //         if ((i + 1) % 5 == 0) {
    //             heightPhong += 95;
    //             pnShowPhong.setPreferredSize(new Dimension(550, heightPhong));
    //         }
    //         btnPhongList[i].addActionListener(new ActionListener() {
    //             @Override
    //             public void actionPerformed(ActionEvent e) {
    //                 int maPhong = item.getMaPhong();
    //                 showHoaDon(maPhong);
    //             }
    //         });
    //         pnShowPhong.add(btnPhongList[i]);
    //     }
    // }

    // private void showHoaDon(int maPhong) {
    //     int maHD = HoaDonDAO.getInstance().getUncheckHoaDonByMaPhong(maPhong);
    //     ArrayList<ChiTietHoaDon> dataCTHoaDon = ChiTietHoaDonDAO.getInstance().getListChiTietHoaDonByMaCTHoaDon(maHD);
    //     for (ChiTietHoaDon item : dataCTHoaDon) {
    //         tableModelDV.addRow(new Object[] { item.getMaDV(), item.getSoLuong(), "", "" });
    //     }
    // }
}
