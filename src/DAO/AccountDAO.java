package DAO;

import java.sql.*;
import java.util.*;

import connectDB.ConnectDB;
import entity.Account;

public class AccountDAO {
    private static AccountDAO instance = new AccountDAO();
    private static ConnectDB db = ConnectDB.getInstance();

    public static AccountDAO getInstance() {
        return instance;
    }

    public ArrayList<Account> getListAccounts() {
        ArrayList<Account> accountList = new ArrayList<Account>();
        CallableStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        String query = "{CALL UDP_getAccountList}";
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareCall(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Account tk = new Account(rs);
                accountList.add(tk);
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
        return accountList;
    }

    public static boolean Login(String username, String password) {
        int count = 0;
        CallableStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        String query = "EXEC UDP_Login ? , ?";
        try {
            db.connect();
            con = ConnectDB.getConnection();
            stmt = con.prepareCall(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            // đến số dòng được trả về
            rs.last();
            count = rs.getRow();
            rs.beforeFirst();
            // cách khác
            // ResultSetMetaData rsMetaData = rs.getMetaData();
            // count = rsMetaData.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count > 0;
    }
}
