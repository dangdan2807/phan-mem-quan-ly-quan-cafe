package UI.PanelCustom;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class pnDichVu extends JPanel implements ActionListener{
    int widthPn = 770, heightPn = 500;
    private JPanel pnMain;
    private kPnDataListView pnDichVu;
    private JButton btnTimDV;
    private JTextField txtTimDV, txtMaDV, txtTenDV, txtDonGiaDV;
    private JComboBox<String> cboLoaiDV;

    public pnDichVu() {
        setLayout(null);
        setSize(widthPn, heightPn);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, widthPn, heightPn);
        this.add(pnMain);
        
        String[] cols = { "Mã dịch vụ", "Tên dịch vụ", "Mã loại dịch vụ", "Đơn giá" };
        pnDichVu = new kPnDataListView(cols);
        pnDichVu.setBounds(0, 0, pnDichVu.getWidth(), pnDichVu.getHeight());
        pnMain.add(pnDichVu);

        JPanel pnSearchDV = new JPanel();
        pnSearchDV.setLayout(null);
        pnSearchDV.setBounds(467, 0, 282, 36);
        pnMain.add(pnSearchDV);

        btnTimDV = new JButton("Tìm");
        btnTimDV.setBounds(192, 0, 89, 36);
        pnSearchDV.add(btnTimDV);

        txtTimDV = new JTextField();
        txtTimDV.setBounds(2, 14, 185, 20);
        pnSearchDV.add(txtTimDV);
        txtTimDV.setColumns(10);

        JLabel lbTimDV = new JLabel("Tên dịch vụ: ");
        lbTimDV.setBounds(2, 0, 100, 14);
        pnSearchDV.add(lbTimDV);

        JPanel pnInfoDV = new JPanel();
        pnInfoDV.setBorder(new TitledBorder(null, "Thông tin dịch vụ"));
        pnInfoDV.setLayout(null);
        pnInfoDV.setBounds(467, 37, 282, 395);
        pnMain.add(pnInfoDV);

        JLabel lbMaDV = new JLabel("Mã DV: ");
        lbMaDV.setBounds(10, 17, 70, 20);
        pnInfoDV.add(lbMaDV);

        txtMaDV = new JTextField();
        txtMaDV.setBounds(80, 17, 192, 20);
        pnInfoDV.add(txtMaDV);
        txtMaDV.setColumns(10);

        JLabel lbTenDV = new JLabel("Tên DV: ");
        lbTenDV.setBounds(10, 48, 70, 20);
        pnInfoDV.add(lbTenDV);

        txtTenDV = new JTextField();
        txtTenDV.setBounds(80, 48, 192, 20);
        pnInfoDV.add(txtTenDV);
        txtTenDV.setColumns(10);

        JLabel lbSucChua = new JLabel("Đơn giá:");
        lbSucChua.setBounds(10, 110, 70, 20);
        pnInfoDV.add(lbSucChua);

        JLabel lbLoaiDV = new JLabel("Loại DV: ");
        lbLoaiDV.setBounds(10, 79, 70, 20);
        pnInfoDV.add(lbLoaiDV);

        cboLoaiDV = new JComboBox<String>();
        cboLoaiDV.setBounds(80, 79, 192, 20);
        pnInfoDV.add(cboLoaiDV);

        txtDonGiaDV = new JTextField();
        txtDonGiaDV.setBounds(80, 110, 192, 20);
        pnInfoDV.add(txtDonGiaDV);
        txtDonGiaDV.setColumns(10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
