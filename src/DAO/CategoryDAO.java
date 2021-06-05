package DAO;

import java.sql.*;
import java.util.ArrayList;

import entity.Category;

public class CategoryDAO {
    private static CategoryDAO instance = new CategoryDAO();

    public static CategoryDAO getInstance() {
        if (instance == null)
            instance = new CategoryDAO();
        return instance;
    }

    public ArrayList<Category> getListCategory() {
        ArrayList<Category> dataList = new ArrayList<Category>();
        String query = "SELECT * FROM dbo.ProductCategory";
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, null);
        try {
            while (rs.next()) {
                dataList.add(new Category(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public int getCategoryIDByCategoryName(String categoryName) {
        String query = "SELECT t.id FROM dbo.ProductCategory t WHERE t.name = ?";
        Object[] parameter = new Object[] { categoryName };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        int data = -1;
        try {
            rs.next();
            data = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getCategoryNameByID(int categoryID) {
        String query = "SELECT t.name FROM dbo.ProductCategory t WHERE t.id = ?";
        Object[] parameter = new Object[] { categoryID };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        String data = "";
        try {
            rs.next();
            data = rs.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
