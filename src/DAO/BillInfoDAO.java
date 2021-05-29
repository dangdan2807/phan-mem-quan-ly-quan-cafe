package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.BillInfo;

public class BillInfoDAO {
    private static BillInfoDAO instance = new BillInfoDAO();

    public static BillInfoDAO getInstance() {
        if (instance == null)
            instance = new BillInfoDAO();
        return instance;
    }

    public ArrayList<BillInfo> getListBillInfo(int idBill) {
        ArrayList<BillInfo> dataList = new ArrayList<BillInfo>();
        Object[] parameter = new Object[] { idBill };
        String query = "{CALL USP_getListBillInfo ( ? )}";
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        try {
            while (rs.next()) {
                dataList.add(new BillInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public void insertBillInfo(int billID, int productID, int count) {
        Object[] parameter = new Object[] { billID, productID, count };
        String query = "{CALL USP_insertBillInfo ( ? , ? , ? )}";
        DataProvider.getInstance().ExecuteNonQuery(query, parameter);
    }
}
