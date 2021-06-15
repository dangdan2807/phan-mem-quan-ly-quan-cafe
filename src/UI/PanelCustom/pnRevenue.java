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
    private JButton btnStatistic, btnBack, btnLogOut, btnFirst, btnPrevious, btnNext, btnLast;
    private JTable table;
    private DefaultTableModel modelTable;
    private ImageIcon logOutIcon = new ImageIcon("img/logout_16.png");
    private ImageIcon analyticsIcon = new ImageIcon("img/analytics_16.png");
    private ImageIcon backIcon = new ImageIcon("img/back_16.png");
    private JTextField txtPageNum;
    private JLabel lbLastPageNum;

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

        JPanel pnSouth = new JPanel();
        pnSouth.setPreferredSize(new Dimension(10, 32));
        add(pnSouth, BorderLayout.SOUTH);

        btnFirst = new JButton("Trang đầu");
        customUI.getInstance().setCustomBtn(btnFirst);
        pnSouth.add(btnFirst);

        btnPrevious = new JButton("Trang trước");
        customUI.getInstance().setCustomBtn(btnPrevious);
        pnSouth.add(btnPrevious);

        JLabel lbSpace = new JLabel("");
        lbSpace.setPreferredSize(new Dimension(50, 20));
        pnSouth.add(lbSpace);

        txtPageNum = new JTextField();
        txtPageNum.setHorizontalAlignment(SwingConstants.CENTER);
        txtPageNum.setText("1");
        pnSouth.add(txtPageNum);
        txtPageNum.setColumns(10);

        lbLastPageNum = new JLabel("/1");
        lbLastPageNum.setPreferredSize(new Dimension(50, 20));
        pnSouth.add(lbLastPageNum);

        btnNext = new JButton("Trang sau");
        customUI.getInstance().setCustomBtn(btnNext);
        pnSouth.add(btnNext);

        btnLast = new JButton("Trang cuối");
        customUI.getInstance().setCustomBtn(btnLast);
        pnSouth.add(btnLast);

        loadListBillByDate(dateCheckIn, dateCheckOut, 1);

        btnStatistic.addActionListener(this);
        btnFirst.addActionListener(this);
        btnPrevious.addActionListener(this);
        btnNext.addActionListener(this);
        btnLast.addActionListener(this);

        btnStatistic.addMouseListener(this);
        btnLogOut.addMouseListener(this);
        btnBack.addMouseListener(this);
        btnFirst.addMouseListener(this);
        btnPrevious.addMouseListener(this);
        btnNext.addMouseListener(this);
        btnLast.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnStatistic)) {
            statistic();
        } else if (o.equals(btnFirst)) {
            txtPageNum.setText("1");
            statistic();
        } else if (o.equals(btnPrevious)) {
            int pageNum = Integer.parseInt(txtPageNum.getText().trim());
            if (pageNum != 1) {
                pageNum--;
                txtPageNum.setText(String.valueOf(pageNum));
                statistic();
            }
        } else if (o.equals(btnNext)) {
            int pageNum = Integer.parseInt(txtPageNum.getText().trim());
            int lastPage = Integer.parseInt(lbLastPageNum.getText().replace("/", ""));
            if (pageNum < lastPage) {
                pageNum++;
                txtPageNum.setText(String.valueOf(pageNum));
                statistic();
            }
        } else if (o.equals(btnLast)) {
            int lastPage = getLastPage();
            txtPageNum.setText(String.valueOf(lastPage));
            statistic();
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
        } else if (o.equals(btnFirst)) {
            customUI.getInstance().setCustomBtnHover(btnFirst);
        } else if (o.equals(btnPrevious)) {
            customUI.getInstance().setCustomBtnHover(btnPrevious);
        } else if (o.equals(btnNext)) {
            customUI.getInstance().setCustomBtnHover(btnNext);
        } else if (o.equals(btnLast)) {
            customUI.getInstance().setCustomBtnHover(btnLast);
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
        } else if (o.equals(btnFirst)) {
            customUI.getInstance().setCustomBtn(btnFirst);
        } else if (o.equals(btnPrevious)) {
            customUI.getInstance().setCustomBtn(btnPrevious);
        } else if (o.equals(btnNext)) {
            customUI.getInstance().setCustomBtn(btnNext);
        } else if (o.equals(btnLast)) {
            customUI.getInstance().setCustomBtn(btnLast);
        }
    }

    public int getLastPage() {
        Date dateCheckIn = null;
        Date dateCheckOut = null;
        try {
            dateCheckIn = dpTuNgay.getFullDate();
            dateCheckOut = dpDenNgay.getFullDate();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        int sumRecord = BillDAO.getInstance().getNumBillByDate(dateCheckIn, dateCheckOut);
        int lastPage = sumRecord / 30;
        if (sumRecord % 30 != 0) {
            lastPage++;
        }
        return lastPage;
    }

    private void statistic() {
        Date dateCheckIn = null;
        Date dateCheckOut = null;
        try {
            dateCheckIn = dpTuNgay.getFullDate();
            dateCheckOut = dpDenNgay.getFullDate();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        int lastPage = getLastPage();
        lbLastPageNum.setText("/" + lastPage);
        int pageNum = Integer.parseInt(txtPageNum.getText().trim());
        loadListBillByDate(dateCheckIn, dateCheckOut, pageNum);
    }

    private void loadListBillByDate(Date dateCheckIn, Date dateCheckOut, int pageNum) {
        modelTable.getDataVector().removeAllElements();
        modelTable.fireTableDataChanged();
        ResultSet rs = BillDAO.getInstance().getBillListByDateAndPage(dateCheckIn, dateCheckOut, pageNum);
        DecimalFormat df = new DecimalFormat("#,###.##");
        int i = 1 + (pageNum - 1) * 30;
        try {
            while (rs.next()) {
                int billID = rs.getInt("id");
                String name = rs.getString("name");
                String totalPrice = df.format(rs.getDouble("totalPrice"));
                String checkIn = formatDate(rs.getDate("dateCheckIn"));
                String checkOut = formatDate(rs.getDate("dateCheckOut"));
                int discount = rs.getInt("discount");
                String stt = df.format(i++);
                modelTable.addRow(new Object[] { stt, billID, name, checkIn, checkOut, discount + "%", totalPrice });
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
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);

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
