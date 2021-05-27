package UI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import DAO.*;
import entity.*;

public class fManage extends JFrame implements ActionListener {
    JButton[] btnTableList;
    int pnShowTableWidth = 350;
    int heightPhong = 95;

    private JPanel pnMain, pnShowTable;
    private DefaultTableModel modelTableBill, modelTableProduct;
    private JTable tableBill, tableProduct;
    private JLabel lbShowTime;
    private JButton btnChuyenBan, btnLamMoi, btnThoat, btnSearch, btnTamTinh, btnThanhToan;
    private JTextField txtMaHD, txtMaBan, txtThanhToan, txtSearchProduct;
    private JComboBox<String> cboLoaiSp;
    ImageIcon transferIcon = new ImageIcon("img/transfer_16.png");
    ImageIcon logOutIcon = new ImageIcon("img/logout_16.png");
    ImageIcon refreshIcon = new ImageIcon("img/refresh_16.png");
    ImageIcon paymentIcon = new ImageIcon("img/payment_16.png");
    ImageIcon coffeeActionIcon = new ImageIcon("img/coffee_64_action.png");
    ImageIcon coffeeDisableIcon = new ImageIcon("img/coffee_64_disable.png");

    TableDAO tableFoodDAO = new TableDAO();

    public fManage() {
        setTitle("Phần Mềm Quản Lý Quán Cafe");
        setSize(1280, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        // setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createMenuBar();
        createFromQLKS();
        // setCloseAction(this);
    }

    public void createMenuBar() {
    }

    public void createFromQLKS() {
        pnMain = new JPanel();
        pnMain.setBackground(Color.WHITE);
        pnMain.setLayout(null);

        JPanel pnTitle = new JPanel();
        pnTitle.setBounds(0, 0, 1264, 39);
        pnMain.add(pnTitle);
        pnTitle.setBackground(Color.decode("#d0e1fd"));

        JLabel lbTitle = new JLabel("Quản Lý Bán Hàng");
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lbTitle.setForeground(Color.decode("#1a66e3"));
        pnTitle.add(lbTitle);

        JPanel pnNameEmp = new JPanel();
        pnNameEmp.setBackground(Color.WHITE);
        pnNameEmp.setBorder(new TitledBorder(null, "Nhân Viên: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnNameEmp.setBounds(0, 39, 330, 40);
        pnMain.add(pnNameEmp);
        pnNameEmp.setLayout(null);

        JLabel lbTenNV = new JLabel("name");
        lbTenNV.setBounds(12, 12, 306, 21);
        pnNameEmp.add(lbTenNV);
        lbTenNV.setFont(new Font("Dialog", Font.BOLD, 18));

        JPanel pnTableList = new JPanel();
        pnTableList.setBackground(Color.WHITE);
        pnTableList.setBorder(new TitledBorder(null, "Bàn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnTableList.setBounds(0, 80, 330, 581);
        pnMain.add(pnTableList);
        pnTableList.setLayout(new BorderLayout(0, 0));

        JPanel pnControlTable = new JPanel();
        pnControlTable.setBackground(Color.WHITE);
        pnTableList.add(pnControlTable, BorderLayout.NORTH);
        pnControlTable.setLayout(null);
        pnControlTable.setPreferredSize(new Dimension(330, 60));

        btnChuyenBan = new JButton("Chuyển bàn", transferIcon);
        btnChuyenBan.setBounds(12, 33, 296, 27);
        btnChuyenBan.setBackground(Color.decode("#d0e1fd"));
        btnChuyenBan.setForeground(Color.decode("#1a66e3"));
        pnControlTable.add(btnChuyenBan);

        btnLamMoi = new JButton("Làm mới", refreshIcon);
        btnLamMoi.setBounds(12, 0, 142, 27);
        btnLamMoi.setBackground(Color.decode("#d0e1fd"));
        btnLamMoi.setForeground(Color.decode("#1a66e3"));
        pnControlTable.add(btnLamMoi);

        btnThoat = new JButton("Thoát", logOutIcon);
        btnThoat.setBounds(166, 0, 142, 26);
        btnThoat.setBackground(Color.decode("#d0e1fd"));
        btnThoat.setForeground(Color.decode("#1a66e3"));
        pnControlTable.add(btnThoat);

        pnShowTable = new JPanel();
        pnShowTable.setBackground(Color.WHITE);
        FlowLayout fl_pnShowTable = new FlowLayout(FlowLayout.LEFT);
        fl_pnShowTable.setHgap(10);
        fl_pnShowTable.setVgap(10);
        pnShowTable.setLayout(fl_pnShowTable);
        pnShowTable.setPreferredSize(new Dimension(320, heightPhong));

        JScrollPane scpShowTable = new JScrollPane(pnShowTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnTableList.add(scpShowTable, BorderLayout.CENTER);
        scpShowTable.setBorder(new TitledBorder(null, "Danh sách bàn"));
        scpShowTable.setBackground(Color.WHITE);
        scpShowTable.getVerticalScrollBar().setUnitIncrement(10);

        JPanel pnBill = new JPanel();
        pnBill.setBackground(Color.WHITE);
        pnBill.setBounds(330, 40, 488, 622);

        JPanel pnBillInfo = new JPanel();
        pnBillInfo.setBackground(Color.WHITE);
        pnBillInfo.setBorder(
                new TitledBorder(null, "Thông tin hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnBillInfo.setLayout(null);
        pnBillInfo.setPreferredSize(new Dimension(488, 120));

        JPanel pnBillList = new JPanel();
        pnBillList.setBackground(Color.WHITE);
        pnBillList.setBorder(
                new TitledBorder(null, "Danh sách order", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        String[] colsDV = { "STT", "Tên sản phẩm", "Đơn giá", "Số Lượng", "Thành tiền" };
        modelTableBill = new DefaultTableModel(colsDV, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        pnBillList.setLayout(new BorderLayout(0, 0));
        tableBill = new JTable(modelTableBill);
        tableBill.setBackground(Color.WHITE);

        JScrollPane scpTableBill = new JScrollPane(tableBill, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scpTableBill.setBackground(Color.WHITE);
        scpTableBill.getViewport().setBackground(Color.WHITE);
        pnBillList.add(scpTableBill, BorderLayout.CENTER);
        pnBill.setLayout(new BorderLayout(0, 0));

        pnBill.add(pnBillInfo, BorderLayout.NORTH);

        JLabel lbMaHD = new JLabel("Mã hóa đơn: ");
        lbMaHD.setBounds(12, 15, 85, 20);
        pnBillInfo.add(lbMaHD);

        txtMaHD = new JTextField();
        txtMaHD.setEditable(false);
        txtMaHD.setBounds(90, 15, 148, 20);
        pnBillInfo.add(txtMaHD);
        txtMaHD.setColumns(10);

        JLabel lblMaBan = new JLabel("Bàn số: ");
        lblMaBan.setBounds(256, 16, 57, 20);
        pnBillInfo.add(lblMaBan);

        txtMaBan = new JTextField();
        txtMaBan.setEditable(false);
        txtMaBan.setBounds(331, 16, 130, 20);
        pnBillInfo.add(txtMaBan);
        txtMaBan.setColumns(10);

        JLabel lbTime = new JLabel("Thời gian: ");
        lbTime.setBounds(12, 53, 85, 16);
        pnBillInfo.add(lbTime);

        lbShowTime = new JLabel("dd/MM/yyyy HH:mm:ss");
        lbShowTime.setBounds(90, 53, 223, 16);
        pnBillInfo.add(lbShowTime);

        btnTamTinh = new JButton("Tạm tính");
        btnTamTinh.setBounds(331, 48, 130, 26);
        btnTamTinh.setBackground(Color.decode("#d0e1fd"));
        btnTamTinh.setForeground(Color.decode("#1a66e3"));
        pnBillInfo.add(btnTamTinh);

        btnThanhToan = new JButton("Thanh toán", paymentIcon);
        btnThanhToan.setBounds(331, 86, 130, 26);
        btnThanhToan.setBackground(Color.decode("#d0e1fd"));
        btnThanhToan.setForeground(Color.decode("#1a66e3"));
        pnBillInfo.add(btnThanhToan);

        JLabel lblTongTien = new JLabel("Tổng tiền: ");
        lblTongTien.setBounds(12, 89, 85, 20);
        pnBillInfo.add(lblTongTien);

        txtThanhToan = new JTextField();
        txtThanhToan.setHorizontalAlignment(SwingConstants.RIGHT);
        txtThanhToan.setText("0.0");
        txtThanhToan.setEditable(false);
        txtThanhToan.setBounds(90, 89, 148, 20);
        pnBillInfo.add(txtThanhToan);
        txtThanhToan.setColumns(10);

        JLabel lbVND = new JLabel("(VND)");
        lbVND.setBounds(245, 89, 57, 20);
        pnBillInfo.add(lbVND);
        pnBill.add(pnBillList);
        pnMain.add(pnBill);
        getContentPane().add(pnMain);

        JPanel pnProduct = new JPanel();
        pnProduct.setBackground(Color.WHITE);
        pnProduct.setBorder(
                new TitledBorder(null, "Thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnProduct.setBounds(819, 40, 445, 622);
        pnMain.add(pnProduct);
        pnProduct.setLayout(new BorderLayout(0, 0));

        JPanel pnControlProduct = new JPanel();
        pnControlProduct.setPreferredSize(new Dimension(445, 70));
        pnControlProduct.setBackground(Color.WHITE);
        pnProduct.add(pnControlProduct, BorderLayout.NORTH);
        pnControlProduct.setLayout(null);

        JLabel lbNameProduct = new JLabel("Tên sản phẩm: ");
        lbNameProduct.setBounds(12, 12, 90, 16);
        pnControlProduct.add(lbNameProduct);

        txtSearchProduct = new JTextField();
        txtSearchProduct.setBounds(110, 10, 170, 20);
        pnControlProduct.add(txtSearchProduct);
        txtSearchProduct.setColumns(10);

        btnSearch = new JButton("Tìm");
        btnSearch.setBounds(292, 7, 131, 26);
        btnSearch.setBackground(Color.decode("#d0e1fd"));
        btnSearch.setForeground(Color.decode("#1a66e3"));
        pnControlProduct.add(btnSearch);

        JLabel lnLoaiSp = new JLabel("Loại sản phẩm: ");
        lnLoaiSp.setBounds(12, 40, 100, 16);
        pnControlProduct.add(lnLoaiSp);

        cboLoaiSp = new JComboBox<String>();
        cboLoaiSp.setBounds(110, 38, 170, 20);
        pnControlProduct.add(cboLoaiSp);

        JPanel pnProductList = new JPanel();
        pnProductList.setBackground(Color.WHITE);
        pnProductList.setBorder(
                new TitledBorder(null, "Danh sách sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnProduct.add(pnProductList, BorderLayout.CENTER);

        String[] colsProduct = { "STT", "Tên sản phẩm", "Đơn giá" };
        modelTableProduct = new DefaultTableModel(colsProduct, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        pnProductList.setLayout(new BorderLayout(0, 0));
        tableProduct = new JTable(modelTableProduct);

        JScrollPane scpProductList = new JScrollPane(tableProduct, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scpProductList.getViewport().setBackground(Color.WHITE);
        pnProductList.add(scpProductList);

        LoadTable();
    }

    public static void main(String[] args) {
        new fManage().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThoat)) {
            fLogin f = new fLogin();
            this.setVisible(false);
            f.setVisible(true);
        }
        // } else if (o.equals(itemThongTinTK)) {
        // fAccountProfile f = new fAccountProfile();
        // f.setModal(true);
        // f.setVisible(true);
        // } else if (o.equals(itemAdmin)) {
        // fAdmin f = new fAdmin();
        // f.setModal(true);
        // f.setVisible(true);
        // }
    }

    // mô tả: Bắt sự kiện khi click btn close(x), sẽ show 1 form xác nhận đăng xuất
    // hay thoát chương trình
    // public void setCloseAction(JFrame jframe) {
    // jframe.addWindowListener(new java.awt.event.WindowAdapter() {
    // @Override
    // public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    // Object[] options = { "Đăng xuất", "Thoát" };
    // int select = JOptionPane.showOptionDialog(null, "Bạn muốn đăng xuất hay thoát
    // chương trình ?",
    // "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
    // options,
    // options[0]);
    // if (select == JOptionPane.OK_OPTION) {
    // // đăng xuất
    // fLogin f = new fLogin();
    // jframe.setVisible(false);
    // f.setVisible(true);
    // } else if (select == JOptionPane.NO_OPTION) {
    // // thoát
    // System.exit(0);
    // }
    // }
    // });
    // }

    public void LoadTable() {
        ArrayList<Table> TableList = tableFoodDAO.getTableList();
        int sizeTableList = TableList.size();
        btnTableList = new JButton[sizeTableList];
        for (int i = 0; i < sizeTableList; i++) {
            Table item = TableList.get(i);
            String name = item.getName();
            String status = item.getStatus();
            // String nameBtn = "<html>" + name + "<br><p style='text-align: center;'>" +
            // status + "</p></html>";
            btnTableList[i] = new JButton(name);
            btnTableList[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            btnTableList[i].setHorizontalTextPosition(SwingConstants.CENTER);
            btnTableList[i].setPreferredSize(new Dimension(TableDAO.TABLE_WIDTH, TableDAO.TABLE_HEIGHT));
            switch (status) {
                case "Trống":
                    btnTableList[i].setBackground(Color.CYAN);
                    btnTableList[i].setIcon(coffeeActionIcon);
                    break;
                default:
                    btnTableList[i].setBackground(Color.decode("#E0FFFF"));
                    btnTableList[i].setIcon(coffeeDisableIcon);
                    break;
            }
            if ((i + 1) % 3 == 0) {
                heightPhong += 90;
                pnShowTable.setPreferredSize(new Dimension(pnShowTableWidth, heightPhong));
            }
            btnTableList[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // int maPhong = item.getId();
                    // showHoaDon(maPhong);
                }
            });
            pnShowTable.add(btnTableList[i]);
        }
    }
}
