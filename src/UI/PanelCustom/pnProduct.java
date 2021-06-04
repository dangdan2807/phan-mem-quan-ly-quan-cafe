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

public class pnProduct extends JPanel implements interfaceBtn, ActionListener, MouseListener, ItemListener {
    private JTable table;
    private DefaultTableModel modelTable;
    private JTextField txtProductID, txtProductName, txtPrice;
    private JComboBox<String> cboCategory, cboSearchCategory;
    private JButton btnAdd, btnDelete, btnUpdate, btnRefresh, btnLogOut, btnBack, btnSearch, btnViewAll;
    private JTextField txtKeyWord;
    private ImageIcon addIcon = new ImageIcon("img/blueAdd_16.png");
    private ImageIcon trashIcon = new ImageIcon("img/trash_16.png");
    private ImageIcon refreshIcon = new ImageIcon("img/refresh_16.png");
    private ImageIcon searchIcon = new ImageIcon("img/search_16.png");
    private ImageIcon logOutIcon = new ImageIcon("img/logout_16.png");
    private ImageIcon backIcon = new ImageIcon("img/back_16.png");
    private ImageIcon updateIcon = new ImageIcon("img/update_16.png");
    int index = 1;

    public pnProduct() {
        setSize(1270, 630);
        this.setLayout(null);
        this.setLayout(new BorderLayout(0, 0));

        JPanel pnTop = new JPanel();
        pnTop.setBackground(Color.WHITE);
        pnTop.setPreferredSize(new Dimension(10, 200));
        pnTop.setLayout(null);
        this.add(pnTop, BorderLayout.NORTH);

        JPanel pnTitle = new JPanel();
        pnTitle.setBounds(0, 0, 1270, 40);
        pnTitle.setBackground(Color.decode("#d0e1fd"));
        pnTop.add(pnTitle);

        JLabel lbTitle = new JLabel("Quản Lý Sản Phẩm");
        customUI.getInstance().setCustomLbTitle(lbTitle);
        pnTitle.add(lbTitle);

        JPanel pnInfo = new JPanel();
        pnInfo.setBorder(
                new TitledBorder(null, "Thông tin sản phẩm ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnInfo.setBackground(Color.WHITE);
        pnInfo.setLayout(null);
        pnInfo.setBounds(0, 41, 1270, 120);
        pnTop.add(pnInfo);

        JLabel lbProductID = new JLabel("Mã sản phẩm: ");
        lbProductID.setBounds(10, 22, 90, 14);
        lbProductID.setBackground(Color.decode("#f9f9f9"));
        pnInfo.add(lbProductID);

        txtProductID = new JTextField();
        txtProductID.setEditable(false);
        txtProductID.setBounds(101, 19, 150, 20);
        pnInfo.add(txtProductID);
        txtProductID.setColumns(10);

        JLabel lbProductName = new JLabel("Tên sản phẩm: ");
        lbProductName.setBounds(10, 48, 90, 16);
        pnInfo.add(lbProductName);

        txtProductName = new JTextField();
        txtProductName.setBounds(101, 46, 150, 20);
        pnInfo.add(txtProductName);
        txtProductName.setColumns(10);

        JLabel lbPrice = new JLabel("Đơn giá: ");
        lbPrice.setBounds(300, 46, 90, 16);
        pnInfo.add(lbPrice);

        txtPrice = new JTextField();
        txtPrice.setText("0");
        txtPrice.setBounds(400, 46, 170, 20);
        pnInfo.add(txtPrice);
        txtPrice.setColumns(10);

        JLabel lbCategory = new JLabel("Loại sản phẩm: ");
        lbCategory.setBounds(300, 21, 100, 16);
        pnInfo.add(lbCategory);

        cboCategory = new JComboBox<String>();
        cboCategory.setBounds(400, 19, 170, 20);
        pnInfo.add(cboCategory);

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

        JLabel lbSearchCategory = new JLabel("Loại sản phẩm: ");
        lbSearchCategory.setBounds(12, 12, 100, 16);
        pnSearch.add(lbSearchCategory);

        cboSearchCategory = new JComboBox<String>();
        cboSearchCategory.setBounds(111, 10, 150, 20);
        pnSearch.add(cboSearchCategory);

        JLabel lbKeyWord = new JLabel("Từ khóa: ");
        lbKeyWord.setBounds(279, 12, 70, 16);
        pnSearch.add(lbKeyWord);

        txtKeyWord = new JTextField();
        txtKeyWord.setBounds(345, 10, 150, 20);
        pnSearch.add(txtKeyWord);
        txtKeyWord.setColumns(10);

        btnSearch = new JButton("Tìm kiếm", searchIcon);
        btnSearch.setBounds(507, 7, 120, 26);
        customUI.getInstance().setCustomBtn(btnSearch);
        pnSearch.add(btnSearch);

        btnViewAll = new JButton("Xem tất cả");
        btnViewAll.setBounds(639, 7, 120, 26);
        pnSearch.add(btnViewAll);

        String[] cols = { "STT", "Mã sản phẩm", "Tên sản phẩm ", "Loại sản phẩm", "Giá" };
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
        loadCategoryList();
        reSizeColumnTable();
        loadProductList();

        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnSearch.addActionListener(this);
        btnViewAll.addActionListener(this);

        cboSearchCategory.addItemListener(this);
        table.addMouseListener(this);
    }

    public static void main(String[] args) {
        new pnProduct().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnAdd)) {
            if (validData()) {
                Product product = getDataInFrom();
                boolean result = ProductDAO.getInstance().insertProduct(product);
                DecimalFormat df = new DecimalFormat("#,###.##");
                if (result == true) {
                    String stt = df.format(index++);
                    int id = ProductDAO.getInstance().getProductIDByProductName(product.getName());
                    String productID = df.format(id);
                    String price = df.format(product.getPrice());
                    String categoryName = CategoryDAO.getInstance().getCategoryNameByID(product.getCategoryID());
                    String categoryNameCbo = cboSearchCategory.getSelectedItem().toString();
                    if (categoryNameCbo.equals(categoryName) == true) {
                        modelTable.addRow(new Object[] { stt, productID, product.getName(), categoryName, price });
                        modelTable.fireTableDataChanged();
                    }
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
                }
            }
        } else if (o.equals(btnUpdate)) {
            if (validData()) {
                Product product = getDataInFrom();
                int productID = product.getId();
                boolean result = ProductDAO.getInstance().updateProduct(product);
                DecimalFormat df = new DecimalFormat("#,###.##");
                if (result == true) {
                    String price = df.format(product.getPrice());
                    String categoryName = CategoryDAO.getInstance().getCategoryNameByID(product.getCategoryID());
                    for (int i = 0; i < table.getRowCount(); i++) {
                        int idProduct = Integer.parseInt((String) modelTable.getValueAt(i, 1));
                        if (idProduct == productID) {
                            System.out.println(i);
                            modelTable.setValueAt(product.getName(), i, 2);
                            modelTable.setValueAt(categoryName, i, 3);
                            modelTable.setValueAt(price, i, 4);
                            modelTable.fireTableDataChanged();
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "cập nhật sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "cập nhật sản phẩm thất bại");
                }
            }
        } else if (o.equals(btnDelete)) {
            if (!txtProductID.getText().trim().equals("")) {
                int ProductID = Integer.parseInt(txtProductID.getText().trim());
                boolean result = ProductDAO.getInstance().deleteProduct(ProductID);
                if (result == true) {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chọn 1 sản phẩm cần xóa");
            }
        } else if (o.equals(btnRefresh)) {
            txtProductID.setText("");
            txtProductName.setText("");
            cboCategory.setSelectedIndex(0);
            txtPrice.setText("0");
        } else if (o.equals(btnSearch)) {
            String categoryName = cboSearchCategory.getSelectedItem().toString();
            System.out.println(categoryName);
            String productName = txtKeyWord.getText().trim();
            if (productName.equals("")) {
                loadProductListByCategoryName(categoryName);
            } else
                loadProductListByCategoryNameAndProductName(productName, categoryName);
        } else if (o.equals(btnViewAll)) {
            cboSearchCategory.setSelectedIndex(0);
            loadProductList();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object o = e.getSource();
        if (o.equals(cboSearchCategory)) {
            String categoryName = cboSearchCategory.getSelectedItem().toString();
            loadProductListByCategoryName(categoryName);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(table)) {
            int row = table.getSelectedRow();
            txtProductID.setText(String.valueOf(table.getValueAt(row, 1)));
            txtProductName.setText(String.valueOf(modelTable.getValueAt(row, 2)));
            txtPrice.setText(String.valueOf(modelTable.getValueAt(row, 4)));
            cboCategory.setSelectedItem(String.valueOf(modelTable.getValueAt(row, 3)));
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
            customUI.getInstance().setCustomBtnHover(btnSearch);
        }
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
        String productName = txtProductName.getText().trim();
        String price = txtPrice.getText().trim().replace(",", "");
        if (!(productName.length() > 0)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống");
            return false;
        }

        if (!(price.length() > 0)) {
            double gia = Double.parseDouble(price);
            if (gia < 0) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm phải >= 0");
            } else if (price.length() < 0) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm không được để trống");
            }
            return false;
        }
        return true;
    }

    private Product getDataInFrom() {
        int productId = -1;
        if (!txtProductID.getText().trim().equals(""))
            productId = Integer.parseInt(txtProductID.getText().trim());
        String productName = txtProductName.getText().trim();
        double price = Double.parseDouble(txtPrice.getText().trim().replace(",", ""));
        String categoryName = cboCategory.getSelectedItem().toString();
        int categoryID = CategoryDAO.getInstance().getCategoryIDByCategoryName(categoryName);
        Product product = new Product(productId, categoryID, productName, price);
        return product;
    }

    private void loadCategoryList() {
        ArrayList<Category> categoryList = CategoryDAO.getInstance().getListCategory();
        for (Category item : categoryList) {
            cboCategory.addItem(item.getName());
            cboSearchCategory.addItem(item.getName());
        }
    }

    private void loadProductList() {
        ResultSet productList = ProductDAO.getInstance().getListProductCustom();
        readerDataIntoTable(productList);
    }

    private void loadProductListByCategoryName(String categoryName) {
        ResultSet productList = ProductDAO.getInstance().getListProductCustomByCategoryName(categoryName);
        readerDataIntoTable(productList);
    }

    private void loadProductListByCategoryNameAndProductName(String productName, String categoryName) {
        ResultSet productList = ProductDAO.getInstance().getListProductCustomByCategoryAndProductName(productName,
                categoryName);
        readerDataIntoTable(productList);
    }

    private void readerDataIntoTable(ResultSet rs) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        modelTable.getDataVector().removeAllElements();
        modelTable.fireTableDataChanged();
        index = 1;
        try {
            while (rs.next()) {
                String stt = df.format(index++);
                String id = df.format(rs.getInt("id"));
                String price = df.format(rs.getDouble("price"));
                modelTable.addRow(new Object[] { stt, id, rs.getString("name"), rs.getString("CategoryName"), price });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void reSizeColumnTable() {
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
    }
}
