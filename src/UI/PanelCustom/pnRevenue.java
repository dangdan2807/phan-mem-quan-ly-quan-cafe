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
import javax.swing.border.TitledBorder;

public class pnRevenue extends JPanel implements interfaceBtn, ActionListener, MouseListener {
    private kDatePicker dpTuNgay, dpDenNgay;
    private JButton btnStatistic, btnBack, btnLogOut;
    private JTable table;
    private DefaultTableModel modelTable;
    private ImageIcon logOutIcon = new ImageIcon("img/logout_16.png");
    private ImageIcon analyticsIcon = new ImageIcon("img/analytics_16.png");
    private ImageIcon backIcon = new ImageIcon("img/back_16.png");

    public pnRevenue() {
        setLayout(null);
        setSize(1270, 630);
        setLayout(new BorderLayout(0, 0));

        JPanel pnTop = new JPanel();
        pnTop.setBackground(Color.WHITE);
        pnTop.setPreferredSize(new Dimension(10, 100));
        pnTop.setLayout(null);
        this.add(pnTop, BorderLayout.NORTH);

        JLabel lbTuNgay = new JLabel("Từ ngày: ");
        JLabel lbDenNgay = new JLabel("Đến ngày: ");

        dpTuNgay = new kDatePicker();
        dpDenNgay = new kDatePicker();
        btnStatistic = new JButton("Thống kê", analyticsIcon);
        customUI.getInstance().setCustomBtn(btnStatistic);

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

        JPanel pnTable = new JPanel();
        pnTable.setBackground(Color.WHITE);
        pnTable.setLayout(new BorderLayout(0, 0));
        pnTable.add(scpTable, BorderLayout.CENTER);

        JPanel pnStatistic = new JPanel();
        pnStatistic.setBorder(
                new TitledBorder(null, "Thông tin lập thống kê ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnStatistic.setBackground(Color.WHITE);
        pnStatistic.setLayout(null);
        pnStatistic.setBounds(0, 41, 1270, 59);
        pnStatistic.add(lbTuNgay);
        pnStatistic.add(dpTuNgay);
        pnStatistic.add(lbDenNgay);
        pnStatistic.add(dpDenNgay);
        pnStatistic.add(btnStatistic);
        pnTop.add(pnStatistic);

        this.add(pnTable, BorderLayout.CENTER);

        int h = 20;
        lbTuNgay.setBounds(10, 21, 70, h);
        dpTuNgay.setBounds(75, 21, 150, h);
        lbDenNgay.setBounds(240, 21, 70, h);
        dpDenNgay.setBounds(315, 21, 150, h);
        btnStatistic.setBounds(477, 18, 120, 26);

        btnBack = new JButton("Thoát", backIcon);
        customUI.getInstance().setCustomBtn(btnBack);
        btnBack.setBounds(609, 18, 120, 26);
        pnStatistic.add(btnBack);

        btnLogOut = new JButton("Đăng xuất", logOutIcon);
        customUI.getInstance().setCustomBtn(btnLogOut);
        btnLogOut.setBounds(741, 18, 120, 26);
        pnStatistic.add(btnLogOut);

        JPanel pnTitle = new JPanel();
        pnTitle.setBackground(Color.WHITE);
        pnTitle.setBounds(0, 0, 1270, 40);
        pnTitle.setBackground(Color.decode("#d0e1fd"));
        pnTop.add(pnTitle);

        JLabel lbTitle = new JLabel("Quản lý Doanh Thu");
        customUI.getInstance().setCustomLbTitle(lbTitle);
        pnTitle.add(lbTitle);
        pnTable.setBounds(10, 25, 1250, 600);

        reSizeColumnTable();
        Date dateCheckIn = null;
        Date dateCheckOut = null;
        try {
            dateCheckIn = dpTuNgay.getFullDate();
            dateCheckOut = dpDenNgay.getFullDate();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        loadListBillByDate(dateCheckIn, dateCheckOut);

        btnStatistic.addActionListener(this);
        btnLogOut.addActionListener(this);
        btnBack.addActionListener(this);

        btnStatistic.addMouseListener(this);
        btnLogOut.addMouseListener(this);
        btnBack.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnStatistic)) {
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(btnStatistic)) {
            customUI.getInstance().setCustomBtnHover(btnStatistic);
        } else if (o.equals(btnLogOut)) {
            customUI.getInstance().setCustomBtnHover(btnLogOut);
        } else if (o.equals(btnBack)) {
            customUI.getInstance().setCustomBtnHover(btnBack);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(btnStatistic)) {
            customUI.getInstance().setCustomBtn(btnStatistic);
        } else if (o.equals(btnLogOut)) {
            customUI.getInstance().setCustomBtn(btnLogOut);
        } else if (o.equals(btnBack)) {
            customUI.getInstance().setCustomBtn(btnBack);
        }
    }

    private void loadListBillByDate(Date dateCheckIn, Date dateCheckOut) {
        modelTable.getDataVector().removeAllElements();
        modelTable.fireTableDataChanged();
        ResultSet rs = BillDAO.getInstance().getBillListByDate(dateCheckIn, dateCheckOut);
        DecimalFormat df = new DecimalFormat("#,###.##");
        int i = 1;
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

    @Override
    public JButton getBtnLogOut() {
        return btnLogOut;
    }

    @Override
    public JButton getBtnBack() {
        return btnBack;
    }

    private void reSizeColumnTable() {
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
    }
}
