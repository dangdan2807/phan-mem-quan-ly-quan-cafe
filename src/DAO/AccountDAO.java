package DAO;

import java.sql.*;
import java.util.*;

import entity.Account;

public class AccountDAO {
    private static AccountDAO instance = new AccountDAO();

    public static AccountDAO getInstance() {
        if (instance == null)
            instance = new AccountDAO();
        return instance;
    }

    public ArrayList<Account> getListAccount() {
        String query = "SELECT username , displayName , type FROM dbo.Account";
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, null);
        ArrayList<Account> dataList = new ArrayList<Account>();
        try {
            while (rs.next()) {
                dataList.add(new Account(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public boolean Login(String username, String password) {
        int count = 0;
        String query = "{CALL USP_Login ( ? , ? )}";
        Object[] parameter = new Object[] { username, password };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        try {
            rs.next();
            count = rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0;
    }

    public Account getAccountByUsername(String username) {
        String query = "Select * from dbo.Account where username = ?";
        Object[] parameter = new Object[] { username };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        Account account = null;
        try {
            rs.next();
            account = new Account(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public ArrayList<Account> searchAccountListByUsername(String username) {
        String query = "Select * from dbo.Account where dbo.fuConvertToUnsign(username) like dbo.fuConvertToUnsign( ? )";
        Object[] parameter = new Object[] { "%" + username + "%"};
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        ArrayList<Account> dataList = new ArrayList<Account>();
        try {
            while (rs.next()) {
                dataList.add(new Account(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<Account> searchAccountListByType(int type) {
        String query = "Select * from dbo.Account where type = ?";
        Object[] parameter = new Object[] { type };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        ArrayList<Account> dataList = new ArrayList<Account>();
        try {
            while (rs.next()) {
                dataList.add(new Account(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public ArrayList<Account> searchAccountListByUsernameAndType(String username, int type) {
        String query = "Select * from dbo.Account where dbo.fuConvertToUnsign(username) like dbo.fuConvertToUnsign(?) and type = ?";
        Object[] parameter = new Object[] { "%" + username + "%", type };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        ArrayList<Account> dataList = new ArrayList<Account>();
        try {
            while (rs.next()) {
                dataList.add(new Account(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public boolean insertAccount(Account account) {
        String query = "INSERT INTO dbo.Account (username , password , displayName , type) VALUES ( ? , ? , ? , ? )";
        Object[] parameter = new Object[] { account.getUsername(), account.getPassword(), account.getDisplayName(),
                account.getType() };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean updateAccountInfo(String username, String displayName, String password, String newPassword) {
        String query = "{CALL UPS_updateAccount ( ? , ? , ? , ? )}";
        Object[] parameter = new Object[] { username, displayName, password, newPassword };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean updateAccount(Account account) {
        String query = "update dbo.Account SET displayName = ? , type = ? WHERE username = ?";
        Object[] parameter = new Object[] { account.getDisplayName(), account.getType(), account.getUsername() };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean deleteAccount(String username) {
        String query = "delete from dbo.Account Where username = ?";
        Object[] parameter = new Object[] { username };
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }
}
