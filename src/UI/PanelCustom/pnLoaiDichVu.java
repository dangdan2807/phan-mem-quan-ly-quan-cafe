package UI.PanelCustom;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class pnLoaiDichVu extends JPanel implements ActionListener {
    int widthPn = 770, heightPn = 500;
    private JPanel pnMain;
    private kPnDataListView pnLDichVu;
    private JTextField txtMaLDV, txtTenLDV;

    public pnLoaiDichVu() {
        setLayout(null);
        setSize(widthPn, heightPn);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, widthPn, heightPn);
        this.add(pnMain);

        String[] cols = { "Mã loại dịch vụ", "Tên loại dịch vụ" };
        pnLDichVu = new kPnDataListView(cols);
        pnLDichVu.setBounds(0, 0, pnLDichVu.getWidth(), pnLDichVu.getHeight());
        pnMain.add(pnLDichVu);

        JPanel pnSearchLDV = new JPanel();
        pnSearchLDV.setLayout(null);
        pnSearchLDV.setBounds(467, 0, 282, 36);
        pnMain.add(pnSearchLDV);

        JPanel pnInfoLDV = new JPanel();
        pnInfoLDV.setBorder(new TitledBorder(null, "Thông tin loại dịch vụ"));
        pnInfoLDV.setLayout(null);
        pnInfoLDV.setBounds(467, 37, 282, 406);
        pnMain.add(pnInfoLDV);

        JLabel lbMaLDV = new JLabel("Mã loại DV: ");
        lbMaLDV.setBounds(10, 17, 80, 20);
        pnInfoLDV.add(lbMaLDV);

        txtMaLDV = new JTextField();
        txtMaLDV.setBounds(90, 17, 180, 20);
        pnInfoLDV.add(txtMaLDV);
        txtMaLDV.setColumns(10);

        JLabel lbTenLDV = new JLabel("Tên loại DV: ");
        lbTenLDV.setBounds(10, 48, 80, 20);
        pnInfoLDV.add(lbTenLDV);

        txtTenLDV = new JTextField();
        txtTenLDV.setBounds(92, 48, 180, 20);
        pnInfoLDV.add(txtTenLDV);
        txtTenLDV.setColumns(10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
