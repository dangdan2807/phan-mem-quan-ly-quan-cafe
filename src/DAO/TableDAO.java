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

    public Table getTableByTableID(int tableID) {
        String query = "select * FROM dbo.TableFood t WHERE t.id = ?";
        Object[] parameter = new Object[] { tableID };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        Table table = null;
        try {
            while (rs.next()) {
                table = new Table(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }
}
