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

public class pnAccount extends JPanel
        implements interfaceBtn, ActionListener, MouseListener, KeyListener, ItemListener {
    private JTable table;
    private DefaultTableModel modelTable;
    private JTextField txtUsername, txtDisplayName, txtKeyWord;
    private JButton btnAdd, btnDelete, btnUpdate, btnRefresh, btnLogOut, btnBack, btnSearch, btnViewAll, btnResetPass;
    private ImageIcon addIcon = new ImageIcon("img/blueAdd_16.png");
    private ImageIcon trashIcon = new ImageIcon("img/trash_16.png");
    private ImageIcon refreshIcon = new ImageIcon("img/refresh_16.png");
    private ImageIcon searchIcon = new ImageIcon("img/search_16.png");
    private ImageIcon logOutIcon = new ImageIcon("img/logout_16.png");
    private ImageIcon backIcon = new ImageIcon("img/back_16.png");
    private ImageIcon updateIcon = new ImageIcon("img/update_16.png");
    private JRadioButton radStaff, radManager;
    private JComboBox<String> cboSearch;
    int index = 1;
    private Account loginAccount = null;

    public pnAccount(Account account) {
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

        JLabel lbTitle = new JLabel("Quán Lý Tài Khoản");
        customUI.getInstance().setCustomLbTitle(lbTitle);
        pnTitle.add(lbTitle);

        JPanel pnInfo = new JPanel();
        pnInfo.setBorder(
                new TitledBorder(null, "Thông tin tài khoản ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnInfo.setBackground(Color.WHITE);
        pnInfo.setLayout(null);
        pnInfo.setBounds(0, 41, 1270, 120);
        pnTop.add(pnInfo);

        JLabel lbUsername = new JLabel("Tài khoản");
        lbUsername.setBounds(10, 22, 70, 14);
        lbUsername.setBackground(Color.decode("#f9f9f9"));
        pnInfo.add(lbUsername);

        txtUsername = new JTextField();
        // txtUsername.setEditable(false);
        txtUsername.setBounds(85, 19, 150, 20);
        pnInfo.add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lbDisplayName = new JLabel("Tên hiển thị: ");
        lbDisplayName.setBounds(10, 48, 80, 16);
        pnInfo.add(lbDisplayName);

        txtDisplayName = new JTextField();
        txtDisplayName.setBounds(85, 46, 150, 20);
        pnInfo.add(txtDisplayName);
        txtDisplayName.setColumns(10);

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

        JLabel lbType = new JLabel("Loại tài khoản: ");
        lbType.setBounds(253, 21, 95, 16);
        pnInfo.add(lbType);

        radStaff = new JRadioButton("Nhân viên");
        radStaff.setBounds(349, 17, 90, 24);
        radStaff.setBackground(Color.WHITE);
        radStaff.setSelected(true);
        pnInfo.add(radStaff);

        radManager = new JRadioButton("Quản lý");
        radManager.setBounds(349, 44, 90, 24);
        radManager.setBackground(Color.WHITE);
        pnInfo.add(radManager);

        ButtonGroup grRadStatus = new ButtonGroup();
        grRadStatus.add(radStaff);
        grRadStatus.add(radManager);

        btnResetPass = new JButton("Đặt lại mk", null);
        customUI.getInstance().setCustomBtn(btnResetPass);
        btnResetPass.setBounds(447, 16, 110, 26);
        pnInfo.add(btnResetPass);

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
        cboSearch.addItem("Nhân viên");
        cboSearch.addItem("Quản lý");
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

        String[] cols = { "STT", "Tài khoản", "Tên nhân viên", "Loại tài khoản" };
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
        loadAccountList();

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
                Account accountData = getDataInFrom();
                boolean result = AccountDAO.getInstance().insertAccount(accountData);
                DecimalFormat df = new DecimalFormat("#,###.##");
                if (result == true) {
                    String stt = df.format(index++);
                    String type = "Nhân viên";
                    if (accountData.getType() == 1)
                        type = "Quản lý";
                    modelTable.addRow(
                            new Object[] { stt, accountData.getUsername(), accountData.getDisplayName(), type });
                    modelTable.fireTableDataChanged();
                    // di chuyển và bô đen dòng vừa thêm vào
                    int lastIndex = table.getRowCount() - 1;
                    table.getSelectionModel().setSelectionInterval(lastIndex, lastIndex);
                    table.scrollRectToVisible(table.getCellRect(lastIndex, lastIndex, true));
                    JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công. Mật khẩu mặc định là: 123456");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm tài khoản thất bại");
                }
            }
        } else if (o.equals(btnUpdate)) {
            if (authentication()) {
                if (validData()) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Account accountData = getDataInFrom();
                        boolean result = AccountDAO.getInstance().updateAccount(accountData);
                        if (result == true) {
                            String type = "Nhân viên";
                            if (accountData.getType() == 1)
                                type = "Quản lý";
                            modelTable.setValueAt(accountData.getDisplayName(), row, 2);
                            modelTable.setValueAt(type, row, 3);
                            JOptionPane.showMessageDialog(this, "cập nhật tài khoản thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "cập nhật tài khoản thất bại");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Chọn 1 tài khoản cần cập nhật");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
            }
        } else if (o.equals(btnDelete)) {
            if (authentication()) {
                int row = table.getSelectedRow();
                String username = txtUsername.getText().trim();
                if (row != -1 && !username.equals("")) {
                    if (loginAccount.getUsername().equals(username)) {
                        JOptionPane.showMessageDialog(this, "Bậy rồi bạn ơi!! ai lại đi xóa chính mình :>");
                    } else {
                        String message = String.format("Bạn muốn xóa tài khoản %s\n", username);
                        int select = JOptionPane.showConfirmDialog(this, message, "Thông báo",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (select == JOptionPane.YES_OPTION) {
                            boolean result = AccountDAO.getInstance().deleteAccount(username);
                            if (result == true) {
                                modelTable.removeRow(row);
                                refreshInput();
                                JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công");
                            } else {
                                JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chọn 1 tài khoản cần xóa");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
            }
        } else if (o.equals(btnRefresh)) {
            refreshInput();
        } else if (o.equals(btnSearch)) {
            String username = txtKeyWord.getText().trim();
            String typeStr = cboSearch.getSelectedItem().toString();
            int type = 0;
            if (typeStr.equals("Tất cả"))
                type = -1;
            else if (typeStr.equals("Quản lý"))
                type = 1;

            if (username.equals("")) {
                if (type == -1) {
                    loadAccountList();
                } else {
                    searchAccountListByType(type);
                }
            } else {
                if (type == -1)
                    searchAccountListByUsername(username);
                else
                    searchAccountListByUsernameAndType(username, type);
            }
        } else if (o.equals(btnViewAll)) {
            loadAccountList();
        } else if (o.equals(btnResetPass)) {
            if (authentication()) {
                String username = txtUsername.getText().trim();
                if (username.equals("")) {
                    JOptionPane.showMessageDialog(this, "hãy chọn tài khoản");
                } else {
                    boolean result = AccountDAO.getInstance().resetPassword(username);
                    if (result == true)
                        JOptionPane.showMessageDialog(this, "Mật khẩu mới là: 123456");
                    else
                        JOptionPane.showMessageDialog(this, "Đặt lại mật khẩu thất bại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object o = e.getSource();
        if (o.equals(cboSearch)) {
            String typeStr = String.valueOf(cboSearch.getSelectedItem());
            if (typeStr.equals("Tất cả")) {
                loadAccountList();
            } else {
                if (typeStr.equals("Nhân viên"))
                    searchAccountListByType(0);
                else
                    searchAccountListByType(1);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(table)) {
            int row = table.getSelectedRow();
            txtUsername.setText(modelTable.getValueAt(row, 1).toString());
            txtDisplayName.setText(modelTable.getValueAt(row, 2).toString());
            String type = String.valueOf(modelTable.getValueAt(row, 3));
            if (type.equals("Nhân viên"))
                radStaff.setSelected(true);
            else
                radManager.setSelected(true);
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
        } else if (o.equals(btnResetPass)) {
            customUI.getInstance().setCustomBtnHover(btnResetPass);
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
        } else if (o.equals(btnResetPass)) {
            customUI.getInstance().setCustomBtn(btnResetPass);
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
        String username = txtUsername.getText().trim();
        if (!(username.length() > 0 && username.matches("^[a-zA-Z0-9_@#]{5,}$"))) {
            if (username.length() < 0)
                JOptionPane.showMessageDialog(this, "Tài khoản không được để trống");
            else
                JOptionPane.showMessageDialog(this,
                        "Tài khoản phải có tối thiểu 5 ký tự và chỉ được chứa chữ, số, @, _, #");
            return false;
        }

        String displayName = txtDisplayName.getText().trim();
        if (!(displayName.length() > 0)) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống");
            return false;
        }

        return true;
    }

    private void refreshInput() {
        txtUsername.setText("");
        txtDisplayName.setText("");
        radStaff.setSelected(true);
    }

    // mỏ txtUsername
    private Account getDataInFrom() {
        String username = "";
        if (!txtUsername.getText().trim().equals(""))
            username = txtUsername.getText().trim();
        String password = "-3110-365773-7089-85-6686-3287-1415-12062";
        String displayName = "Chưa cập nhật";
        if (!txtDisplayName.getText().trim().equals(""))
            displayName = txtDisplayName.getText().trim();
        int type = 0;
        if (radManager.isSelected())
            type = 1;
        return (new Account(username, password, displayName, type));
    }

    private void loadAccountList() {
        ArrayList<Account> dataList = AccountDAO.getInstance().getListAccount();
        loadDataIntoTable(dataList);
    }

    private void searchAccountListByUsername(String username) {
        ArrayList<Account> dataList = AccountDAO.getInstance().searchAccountListByUsername(username);
        loadDataIntoTable(dataList);
    }

    private void searchAccountListByUsernameAndType(String username, int type) {
        ArrayList<Account> dataList = AccountDAO.getInstance().searchAccountListByUsernameAndType(username, type);
        loadDataIntoTable(dataList);
    }

    private void searchAccountListByType(int type) {
        ArrayList<Account> dataList = AccountDAO.getInstance().searchAccountListByType(type);
        loadDataIntoTable(dataList);
    }

    private void loadDataIntoTable(ArrayList<Account> dataList) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        modelTable.getDataVector().removeAllElements();
        modelTable.fireTableDataChanged();
        index = 1;
        for (Account item : dataList) {
            String stt = df.format(index++);
            String type = "Nhân viên";
            if (item.getType() == 1)
                type = "Quản lý";
            modelTable.addRow(new Object[] { stt, item.getUsername(), item.getDisplayName(), type });
        }
    }

    private void reSizeColumnTable() {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }
}
