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

    public ArrayList<Category> getListCategoryByName(String categoryName) {
        ArrayList<Category> dataList = new ArrayList<Category>();
        String query = "SELECT * FROM dbo.ProductCategory WHERE name = ?";
        Object[] parameter = new Object[] { categoryName };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
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
        String query = "SELECT t.id FROM dbo.ProductCategory t WHERE dbo.fuConvertToUnsign(t.name) like dbo.fuConvertToUnsign( ? )";
        Object[] parameter = new Object[] { "%" + categoryName + "%" };
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

    public int getLastCategoryID() {
        int data = -1;
        String query = "SELECT * FROM dbo.ProductCategory";
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, null);
        try {
            rs.last();
            data = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public int getProductCount(int categoryID) {
        int count = 0;
        String query = "SELECT count(*) as ProductCount FROM dbo.Product p where p.idCategory = ?";
        Object[] parameter = new Object[] { categoryID };
        count = (int) DataProvider.getInstance().ExecuteScalar(query, parameter);
        return count;
    }

    public boolean insertCategory(Category category) {
        String query = "INSERT INTO dbo.ProductCategory (name) VALUES ( ? )";
        Object[] parameter = new Object[] { category.getName() };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean updateProduct(Category category) {
        String query = "Update dbo.ProductCategory set name = ? where id = ?";
        Object[] parameter = new Object[] { category.getName(), category.getId() };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean deleteProduct(int id) {
        String query = "delete from dbo.ProductCategory Where id = ?";
        Object[] parameter = new Object[] { id };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }
}
