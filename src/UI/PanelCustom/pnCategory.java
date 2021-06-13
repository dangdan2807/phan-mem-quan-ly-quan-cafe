package UI.PanelCustom;

import javax.swing.*;
import javax.swing.table.*;

import DAO.*;
import entity.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.border.*;

public class pnCategory extends JFrame
        implements interfaceBtn, ActionListener, MouseListener, ItemListener, KeyListener {
    private JTable table;
    private DefaultTableModel modelTable;
    private JTextField txtCategoryID, txtCategoryName, txtKeyWord;
    private JButton btnAdd, btnDelete, btnUpdate, btnRefresh, btnLogOut, btnBack, btnSearch, btnViewAll;
    private ImageIcon addIcon = new ImageIcon("img/blueAdd_16.png");
    private ImageIcon trashIcon = new ImageIcon("img/trash_16.png");
    private ImageIcon refreshIcon = new ImageIcon("img/refresh_16.png");
    private ImageIcon searchIcon = new ImageIcon("img/search_16.png");
    private ImageIcon logOutIcon = new ImageIcon("img/logout_16.png");
    private ImageIcon backIcon = new ImageIcon("img/back_16.png");
    private ImageIcon updateIcon = new ImageIcon("img/update_16.png");
    int index = 1;

    public pnCategory() {
        setSize(1270, 630);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel pnTop = new JPanel();
        pnTop.setBackground(Color.WHITE);
        pnTop.setPreferredSize(new Dimension(10, 200));
        pnTop.setLayout(null);
        getContentPane().add(pnTop, BorderLayout.NORTH);

        JPanel pnTitle = new JPanel();
        pnTitle.setBounds(0, 0, 1270, 40);
        pnTitle.setBackground(Color.decode("#d0e1fd"));
        pnTop.add(pnTitle);

        JLabel lbTitle = new JLabel("Quản Lý Loại Sản Phẩm");
        customUI.getInstance().setCustomLbTitle(lbTitle);
        pnTitle.add(lbTitle);

        JPanel pnInfo = new JPanel();
        pnInfo.setBorder(
                new TitledBorder(null, "Thông tin sản phẩm ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnInfo.setBackground(Color.WHITE);
        pnInfo.setLayout(null);
        pnInfo.setBounds(0, 41, 1270, 120);
        pnTop.add(pnInfo);

        JLabel lbProductID = new JLabel("Mã loại sản phẩm: ");
        lbProductID.setBounds(10, 22, 120, 14);
        lbProductID.setBackground(Color.decode("#f9f9f9"));
        pnInfo.add(lbProductID);

        txtCategoryID = new JTextField();
        txtCategoryID.setEditable(false);
        txtCategoryID.setBounds(142, 19, 150, 20);
        pnInfo.add(txtCategoryID);
        txtCategoryID.setColumns(10);

        JLabel lbProductName = new JLabel("Tên loại sản phẩm: ");
        lbProductName.setBounds(10, 48, 120, 16);
        pnInfo.add(lbProductName);

        txtCategoryName = new JTextField();
        txtCategoryName.setBounds(142, 46, 150, 20);
        pnInfo.add(txtCategoryName);
        txtCategoryName.setColumns(10);

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

        JPanel pnSearch = new JPanel();
        pnSearch.setBackground(Color.WHITE);
        pnSearch.setBounds(0, 161, 1270, 39);
        pnSearch.setLayout(null);
        pnTop.add(pnSearch);

        JLabel lbKeyWord = new JLabel("Từ khóa: ");
        lbKeyWord.setBounds(10, 12, 70, 16);
        pnSearch.add(lbKeyWord);

        txtKeyWord = new JTextField();
        txtKeyWord.setBounds(75, 10, 170, 20);
        pnSearch.add(txtKeyWord);
        txtKeyWord.setColumns(10);

        btnSearch = new JButton("Tìm kiếm", searchIcon);
        btnSearch.setBounds(257, 7, 120, 26);
        customUI.getInstance().setCustomBtn(btnSearch);
        pnSearch.add(btnSearch);

        btnViewAll = new JButton("Xem tất cả", null);
        btnViewAll.setBounds(389, 7, 120, 26);
        customUI.getInstance().setCustomBtn(btnViewAll);
        pnSearch.add(btnViewAll);

        String[] cols = { "STT", "Mã loại sản phẩm", "Tên loại sản phẩm " };
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

        getContentPane().add(pnTable, BorderLayout.CENTER);
        reSizeColumnTable();
        loadCategoryList();

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

        table.addMouseListener(this);

        txtKeyWord.addKeyListener(this);
    }

    public static void main(String[] args) {
        new pnCategory().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnAdd)) {
            if (validData()) {
                Category category = getDataInFrom();
                boolean result = CategoryDAO.getInstance().insertCategory(category);
                DecimalFormat df = new DecimalFormat("#,###.##");
                if (result == true) {
                    String stt = df.format(index++);
                    int categoryID = CategoryDAO.getInstance().getLastCategoryID();
                    modelTable.addRow(new Object[] { stt, categoryID, category.getName() });
                    modelTable.fireTableDataChanged();
                    int lastIndex = table.getRowCount() - 1;
                    table.getSelectionModel().setSelectionInterval(lastIndex, lastIndex);
                    table.scrollRectToVisible(table.getCellRect(lastIndex, lastIndex, true));
                    JOptionPane.showMessageDialog(this, "Thêm loại sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm loại sản phẩm thất bại");
                }
            }
        // } else if (o.equals(btnUpdate)) {
        //     if (validData()) {
        //         int row = table.getSelectedRow();
        //         if (row == -1) {
        //             Category category = getDataInFrom();
        //             boolean result = ProductDAO.getInstance().updateProduct(category);
        //             DecimalFormat df = new DecimalFormat("#,###.##");
        //             if (result == true) {
        //                 modelTable.setValueAt(category.getName(), row, 2);
        //                 JOptionPane.showMessageDialog(this, "cập nhật loại sản phẩm thành công");
        //             } else {
        //                 JOptionPane.showMessageDialog(this, "cập nhật loại sản phẩm thất bại");
        //             }
        //         } else {
        //             JOptionPane.showMessageDialog(this, "Chọn 1 loại sản phẩm cần cập nhật");
        //         }
        //     }
            // } else if (o.equals(btnDelete)) {
            // String productName = txtProductName.getText().trim();
            // String productIDStr = txtProductID.getText().trim();
            // int row = table.getSelectedRow();
            // if (row != -1 && !productIDStr.equals("")) {
            // String message = String.format(
            // "Bạn có muốn xóa sản phẩm: %s \nXóa sản phẩm sẽ dẫn đến xóa tất cả trong các
            // hóa đơn đã thanh toán trước đây",
            // productName);
            // int select = JOptionPane.showConfirmDialog(this, message, "Xác nhận xóa",
            // JOptionPane.YES_NO_OPTION,
            // JOptionPane.QUESTION_MESSAGE);
            // if (select == JOptionPane.YES_OPTION) {
            // int productID = Integer.parseInt(productIDStr);
            // BillInfoDAO.getInstance().deleteBillInfoByProductID(productID);
            // boolean resultProduct = ProductDAO.getInstance().deleteProduct(productID);
            // if (resultProduct == true) {
            // modelTable.removeRow(row);
            // refreshInput();
            // JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
            // } else {
            // JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại");
            // }
            // }
            // } else {
            // JOptionPane.showMessageDialog(this, "Chọn 1 sản phẩm cần xóa");
            // }
        } else if (o.equals(btnRefresh)) {
            refreshInput();
        } else if (o.equals(btnSearch)) {
            String categoryName = txtKeyWord.getText().trim();
            if (categoryName.equals("")) {
                JOptionPane.showMessageDialog(this, "Nhập tên loại sản phẩm cần tìm");
            } else {
                searchCategoryListByName(categoryName);
            }
        } else if (o.equals(btnViewAll)) {
            loadCategoryList();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object o = e.getSource();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(table)) {
            int row = table.getSelectedRow();
            txtCategoryID.setText(table.getValueAt(row, 1).toString());
            txtCategoryName.setText(modelTable.getValueAt(row, 2).toString());
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

    private boolean validData() {
        String productName = txtCategoryName.getText().trim();
        if (!(productName.length() > 0)) {
            JOptionPane.showMessageDialog(this, "Tên loại sản phẩm không được để trống");
            return false;
        }
        return true;
    }

    private void refreshInput() {
        txtCategoryID.setText("");
        txtCategoryName.setText("");
    }

    private Category getDataInFrom() {
        String categoryName = txtCategoryName.getText().trim();
        int categoryID = Integer.parseInt(txtCategoryID.getText().trim());
        return (new Category(categoryID, categoryName));
    }

    private void loadCategoryList() {
        ArrayList<Category> categoryList = CategoryDAO.getInstance().getListCategory();
        loadDataIntoTable(categoryList);
    }

    private void searchCategoryListByName(String categoryName) {
        ArrayList<Category> dataList = CategoryDAO.getInstance().getListCategoryByName(categoryName);
        loadDataIntoTable(dataList);
    }

    // private void loadProductListByCategoryNameAndProductName(String productName,
    // String categoryName) {
    // ResultSet productList = null;
    // if (categoryName.equalsIgnoreCase("Tất cả")) {
    // productList =
    // ProductDAO.getInstance().searchProductByProductName(productName);
    // } else {
    // productList =
    // ProductDAO.getInstance().searchProductByCategoryNameAndProductName(productName,
    // categoryName);
    // }
    // loadDataIntoTable(productList);
    // }

    private void loadDataIntoTable(ArrayList<Category> categoryList) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        modelTable.getDataVector().removeAllElements();
        modelTable.fireTableDataChanged();
        index = 1;
        for (Category item : categoryList) {
            String stt = df.format(index++);
            modelTable.addRow(new Object[] { stt, item.getId(), item.getName() });

        }
    }

    private void reSizeColumnTable() {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    }
}
