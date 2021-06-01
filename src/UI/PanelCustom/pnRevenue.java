package UI.PanelCustom;

import javax.swing.*;
import javax.swing.table.*;

import DAO.BillDAO;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class pnRevenue extends JPanel implements ActionListener {
    private JPanel pnBtn;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JButton btnThongKe;
    private JTable table;
    private DefaultTableModel modelTable;

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

        String[] cols = { "STT", "Mã HD", "Tên bàn", "Ngày checkIn", "Ngày checkOut", "Giảm giá", "Thành tiền" };
        modelTable = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table = new JTable(modelTable);

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
        Object o = e.getSource();
        if (o.equals(btnThongKe)) {
            Date dateCheckIn = null;
            Date dateCheckOut = null;
            try {
                dateCheckIn = dpTuNgay.getFullDate();
                dateCheckOut = dpDenNgay.getFullDate();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            loadListBillByDate(dateCheckIn, dateCheckOut);
        }
    }

    private void loadListBillByDate(Date dateCheckIn, Date dateCheckOut) {
        modelTable.getDataVector().removeAllElements();
        modelTable.fireTableDataChanged();
        ResultSet rs = BillDAO.getInstance().getBillListByDate(dateCheckIn, dateCheckOut);
        DecimalFormat df = new DecimalFormat("#,###.##");
        int i = 0;
        try {
            while (rs.next()) {
                String billID = String.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String totalPrice = df.format(rs.getDouble("totalPrice"));
                String checkIn = formatDate(rs.getDate("dateCheckIn"));
                String checkOut = formatDate(rs.getDate("dateCheckOut"));
                int discount = rs.getInt("discount");
                String stt = df.format(i++);
                modelTable.addRow(new Object[] { stt, billID, name, checkIn, checkOut, discount, totalPrice });
                ++i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String formatDate(Date date) {
        if (date == null)
            return "Chưa cập nhật";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
