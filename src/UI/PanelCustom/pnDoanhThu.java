package UI.PanelCustom;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;


public class pnDoanhThu extends JPanel implements ActionListener{
    int widthPn = 770, heightPn = 500;
    private JPanel pnMain;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JButton btnThongKe;
    private JTable tableDT;

    public pnDoanhThu() {
        setLayout(null);
        setSize(760, 440);
        setLayout(new BorderLayout(0, 0));
        
        pnMain = new JPanel();
        pnMain.setBounds(0, 0, widthPn, heightPn);
        pnMain.setLayout(null);
        this.add(pnMain);

        JLabel lbTuNgay = new JLabel("Từ ngày: ");
        JLabel lbDenNgay = new JLabel("Đến ngày: ");

        dpTuNgay = new kDatePicker();
        dpDenNgay = new kDatePicker();
        btnThongKe = new JButton("Thống kê");

        String[] cols = { "" };
        DefaultTableModel modelTableDT = new DefaultTableModel(cols, 0);
        tableDT = new JTable(modelTableDT);

        JScrollPane scpDT = new JScrollPane(tableDT, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel pnTableDT = new JPanel();
        pnTableDT.setLayout(new BoxLayout(pnTableDT, BoxLayout.Y_AXIS));
        pnTableDT.add(scpDT, BorderLayout.CENTER);

        pnMain.add(lbTuNgay);
        pnMain.add(dpTuNgay);
        pnMain.add(lbDenNgay);
        pnMain.add(dpDenNgay);
        pnMain.add(btnThongKe);
        pnMain.add(pnTableDT);

        int h = 20, wRow1 = 2;
        lbTuNgay.setBounds(10, wRow1, 70, h);
        dpTuNgay.setBounds(75, wRow1, 150, h);
        lbDenNgay.setBounds(240, wRow1, 70, h);
        dpDenNgay.setBounds(315, wRow1, 150, h);
        btnThongKe.setBounds(480, wRow1, 100, h);
        pnTableDT.setBounds(10, 25, 740, 404);

        btnThongKe.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}
