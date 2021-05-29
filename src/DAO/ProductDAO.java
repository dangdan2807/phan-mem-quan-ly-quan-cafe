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
}
