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
        String query = "{CALL USP_getUncheckBillByTableID ( ? )}";
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

    public void insertBill(int tableID) {
        Object[] parameter = new Object[] { tableID };
        String query = "{CALL USP_insertBill ( ? )}";
        DataProvider.getInstance().ExecuteNonQuery(query, parameter);
    }

    public int getMaxIDBill() {
        try {
            String query = "Select MAX(id) FROM dbo.Bill";
            int data = (int) DataProvider.getInstance().ExecuteScalar(query, null);
            return data;
        } catch (Exception e) {
            return 1;
        }
    }

    public void checkOut(int billID, int discount) {
        String query = "UPDATE dbo.Bill SET Status = 1, discount = ? WHERE id = ?";
        Object[] param = new Object[] { discount, billID };
        DataProvider.getInstance().ExecuteNonQuery(query, param);
    }
}
