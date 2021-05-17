package UI.PanelCustom;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class pnKhachHang extends JPanel implements ActionListener {
    int widthPn = 770, heightPn = 500;
    private JPanel pnMain;
    private kPnDataListView pnKhachHang;
    private JButton btnTimKH;
    private JTextField txtTimKH, txtMaKH, txtTenKH, txtCccd;
    private JComboBox<String> cboLoaiKH;

    public pnKhachHang() {
        setLayout(null);
        setSize(widthPn, heightPn);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, widthPn, heightPn);
        this.add(pnMain);

        String[] cols = { "Mã khách hàng", "Tên khách hàng", "CCCD", "Loại Khách" };
        pnKhachHang = new kPnDataListView(cols);
        pnKhachHang.setBounds(0, 0, pnKhachHang.getWidth(), pnKhachHang.getHeight());
        pnMain.add(pnKhachHang);

        JPanel pnSearchKH = new JPanel();
        pnSearchKH.setLayout(null);
        pnSearchKH.setBounds(467, 0, 282, 36);
        pnMain.add(pnSearchKH);

        btnTimKH = new JButton("Tìm");
        btnTimKH.setBounds(192, 0, 89, 36);
        pnSearchKH.add(btnTimKH);

        txtTimKH = new JTextField();
        txtTimKH.setBounds(2, 15, 185, 20);
        pnSearchKH.add(txtTimKH);
        txtTimKH.setColumns(10);

        JLabel lbTimKH = new JLabel("Tên khách hàng: ");
        lbTimKH.setBounds(2, 0, 100, 14);
        pnSearchKH.add(lbTimKH);

        JPanel pnInfoKH = new JPanel();
        pnInfoKH.setBorder(new TitledBorder(null, "Thông tin khách hàng"));
        pnInfoKH.setLayout(null);
        pnInfoKH.setBounds(467, 37, 282, 393);
        pnMain.add(pnInfoKH);

        JLabel lbMaKH = new JLabel("Mã khách hàng: ");
        lbMaKH.setBounds(10, 17, 95, 20);
        pnInfoKH.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setBounds(105, 17, 167, 20);
        pnInfoKH.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTenKH = new JLabel("Tên KH: ");
        lbTenKH.setBounds(10, 48, 92, 20);
        pnInfoKH.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(105, 48, 167, 20);
        pnInfoKH.add(txtTenKH);
        txtTenKH.setColumns(10);

        JLabel lbCccd = new JLabel("CCCD:");
        lbCccd.setBounds(12, 80, 92, 16);
        pnInfoKH.add(lbCccd);

        txtCccd = new JTextField();
        txtCccd.setBounds(105, 78, 167, 20);
        pnInfoKH.add(txtCccd);
        txtCccd.setColumns(10);

        JLabel lbEmail = new JLabel("Loại KH:");
        lbEmail.setBounds(10, 108, 92, 16);
        pnInfoKH.add(lbEmail);

        cboLoaiKH = new JComboBox<String>();
        cboLoaiKH.setBounds(105, 106, 167, 20);
        cboLoaiKH.addItem("Việt Nam");
        cboLoaiKH.addItem("Nước ngoài");
        pnInfoKH.add(cboLoaiKH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
