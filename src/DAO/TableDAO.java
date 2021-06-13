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
        String query = "SELECT * FROM dbo.TableFood";
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

    public Table getTableByTableName(String tableName) {
        String query = "select * FROM dbo.TableFood t WHERE t.name = ?";
        Object[] parameter = new Object[] { tableName };
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

    public void switchTable(int tableID1, int tableID2) {
        String query = "{CALL USP_SwitchTable( ? , ? )}";
        Object[] parameter = new Object[] { tableID1, tableID2 };
        DataProvider.getInstance().ExecuteNonQuery(query, parameter);
    }

    public ArrayList<Table> getTableListByTableName(String tableName) {
        String query = "select * FROM dbo.TableFood t WHERE dbo.fuConvertToUnsign(t.name) like dbo.fuConvertToUnsign( ? )";
        Object[] parameter = new Object[] { "%" + tableName + "%" };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        ArrayList<Table> dataList = new ArrayList<Table>();
        try {
            while (rs.next()) {
                dataList.add(new Table(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<Table> getTableListByTableNameAndStatus(String tableName, String status) {
        String query = "select * FROM dbo.TableFood t WHERE dbo.fuConvertToUnsign(t.name) like dbo.fuConvertToUnsign( ? ) and status = ?";
        Object[] parameter = new Object[] { "%" + tableName + "%", status };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        ArrayList<Table> dataList = new ArrayList<Table>();
        try {
            while (rs.next()) {
                dataList.add(new Table(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<Table> getTableListByStatus(String status) {
        String query = "select * FROM dbo.TableFood t WHERE status = ?";
        Object[] parameter = new Object[] { status };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        ArrayList<Table> dataList = new ArrayList<Table>();
        try {
            while (rs.next()) {
                dataList.add(new Table(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public int getLastCategoryID() {
        int data = -1;
        String query = "SELECT * FROM dbo.TableFood";
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, null);
        try {
            rs.last();
            data = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean insertTable(Table table) {
        String query = "INSERT INTO dbo.TableFood (name, status) VALUES ( ? , ? )";
        Object[] parameter = new Object[] { table.getName(), table.getStatus() };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean updateTable(Table table) {
        String query = "Update dbo.TableFood set name = ? , status = ? where id = ?";
        Object[] parameter = new Object[] { table.getName(), table.getStatus(), table.getId() };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean deleteTable(int id) {
        String query = "delete from dbo.TableFood Where id = ?";
        Object[] parameter = new Object[] { id };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }
}
