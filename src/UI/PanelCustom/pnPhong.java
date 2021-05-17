package UI.PanelCustom;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

public class pnPhong extends JPanel implements ActionListener {
    int widthPn = 770, heightPn = 500;
    private JPanel pnMain;
    private kPnDataListView pnPhong;
    private JButton btnTimPhong;
    private JTextField txtTimPhong, txtMaPhong, txtTenPhong;
    private SpinnerNumberModel modelSpinNumber;
    private JSpinner spinSucChua, spinSoGiuong;
    private JComboBox<String> cboTinhTrang, cboLoaiPhong;

    public pnPhong() {
        setSize(widthPn, heightPn);
        setLayout(null);

        pnMain = new JPanel();
        pnMain.setBounds(0, 0, widthPn, heightPn);
        pnMain.setLayout(null);
        this.add(pnMain);

        String[] cols = { "Mã phòng", "Tên phòng", "Sức chứa", "Số Giường", "Tình Trạng", "Loại Phòng" };
        pnPhong = new kPnDataListView(cols);
        pnPhong.setBounds(0, 0, pnPhong.getWidth(), pnPhong.getHeight());
        pnMain.add(pnPhong);

        JPanel pnSearchPhong = new JPanel();
        pnSearchPhong.setLayout(null);
        pnSearchPhong.setBounds(467, 0, 282, 36);
        pnMain.add(pnSearchPhong);

        btnTimPhong = new JButton("Tìm");
        btnTimPhong.setBounds(192, 0, 89, 36);
        pnSearchPhong.add(btnTimPhong);

        txtTimPhong = new JTextField();
        txtTimPhong.setBounds(2, 15, 185, 20);
        pnSearchPhong.add(txtTimPhong);
        txtTimPhong.setColumns(10);

        JLabel lbTimPhong = new JLabel("Tên Phòng: ");
        lbTimPhong.setBounds(2, 0, 100, 14);
        pnSearchPhong.add(lbTimPhong);

        JPanel pnInfoPhong = new JPanel();
        pnInfoPhong.setBorder(new TitledBorder(null, "Thông tin phòng"));
        pnInfoPhong.setLayout(null);
        pnInfoPhong.setBounds(467, 37, 282, 407);
        pnMain.add(pnInfoPhong);

        JLabel lbMaPhong = new JLabel("Mã phòng: ");
        lbMaPhong.setBounds(10, 17, 70, 20);
        pnInfoPhong.add(lbMaPhong);

        txtMaPhong = new JTextField();
        txtMaPhong.setBounds(80, 17, 192, 20);
        pnInfoPhong.add(txtMaPhong);
        txtMaPhong.setColumns(10);

        JLabel lbTenPhong = new JLabel("Tên phòng: ");
        lbTenPhong.setBounds(10, 42, 70, 20);
        pnInfoPhong.add(lbTenPhong);

        txtTenPhong = new JTextField();
        txtTenPhong.setBounds(80, 42, 192, 20);
        pnInfoPhong.add(txtTenPhong);
        txtTenPhong.setColumns(10);

        JLabel lbSucChua = new JLabel("Sức chứa:");
        lbSucChua.setBounds(10, 67, 70, 20);
        pnInfoPhong.add(lbSucChua);

        modelSpinNumber = new SpinnerNumberModel(1, 1, 100, 1);
        spinSucChua = new JSpinner(modelSpinNumber);
        spinSucChua.setBounds(80, 67, 93, 20);
        pnInfoPhong.add(spinSucChua);

        JLabel lbSoGiuong = new JLabel("Số giường:");
        lbSoGiuong.setBounds(10, 92, 70, 20);
        pnInfoPhong.add(lbSoGiuong);

        spinSoGiuong = new JSpinner(modelSpinNumber);
        spinSoGiuong.setBounds(80, 92, 93, 20);
        pnInfoPhong.add(spinSoGiuong);

        JLabel lbTinhTrang = new JLabel("Tình trạng:");
        lbTinhTrang.setBounds(10, 117, 70, 20);
        pnInfoPhong.add(lbTinhTrang);

        cboTinhTrang = new JComboBox<String>();
        cboTinhTrang.setBounds(80, 117, 192, 20);
        cboTinhTrang.addItem("Trống");
        cboTinhTrang.addItem("Đã cho thuê");
        pnInfoPhong.add(cboTinhTrang);

        JLabel lbLoaiPhong = new JLabel("Loại phòng:");
        lbLoaiPhong.setBounds(10, 144, 70, 20);
        pnInfoPhong.add(lbLoaiPhong);

        cboLoaiPhong = new JComboBox<String>();
        cboLoaiPhong.setBounds(80, 142, 192, 20);
        pnInfoPhong.add(cboLoaiPhong);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
