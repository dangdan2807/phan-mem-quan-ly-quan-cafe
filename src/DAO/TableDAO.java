package DAO;

import java.sql.*;
import java.util.*;

import entity.Table;

public class TableDAO {
    private static TableDAO instance;
    public static int TABLE_WIDTH = 90;
    public static int TABLE_HEIGHT = 90;

    public static TableDAO getInstance() {
        if (instance == null)
            instance = new TableDAO();
        return instance;
    }

    public ArrayList<Table> getListTable() {
        String query = "{CALL USP_getListTable}";
        ArrayList<Table> dataList = new ArrayList<Table>();
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, null);
        try {
            while (rs.next()) {
                dataList.add(new Table(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
