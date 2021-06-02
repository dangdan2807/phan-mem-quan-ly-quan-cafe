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
}
