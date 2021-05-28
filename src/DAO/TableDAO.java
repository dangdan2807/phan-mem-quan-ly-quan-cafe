package DAO;

import java.sql.*;
import java.util.*;

import connectDB.ConnectDB;
import entity.Table;

public class TableDAO {
    private static TableDAO instance;
    private static ConnectDB db = ConnectDB.getInstance();
    public static int TABLE_WIDTH = 90;
    public static int TABLE_HEIGHT = 90;

    public static TableDAO getInstance() {
        if (instance == null)
            instance = new TableDAO();
        return instance;
    }

    public ArrayList<Table> ExecuteQuery(String query, Object[] parameter) {
        ArrayList<Table> dataList = new ArrayList<Table>();
        CallableStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareCall(query);
            if (parameter != null) {
                String[] listParams = query.split(" ");
                int i = 1;
                for (String item : listParams) {
                    if (item.contains("?")) {
                        stmt.setObject(i, parameter[i - 1]);
                        i++;
                    }
                }
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                dataList.add(new Table(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    // dùng insert, update, delete, ...
    // trả về số dòng thành công
    public int ExecuteNonQuery(String query, Object[] parameter) {
        int data = 0;
        CallableStatement stmt = null;
        Connection con = null;
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareCall(query);
            if (parameter != null) {
                String[] listParams = query.split(" ");
                int i = 1;
                for (String item : listParams) {
                    if (item.contains("?")) {
                        stmt.setObject(i, parameter[i - 1]);
                        i++;
                    }
                }
            }
            data = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    // dùng để đếm, ...
    // trả về cột đầu tiên của dùng đầu tiên của kết quả
    public Object ExecuteScalar(String query, Object[] parameter) {
        Object data = "";
        ResultSet rs = null;
        CallableStatement stmt = null;
        Connection con = null;
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareCall(query);
            if (parameter != null) {
                String[] listParams = query.split(" ");
                int i = 1;
                for (String item : listParams) {
                    if (item.contains("?")) {
                        stmt.setObject(i, parameter[i - 1]);
                        i++;
                    }
                }
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                data = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<Table> getTableList() {
        String query = "{CALL USP_getTableList}";
        return ExecuteQuery(query, null);
    }

}
