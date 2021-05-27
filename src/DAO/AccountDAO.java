package DAO;

import java.sql.*;
import java.util.*;

import connectDB.ConnectDB;
import entity.Account;

public class AccountDAO {
    private static AccountDAO instance = new AccountDAO();
    private static ConnectDB db = ConnectDB.getInstance();

    public static AccountDAO getInstance() {
        if (instance == null)
            instance = new AccountDAO();
        return instance;
    }

    public ArrayList<Account> ExecuteQuery(String query, Object[] parameter) {
        ArrayList<Account> dataList = new ArrayList<Account>();
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
                dataList.add(new Account(rs));
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
        ResultSet rs = null;
        Connection con = null;
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareCall(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
            rs.last();
            data = rs.getRow();
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

    public ArrayList<Account> getAccountList() {
        String query = "{CALL USP_getAccountList}";
        return ExecuteQuery(query, null);
    }

    public boolean Login(String username, String password) {
        int count = 0;
        String query = "{CALL USP_Login (? , ?)}";
        count = (int) ExecuteNonQuery(query, new Object[] { username, password });
        return count > 0;
    }
}
