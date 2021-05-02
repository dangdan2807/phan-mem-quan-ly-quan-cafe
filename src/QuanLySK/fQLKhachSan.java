package QuanLySK;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

public class fQLKhachSan extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenuItem itemThanhToan, itemChuyenPhong, itemThemDV, itemThoat, itemTTKH, itemQLKH;
    private JComboBox<String> cboDanhMucDV, cboDV, cboPhong;
    private JPanel pnMain;
    private JButton btnThemDV, btnChuyenPhong, btnGiamGia, btnThanhToan;
    private SpinnerNumberModel spinSLModel, spinGiamGiaModel;
    private JSpinner spinSoLuong, spinGiamGia;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtThanhToan;

    public fQLKhachSan() {
        setTitle("Phần Mềm Quản Lý Khách Sạn");
        setSize(1000, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        createMenuBar();
        GiaoDienChinh();
        setCloseAction(this);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        //
        JMenu menuQLKH = new JMenu("QL Khách Hàng");
        itemTTKH = new JMenuItem("Thông tin khách hàng");
        itemQLKH = new JMenuItem("Quản lý khách hàng");
        menuQLKH.add(itemTTKH);
        menuQLKH.add(itemQLKH);

        JMenu menuQLDV = new JMenu("QL Dịch Vụ");
        JMenu menuQLTK = new JMenu("QL Tài Khoản");
        JMenu menuAdmin = new JMenu("Admin");

        menuBar.add(menuQLKH);
        menuBar.add(menuQLDV);
        menuBar.add(menuQLTK);
        menuBar.add(menuAdmin);
    }

    public void GiaoDienChinh() {
        pnMain = new JPanel();
        pnMain.setLayout(null);

        JPanel pnLeft = new JPanel();
        pnLeft.setBounds(0, 0, 550, 540);
        FlowLayout flowLeft = new FlowLayout();
        flowLeft.setHgap(10);
        flowLeft.setVgap(10);
        pnLeft.setLayout(flowLeft);
        pnLeft.setBackground(Color.CYAN);

        int wRight = 450;
        JPanel pnRight = new JPanel();
        pnRight.setBounds(550, 0, wRight, 540);
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
        lbSoLuong.setBounds(340, 5, 130, 25);
        spinSoLuong.setBounds(300, 31, 130, 25);

        pnRow1.add(cboDanhMucDV);
        pnRow1.add(cboDV);
        pnRow1.add(btnThemDV);
        pnRow1.add(lbSoLuong);
        pnRow1.add(spinSoLuong);

        JPanel pnRow2 = new JPanel();
        pnRow2.setBounds(0, 60, wRight, 420);
        pnRow2.setLayout(null);

        String[] cols = { "Tên Dịch Vụ", "Giá", "Số Lượng", "Thành tiền" };
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);

        JScrollPane scp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scp.setBounds(0, 0, wRight, 420);
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
        spinGiamGia.setBounds(122, y1Row3, wRow3, hRow3);
        btnGiamGia.setBounds(122, y2Row3, wRow3, hRow3);
        lbThanhTien.setBounds(225, y1Row3, wRow3 + 7, hRow3);
        txtThanhToan.setBounds(225, y2Row3, wRow3 + 7, hRow3);
        btnThanhToan.setBounds(335, y1Row3, wRow3, 55);

        pnRight.add(pnRow1);
        pnRight.add(pnRow2);
        pnRight.add(pnRow3);

        pnMain.add(pnLeft);
        pnMain.add(pnRight);
        this.add(pnMain);
    }

    public static void main(String[] args) {
        new fQLKhachSan().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void setCloseAction(JFrame jframe) {

        jframe.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(jframe, "Bạn muốn đăng xuất ?", "Warning",
                        dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    fLogin f = new fLogin();
                    jframe.setVisible(false);
                    f.setVisible(true);
                }
            }
        });
    }
}
