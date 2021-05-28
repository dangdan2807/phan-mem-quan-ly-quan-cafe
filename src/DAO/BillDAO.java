package DAO;

import java.sql.*;
import java.util.ArrayList;

import entity.Bill;

public class BillDAO {
    private static BillDAO instance = new BillDAO();

    public static BillDAO getInstance() {
        if (instance == null)
            instance = new BillDAO();
        return instance;
    }

    /// <summary>
    /// success: return bill ID
    /// fail: return -1
    /// </summary>
    /// <param name="id"></param>
    /// <returns></returns>
    public int getUncheckBillByTableID(int tableID) {
        String query = "{CALL USP_getUncheckBillByTableID (?) }";
        Object[] parameter = new Object[] { tableID };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        ArrayList<Bill> dataList = new ArrayList<Bill>();
        try {
            while (rs.next()) {
                dataList.add(new Bill(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (dataList.size() > 0) {
            Bill bill = dataList.get(0);
            return bill.getId();
        }
        return -1;
    }
}
