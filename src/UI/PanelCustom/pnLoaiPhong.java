package UI.PanelCustom;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class pnLoaiPhong extends JPanel implements ActionListener {
    int widthPn = 770, heightPn = 500;
    private JPanel pnMain;
    private kPnDataListView pnLPhong;
    private JTextField txtMaLP, txtTenLP, txtDonGiaLP;

    public pnLoaiPhong() {
        setLayout(null);
        setSize(widthPn, heightPn);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, widthPn, heightPn);
        this.add(pnMain);

        String[] cols = { "Mã loại phòng", "Tên loại phòng", "Đơn giá" };
        pnLPhong = new kPnDataListView(cols);
        pnLPhong.setBounds(0, 0, pnLPhong.getWidth(), pnLPhong.getHeight());
        pnMain.add(pnLPhong);

        JPanel pnSearchLP = new JPanel();
        pnSearchLP.setLayout(null);
        pnSearchLP.setBounds(467, 0, 282, 36);
        pnMain.add(pnSearchLP);

        JPanel pnInfoLP = new JPanel();
        pnInfoLP.setBorder(new TitledBorder(null, "Thông tin loại phòng"));
        pnInfoLP.setLayout(null);
        pnInfoLP.setBounds(467, 37, 282, 407);
        pnMain.add(pnInfoLP);

        JLabel lbMaLP = new JLabel("Mã L.Phòng: ");
        lbMaLP.setBounds(10, 17, 85, 20);
        pnInfoLP.add(lbMaLP);

        txtMaLP = new JTextField();
        txtMaLP.setBounds(90, 17, 182, 20);
        pnInfoLP.add(txtMaLP);
        txtMaLP.setColumns(10);

        JLabel lbTenLP = new JLabel("Tên L.Phòng: ");
        lbTenLP.setBounds(10, 48, 85, 20);
        pnInfoLP.add(lbTenLP);

        txtTenLP = new JTextField();
        txtTenLP.setBounds(90, 48, 182, 20);
        pnInfoLP.add(txtTenLP);
        txtTenLP.setColumns(10);

        JLabel lbDonGiaLP = new JLabel("Đơn giá:");
        lbDonGiaLP.setBounds(10, 79, 85, 20);
        pnInfoLP.add(lbDonGiaLP);

        txtDonGiaLP = new JTextField();
        txtDonGiaLP.setBounds(90, 79, 182, 20);
        pnInfoLP.add(txtDonGiaLP);
        txtDonGiaLP.setColumns(10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
