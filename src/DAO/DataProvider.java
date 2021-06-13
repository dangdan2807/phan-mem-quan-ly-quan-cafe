package DAO;

import java.sql.*;
import connectDB.ConnectDB;

public class DataProvider {
    private static DataProvider instance = new DataProvider();
    private static ConnectDB db = ConnectDB.getInstance();

    public static DataProvider getInstance() {
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }

    public ResultSet ExecuteQuery(String query, Object[] parameter) {
        PreparedStatement stmt = null;
        ResultSet dataList = null;
        Connection con = null;
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
            dataList = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public int ExecuteNonQuery(String query, Object[] parameter) {
        int data = 0;
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareStatement(query);
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
        Object data = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareStatement(query);
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
                data = rs.getObject(1);
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
}
