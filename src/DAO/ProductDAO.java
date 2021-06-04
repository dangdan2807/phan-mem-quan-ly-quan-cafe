package DAO;

import java.sql.*;
import java.util.ArrayList;

import entity.Product;

public class ProductDAO {
    private static ProductDAO instance = new ProductDAO();

    public static ProductDAO getInstance() {
        if (instance == null)
            instance = new ProductDAO();
        return instance;
    }

    public ArrayList<Product> getListProductByCategoryName(String categoryName) {
        ArrayList<Product> dataList = new ArrayList<Product>();
        String query = "{CALL USP_getListProductByCategoryName ( ? )}";
        Object[] parameter = new Object[] { categoryName };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        try {
            while (rs.next()) {
                dataList.add(new Product(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public Product getProductByProductName(String productName) {
        Product data = null;
        String query = "{CALL USP_getProductByProductName ( ? )}";
        Object[] parameter = new Object[] { productName };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        try {
            rs.next();
            data = new Product(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public int getProductIDByProductName(String productName) {
        int data = -1;
        String query = "SELECT id FROM dbo.Product WHERE name = ?";
        Object[] parameter = new Object[] { productName };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        try {
            rs.next();
            data = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Product> getListProductByProductName(String productName) {
        ArrayList<Product> dataList = new ArrayList<Product>();
        String query = "{CALL USP_getListProductByProductName ( ? )}";
        Object[] parameter = new Object[] { productName };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        try {
            while (rs.next()) {
                dataList.add(new Product(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ResultSet getListProductCustom() {
        String query = "{CALL USP_getListProduct}";
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, null);
        return rs;
    }

    public ResultSet getListProductCustomByCategoryAndProductName(String productName, String categoryName) {
        String query = "{CALL USP_getListProductCustomByCategoryAndProductName ( ? , ? )}";
        Object[] parameter = new Object[] { productName, categoryName };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        return rs;
    }

    public ResultSet getListProductCustomByCategoryName(String categoryName) {
        String query = "{CALL USP_getListProductCustomByCategoryName ( ? )}";
        Object[] parameter = new Object[] { categoryName };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        return rs;
    }

    public boolean insertProduct(Product product) {
        String query = "INSERT INTO dbo.Product (name, idCategory, Price) VALUES ( ? , ? , ? )";
        Object[] parameter = new Object[] { product.getName(), product.getCategoryID(), product.getPrice() };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean updateProduct(Product product) {
        String query = "Update dbo.Product set name = ? , idCategory = ? , price = ? Where id = ?";
        Object[] parameter = new Object[] { product.getName(), product.getCategoryID(), product.getPrice(),
                product.getId() };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean deleteProduct(int id) {
        String query = "delete from dbo.Product Where id = ?";
        Object[] parameter = new Object[] { id };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }
}
