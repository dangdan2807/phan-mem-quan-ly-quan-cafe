package UI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import DAO.*;
import entity.*;

public class fQLKhachSan extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menuAdmin;
    private JMenuItem itemDangXuat, itemThongTinTK, itemAdmin;
    private JComboBox<String> cboDanhMucDV, cboDV, cboPhong, cboLoaiPhong, cboTinhTrang;
    private JPanel pnMain, pnShowPhong;
    private JButton btnThemDV, btnChuyenPhong, btnGiamGia, btnThanhToan, btnDatPhong, btnTraPhong;
    private SpinnerNumberModel spinSLModel, spinGiamGiaModel;
    private JSpinner spinSoLuong, spinGiamGia;
    private DefaultTableModel tableModelKH, tableModelDV;
    private JTable tableDV, tableShowKH;
    private JTextField txtThanhToan, txtTenPhong, txtSucChua, txtSoGiuong, txtMaPhong, txtNgayDat, txtNgayTra;
    JButton[] btnPhongList;

    PhongDAO phongDAO = new PhongDAO();
    int heightPhong = 95;

    public fQLKhachSan() {
        setTitle("Phần Mềm Quản Lý Khách Sạn");
        setSize(1200, 600);
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
        scpShowPhong.setBorder(new TitledBorder(null, "Danh sách phòng"));
        scpShowPhong.getVerticalScrollBar().setUnitIncrement(10);

        JPanel pnInfo = new JPanel();
        pnInfo.setBorder(new TitledBorder(null, "Th\u00F4ng tin ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP,
                null, new Color(51, 51, 51)));
        pnInfo.setBounds(560, 0, 622, 540);
        pnInfo.setLayout(null);

        JPanel pnInfoPhong = new JPanel();
        pnInfoPhong.setBounds(5, 15, 605, 168);
        pnInfo.add(pnInfoPhong);
        pnInfoPhong.setLayout(null);

        JLabel lbTenPhong = new JLabel("Tên Phòng:");
        lbTenPhong.setBounds(10, 32, 72, 16);
        pnInfoPhong.add(lbTenPhong);

        txtTenPhong = new JTextField();
        txtTenPhong.setEditable(false);
        txtTenPhong.setBounds(85, 30, 114, 20);
        pnInfoPhong.add(txtTenPhong);
        txtTenPhong.setColumns(10);

        JLabel lbSucChua = new JLabel("Sức chứa:");
        lbSucChua.setBounds(211, 4, 72, 16);
        pnInfoPhong.add(lbSucChua);

        txtSucChua = new JTextField();
        txtSucChua.setEditable(false);
        txtSucChua.setBounds(285, 2, 114, 20);
        pnInfoPhong.add(txtSucChua);
        txtSucChua.setColumns(10);

        JLabel lbLoaiPhong = new JLabel("Loại phòng:");
        lbLoaiPhong.setBounds(10, 60, 72, 16);
        pnInfoPhong.add(lbLoaiPhong);

        JLabel lbSoGiuong = new JLabel("Số giường: ");
        lbSoGiuong.setBounds(211, 32, 72, 16);
        pnInfoPhong.add(lbSoGiuong);

        txtSoGiuong = new JTextField();
        txtSoGiuong.setEditable(false);
        txtSoGiuong.setBounds(285, 26, 114, 20);
        pnInfoPhong.add(txtSoGiuong);
        txtSoGiuong.setColumns(10);

        JLabel lbMaPhong = new JLabel("Mã phòng:");
        lbMaPhong.setBounds(10, 2, 72, 16);
        pnInfoPhong.add(lbMaPhong);

        txtMaPhong = new JTextField();
        txtMaPhong.setEditable(false);
        txtMaPhong.setBounds(85, 0, 114, 20);
        pnInfoPhong.add(txtMaPhong);
        txtMaPhong.setColumns(10);

        lbLoaiPhong = new JLabel("Tình trạng:");
        lbLoaiPhong.setBounds(211, 62, 72, 16);
        pnInfoPhong.add(lbLoaiPhong);

        cboLoaiPhong = new JComboBox<String>();
        cboLoaiPhong.setBounds(85, 58, 114, 20);
        pnInfoPhong.add(cboLoaiPhong);

        cboTinhTrang = new JComboBox<String>();
        cboTinhTrang.setBounds(285, 56, 114, 20);
        pnInfoPhong.add(cboTinhTrang);

        JPanel pnShowKH = new JPanel();
        pnShowKH.setBounds(0, 88, 446, 75);
        pnInfoPhong.add(pnShowKH);
        pnShowKH.setLayout(new BorderLayout(0, 0));

        JScrollPane scpShowKH = new JScrollPane();
        pnShowKH.add(scpShowKH, BorderLayout.CENTER);

        String[] colsKH = { "Tên KH", "Loại khách" };
        tableModelKH = new DefaultTableModel(colsKH, 0);
        tableShowKH = new JTable(tableModelKH);
        scpShowKH.setViewportView(tableShowKH);

        btnDatPhong = new JButton("Đặt phòng");
        btnDatPhong.setBounds(458, 89, 147, 37);
        pnInfoPhong.add(btnDatPhong);

        btnTraPhong = new JButton("Trả phòng");
        btnTraPhong.setBounds(458, 126, 147, 37);
        pnInfoPhong.add(btnTraPhong);

        JLabel lbNgayDat = new JLabel("Ngày đặt:");
        lbNgayDat.setBounds(417, 4, 64, 16);
        pnInfoPhong.add(lbNgayDat);

        txtNgayDat = new JTextField();
        txtNgayDat.setEditable(false);
        txtNgayDat.setBounds(479, 2, 126, 20);
        pnInfoPhong.add(txtNgayDat);
        txtNgayDat.setColumns(10);

        txtNgayTra = new JTextField();
        txtNgayTra.setEditable(false);
        txtNgayTra.setBounds(479, 30, 126, 20);
        pnInfoPhong.add(txtNgayTra);
        txtNgayTra.setColumns(10);

        JLabel lbNgayTra = new JLabel("Ngày trả:");
        lbNgayTra.setBounds(417, 32, 64, 16);
        pnInfoPhong.add(lbNgayTra);

        JPanel pnAddDV = new JPanel();
        pnAddDV.setBounds(5, 184, 605, 60);
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
        pnTableDV.setBounds(5, 245, 605, 230);

        String[] colsDV = { "Tên Dịch Vụ", "Giá", "Số Lượng", "Thành tiền" };
        tableModelDV = new DefaultTableModel(colsDV, 0);
        pnTableDV.setLayout(new BorderLayout(0, 0));
        tableDV = new JTable(tableModelDV);

        JScrollPane scpTableDV = new JScrollPane(tableDV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnTableDV.add(scpTableDV, BorderLayout.CENTER);

        JPanel pnThanhToan = new JPanel();
        pnThanhToan.setBounds(5, 480, 605, 55);
        pnThanhToan.setLayout(null);

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
        lbThanhTien.setBounds(244, 0, 216, 27);
        txtThanhToan.setBounds(245, 31, 166, 23);
        btnThanhToan.setBounds(457, 0, 148, 55);

        pnInfo.add(pnAddDV);
        pnInfo.add(pnTableDV);
        pnInfo.add(pnThanhToan);

        JLabel lblVND = new JLabel("VND");
        lblVND.setBounds(421, 33, 46, 16);
        pnThanhToan.add(lblVND);

        pnMain.add(scpShowPhong);
        pnMain.add(pnInfo);
        getContentPane().add(pnMain);

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
            Phong item = PhongList.get(i);
            String tenPhong = item.getTenPhong();
            String tinhTrang = item.getTinhTrang();
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
                heightPhong += 95;
                pnShowPhong.setPreferredSize(new Dimension(550, heightPhong));
            }
            btnPhongList[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int maPhong = item.getMaPhong();
                    showHoaDon(maPhong);
                }
            });
            pnShowPhong.add(btnPhongList[i]);
        }
    }

    private void showHoaDon(int maPhong) {
        int maHD = HoaDonDAO.getInstance().getUncheckHoaDonByMaPhong(maPhong);
        ArrayList<ChiTietHoaDon> dataCTHoaDon = ChiTietHoaDonDAO.getInstance().getListChiTietHoaDonByMaCTHoaDon(maHD);
        for (ChiTietHoaDon item : dataCTHoaDon) {
            tableModelDV.addRow(new Object[] { item.getMaDV(), item.getSoLuong(), "", "" });
        }
    }
}
