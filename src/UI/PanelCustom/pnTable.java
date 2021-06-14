package UI.PanelCustom;

import javax.swing.*;
import javax.swing.table.*;

import DAO.*;
import entity.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.border.*;

public class pnTable extends JPanel implements interfaceBtn, ActionListener, MouseListener, KeyListener, ItemListener {
    private JTable table;
    private DefaultTableModel modelTable;
    private JTextField txtTableID, txtTableName, txtKeyWord;
    private JButton btnAdd, btnDelete, btnUpdate, btnRefresh, btnLogOut, btnBack, btnSearch, btnViewAll;
    private ImageIcon addIcon = new ImageIcon("img/blueAdd_16.png");
    private ImageIcon trashIcon = new ImageIcon("img/trash_16.png");
    private ImageIcon refreshIcon = new ImageIcon("img/refresh_16.png");
    private ImageIcon searchIcon = new ImageIcon("img/search_16.png");
    private ImageIcon logOutIcon = new ImageIcon("img/logout_16.png");
    private ImageIcon backIcon = new ImageIcon("img/back_16.png");
    private ImageIcon updateIcon = new ImageIcon("img/update_16.png");
    int index = 1;
    private JRadioButton radEmpty, radNotEmpty;
    private JComboBox<String> cboSearch;
    private Account loginAccount = null;

    public pnTable(Account account) {
        setSize(1270, 630);
        this.setLayout(null);
        this.setLayout(new BorderLayout(0, 0));
        this.loginAccount = account;

        JPanel pnTop = new JPanel();
        pnTop.setBackground(Color.WHITE);
        pnTop.setPreferredSize(new Dimension(10, 200));
        pnTop.setLayout(null);
        this.add(pnTop, BorderLayout.NORTH);

        JPanel pnTitle = new JPanel();
        pnTitle.setBounds(0, 0, 1270, 40);
        pnTitle.setBackground(Color.decode("#d0e1fd"));
        pnTop.add(pnTitle);

        JLabel lbTitle = new JLabel("Quản Lý Bàn");
        customUI.getInstance().setCustomLbTitle(lbTitle);
        pnTitle.add(lbTitle);

        JPanel pnInfo = new JPanel();
        pnInfo.setBorder(new TitledBorder(null, "Thông tin bàn ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnInfo.setBackground(Color.WHITE);
        pnInfo.setLayout(null);
        pnInfo.setBounds(0, 41, 1270, 120);
        pnTop.add(pnInfo);

        JLabel lbTableID = new JLabel("Mã bàn: ");
        lbTableID.setBounds(10, 22, 70, 14);
        lbTableID.setBackground(Color.decode("#f9f9f9"));
        pnInfo.add(lbTableID);

        txtTableID = new JTextField();
        txtTableID.setEditable(false);
        txtTableID.setBounds(74, 19, 150, 20);
        pnInfo.add(txtTableID);
        txtTableID.setColumns(10);

        JLabel lbTableName = new JLabel("Tên bàn: ");
        lbTableName.setBounds(10, 48, 70, 16);
        pnInfo.add(lbTableName);

        txtTableName = new JTextField();
        txtTableName.setBounds(74, 46, 150, 20);
        pnInfo.add(txtTableName);
        txtTableName.setColumns(10);

        btnAdd = new JButton("Thêm", addIcon);
        btnAdd.setBounds(10, 77, 101, 26);
        customUI.getInstance().setCustomBtn(btnAdd);
        pnInfo.add(btnAdd);

        btnDelete = new JButton("Xóa", trashIcon);
        btnDelete.setBounds(123, 77, 101, 26);
        customUI.getInstance().setCustomBtn(btnDelete);
        pnInfo.add(btnDelete);

        btnUpdate = new JButton("Sửa", updateIcon);
        btnUpdate.setBounds(236, 77, 101, 26);
        customUI.getInstance().setCustomBtn(btnUpdate);
        pnInfo.add(btnUpdate);

        btnRefresh = new JButton("Làm mới", refreshIcon);
        btnRefresh.setBounds(349, 77, 110, 26);
        customUI.getInstance().setCustomBtn(btnRefresh);
        pnInfo.add(btnRefresh);

        btnLogOut = new JButton("Đăng xuất", logOutIcon);
        btnLogOut.setBounds(584, 77, 120, 26);
        customUI.getInstance().setCustomBtn(btnLogOut);
        pnInfo.add(btnLogOut);

        btnBack = new JButton("Thoát", backIcon);
        btnBack.setBounds(471, 78, 101, 26);
        customUI.getInstance().setCustomBtn(btnBack);
        pnInfo.add(btnBack);

        JLabel lbStatus = new JLabel("Trạng thái: ");
        lbStatus.setBounds(242, 21, 80, 16);
        pnInfo.add(lbStatus);

        radEmpty = new JRadioButton("Trống");
        radEmpty.setBounds(310, 17, 124, 24);
        radEmpty.setBackground(Color.WHITE);
        radEmpty.setSelected(true);
        pnInfo.add(radEmpty);

        radNotEmpty = new JRadioButton("Có người");
        radNotEmpty.setBounds(310, 44, 124, 24);
        radNotEmpty.setBackground(Color.WHITE);
        pnInfo.add(radNotEmpty);

        ButtonGroup grRadStatus = new ButtonGroup();
        grRadStatus.add(radEmpty);
        grRadStatus.add(radNotEmpty);

        JPanel pnSearch = new JPanel();
        pnSearch.setBackground(Color.WHITE);
        pnSearch.setBounds(0, 161, 1270, 39);
        pnSearch.setLayout(null);
        pnTop.add(pnSearch);

        JLabel lbSearch = new JLabel("Trạng thái: ");
        lbSearch.setBounds(12, 12, 100, 16);
        pnSearch.add(lbSearch);

        cboSearch = new JComboBox<String>();
        cboSearch.setBounds(81, 10, 170, 20);
        cboSearch.addItem("Tất cả");
        cboSearch.addItem("Trống");
        cboSearch.addItem("Có người");
        pnSearch.add(cboSearch);

        JLabel lbKeyWord = new JLabel("Từ khóa: ");
        lbKeyWord.setBounds(269, 12, 70, 16);
        pnSearch.add(lbKeyWord);

        txtKeyWord = new JTextField();
        txtKeyWord.setBounds(336, 10, 170, 20);
        pnSearch.add(txtKeyWord);
        txtKeyWord.setColumns(10);

        btnSearch = new JButton("Tìm kiếm", searchIcon);
        btnSearch.setBounds(518, 7, 120, 26);
        customUI.getInstance().setCustomBtn(btnSearch);
        pnSearch.add(btnSearch);

        btnViewAll = new JButton("Xem tất cả", null);
        btnViewAll.setBounds(650, 7, 120, 26);
        customUI.getInstance().setCustomBtn(btnViewAll);
        pnSearch.add(btnViewAll);

        String[] cols = { "STT", "Mã bàn", "Tên bàn", "Trạng thái" };
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
        pnTable.setBounds(10, 25, 1250, 600);

        this.add(pnTable, BorderLayout.CENTER);
        reSizeColumnTable();
        loadTableList();

        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnSearch.addActionListener(this);
        btnViewAll.addActionListener(this);

        btnAdd.addMouseListener(this);
        btnDelete.addMouseListener(this);
        btnUpdate.addMouseListener(this);
        btnRefresh.addMouseListener(this);
        btnSearch.addMouseListener(this);
        btnViewAll.addMouseListener(this);
        btnBack.addMouseListener(this);
        btnLogOut.addMouseListener(this);

        table.addMouseListener(this);

        txtKeyWord.addKeyListener(this);

        cboSearch.addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnAdd)) {
            if (validData()) {
                Table tableData = getDataInFrom();
                boolean result = TableDAO.getInstance().insertTable(tableData);
                DecimalFormat df = new DecimalFormat("#,###.##");
                if (result == true) {
                    String stt = df.format(index++);
                    int tableID = TableDAO.getInstance().getLastCategoryID();
                    modelTable.addRow(new Object[] { stt, tableID, tableData.getName(), tableData.getStatus() });
                    modelTable.fireTableDataChanged();
                    txtTableID.setText(String.valueOf(tableID));
                    int lastIndex = table.getRowCount() - 1;
                    table.getSelectionModel().setSelectionInterval(lastIndex, lastIndex);
                    table.scrollRectToVisible(table.getCellRect(lastIndex, lastIndex, true));
                    JOptionPane.showMessageDialog(this, "Thêm bàn thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm bàn thất bại");
                }
            }
        } else if (o.equals(btnUpdate)) {
            if (authentication()) {
                if (validData()) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Table tableData = getDataInFrom();
                        boolean result = TableDAO.getInstance().updateTable(tableData);
                        if (result == true) {
                            modelTable.setValueAt(tableData.getName(), row, 2);
                            modelTable.setValueAt(tableData.getStatus(), row, 3);

                            JOptionPane.showMessageDialog(this, "cập nhật table thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "cập nhật table thất bại");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Chọn 1 table cần cập nhật");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
            }
        } else if (o.equals(btnDelete)) {
            if (authentication()) {
                int row = table.getSelectedRow();
                String tableIDStr = txtTableID.getText().trim();
                if (row != -1 && !tableIDStr.equals("")) {
                    int tableID = Integer.parseInt(tableIDStr);
                    String tableName = (TableDAO.getInstance().getTableByTableID(tableID)).getName();
                    int billInfoUnpaidCount = BillDAO.getInstance().getListBillUnpaidByTableID(tableID);
                    if (billInfoUnpaidCount > 0) {
                        String message = String.format("Để xóa %s \nBạn cần thanh toán hóa đơn của bàn này", tableName);
                        JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        String message = String.format(
                                "Bạn muốn xóa %s\n Xóa bàn sẽ xóa tất cả hóa đơn của bàn từ trước đến giờ\nHãy suy nghĩ thật kỹ!!!",
                                tableName);
                        int select = JOptionPane.showConfirmDialog(this, message, "Xác nhận xóa",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (select == JOptionPane.YES_OPTION) {
                            int countBill = BillDAO.getInstance().getCountBillByTableID(tableID);
                            if (countBill > 0)
                                BillDAO.getInstance().deleteBillByTableID(tableID);
                            boolean result = TableDAO.getInstance().deleteTable(tableID);
                            if (result == true) {
                                modelTable.removeRow(row);
                                refreshInput();
                                JOptionPane.showMessageDialog(this, "Xóa bàn thành công");
                            } else {
                                JOptionPane.showMessageDialog(this, "Xóa bàn thất bại");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chọn 1 bàn cần xóa");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
            }
        } else if (o.equals(btnRefresh)) {
            refreshInput();
        } else if (o.equals(btnSearch)) {
            String tableName = txtKeyWord.getText().trim();
            String status = cboSearch.getSelectedItem().toString();
            if (tableName.equals("")) {
                if (status.equals("Tất cả"))
                    loadTableList();
                else
                    searchTableListByStatus(status);
            } else {
                if (status.equals("Tất cả"))
                    searchTableListByName(tableName);
                else
                    searchTableListByNameAndStatus(tableName, status);
            }
        } else if (o.equals(btnViewAll)) {
            loadTableList();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object o = e.getSource();
        if (o.equals(cboSearch)) {
            String tableName = String.valueOf(cboSearch.getSelectedItem());
            if (tableName.equals("Tất cả")) {
                loadTableList();
            } else {
                searchTableListByStatus(tableName);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(table)) {
            int row = table.getSelectedRow();
            txtTableID.setText(modelTable.getValueAt(row, 1).toString());
            txtTableName.setText(modelTable.getValueAt(row, 2).toString());
            String status = String.valueOf(modelTable.getValueAt(row, 3));
            if (status.equals("Trống"))
                radEmpty.setSelected(true);
            else
                radNotEmpty.setSelected(true);
        }
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
        if (o.equals(btnAdd)) {
            customUI.getInstance().setCustomBtnHover(btnAdd);
        } else if (o.equals(btnDelete)) {
            customUI.getInstance().setCustomBtnHover(btnDelete);
        } else if (o.equals(btnUpdate)) {
            customUI.getInstance().setCustomBtnHover(btnUpdate);
        } else if (o.equals(btnRefresh)) {
            customUI.getInstance().setCustomBtnHover(btnRefresh);
        } else if (o.equals(btnBack)) {
            customUI.getInstance().setCustomBtnHover(btnBack);
        } else if (o.equals(btnLogOut)) {
            customUI.getInstance().setCustomBtnHover(btnLogOut);
        } else if (o.equals(btnSearch)) {
            customUI.getInstance().setCustomBtnHover(btnSearch);
        } else if (o.equals(btnViewAll)) {
            customUI.getInstance().setCustomBtnHover(btnViewAll);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(btnAdd)) {
            customUI.getInstance().setCustomBtn(btnAdd);
        } else if (o.equals(btnDelete)) {
            customUI.getInstance().setCustomBtn(btnDelete);
        } else if (o.equals(btnUpdate)) {
            customUI.getInstance().setCustomBtn(btnUpdate);
        } else if (o.equals(btnRefresh)) {
            customUI.getInstance().setCustomBtn(btnRefresh);
        } else if (o.equals(btnBack)) {
            customUI.getInstance().setCustomBtn(btnBack);
        } else if (o.equals(btnLogOut)) {
            customUI.getInstance().setCustomBtn(btnLogOut);
        } else if (o.equals(btnSearch)) {
            customUI.getInstance().setCustomBtn(btnSearch);
        } else if (o.equals(btnViewAll)) {
            customUI.getInstance().setCustomBtn(btnViewAll);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object o = e.getSource();
        Object key = e.getKeyCode();
        if (o.equals(txtKeyWord)) {
            if (key.equals(KeyEvent.VK_ENTER))
                btnSearch.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public JButton getBtnLogOut() {
        return btnLogOut;
    }

    @Override
    public JButton getBtnBack() {
        return btnBack;
    }

    private boolean authentication() {
        String password = "";
        JPasswordField passwordField = new JPasswordField();
        Object[] obj = { "Vui lòng nhập mật khẩu để xác thực danh tính:\n\n", passwordField };
        Object stringArray[] = { "OK", "Cancel" };
        int select = JOptionPane.showOptionDialog(null, obj, "Xác thực", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, stringArray, obj);
        
        boolean result = false;
        if (select == JOptionPane.YES_OPTION) {
            password = new String(passwordField.getPassword());
            System.out.println(password);
            result = AccountDAO.getInstance().Login(loginAccount.getUsername(), password);
        }
        return result;
    }

    private boolean validData() {
        String table = txtTableName.getText().trim();
        if (!(table.length() > 0)) {
            JOptionPane.showMessageDialog(this, "Tên loại sản phẩm không được để trống");
            return false;
        }
        return true;
    }

    private void refreshInput() {
        txtTableID.setText("");
        txtTableName.setText("");
        radEmpty.setSelected(true);
    }

    private Table getDataInFrom() {
        String tableName = txtTableName.getText().trim();
        int tableID = 0;
        if (!txtTableID.getText().trim().equals(""))
            tableID = Integer.parseInt(txtTableID.getText().trim());
        String status = "";
        if (radEmpty.isSelected())
            status = radEmpty.getText();
        else
            status = radNotEmpty.getText();

        return (new Table(tableID, tableName, status));
    }

    private void loadTableList() {
        ArrayList<Table> dataList = TableDAO.getInstance().getListTable();
        loadDataIntoTable(dataList);
    }

    private void searchTableListByName(String tableName) {
        ArrayList<Table> dataList = TableDAO.getInstance().getTableListByTableName(tableName);
        loadDataIntoTable(dataList);
    }

    private void searchTableListByNameAndStatus(String tableName, String status) {
        ArrayList<Table> dataList = TableDAO.getInstance().getTableListByTableNameAndStatus(tableName, status);
        loadDataIntoTable(dataList);
    }

    private void searchTableListByStatus(String status) {
        ArrayList<Table> dataList = TableDAO.getInstance().getTableListByStatus(status);
        loadDataIntoTable(dataList);
    }

    private void loadDataIntoTable(ArrayList<Table> tableList) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        modelTable.getDataVector().removeAllElements();
        modelTable.fireTableDataChanged();
        index = 1;
        for (Table item : tableList) {
            String stt = df.format(index++);
            modelTable.addRow(new Object[] { stt, item.getId(), item.getName(), item.getStatus() });
        }
    }

    private void reSizeColumnTable() {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    }
}
