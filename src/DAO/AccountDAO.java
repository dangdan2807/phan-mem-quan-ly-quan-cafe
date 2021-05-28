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

    public ArrayList<Account> getAccountList() {
        String query = "{CALL USP_getAccountList}";
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
        String query = "{CALL USP_Login (? , ?)}";
        Object[] parameter = new Object[] { username, password };
        count = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return count > 0;
    }
}
