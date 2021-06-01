package UI.PanelCustom;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class pnRevenue extends JPanel implements ActionListener {
    private JPanel pnBtn;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JButton btnThongKe;
    private JTable table;

    public pnRevenue() {
        setLayout(null);
        setSize(1270, 630);
        setLayout(new BorderLayout(0, 0));

        pnBtn = new JPanel();
        pnBtn.setBackground(Color.WHITE);
        pnBtn.setPreferredSize(new Dimension(10, 80));
        pnBtn.setLayout(null);
        this.add(pnBtn, BorderLayout.NORTH);

        JLabel lbTuNgay = new JLabel("Từ ngày: ");
        JLabel lbDenNgay = new JLabel("Đến ngày: ");

        dpTuNgay = new kDatePicker();
        dpDenNgay = new kDatePicker();
        btnThongKe = new JButton("Thống kê");
        btnThongKe.setBackground(Color.decode("#d0e1fd"));
        btnThongKe.setForeground(Color.decode("#1a66e3"));

        String[] cols = { "STT", "Mã HD" };
        DefaultTableModel modelTableDT = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table = new JTable(modelTableDT);

        JScrollPane scpTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scpTable.getViewport().setBackground(Color.WHITE);

        JPanel pnTableRevenue = new JPanel();
        pnTableRevenue.setBackground(Color.WHITE);
        pnTableRevenue.setLayout(new BorderLayout(0, 0));
        pnTableRevenue.add(scpTable, BorderLayout.CENTER);

        pnBtn.add(lbTuNgay);
        pnBtn.add(dpTuNgay);
        pnBtn.add(lbDenNgay);
        pnBtn.add(dpDenNgay);
        pnBtn.add(btnThongKe);
        this.add(pnTableRevenue, BorderLayout.CENTER);

        int h = 20;
        lbTuNgay.setBounds(10, 51, 70, h);
        dpTuNgay.setBounds(75, 51, 150, h);
        lbDenNgay.setBounds(240, 51, 70, h);
        dpDenNgay.setBounds(315, 51, 150, h);
        btnThongKe.setBounds(480, 51, 100, h);

        JPanel pnTitle = new JPanel();
        pnTitle.setBackground(Color.WHITE);
        pnTitle.setBounds(0, 0, 1270, 40);
        pnTitle.setBackground(Color.decode("#d0e1fd"));
        pnBtn.add(pnTitle);

        JLabel lbTitle = new JLabel("Quản lý Doanh Thu");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lbTitle.setForeground(Color.decode("#1a66e3"));
        pnTitle.add(lbTitle);
        pnTableRevenue.setBounds(10, 25, 1250, 600);

        btnThongKe.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
