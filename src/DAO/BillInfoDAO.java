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
        String query = "SELECT * FROM dbo.BillInfo bi WHERE bi.id = ?";
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

    public boolean insertBillInfo(int billID, int productID, int count) {
        Object[] parameter = new Object[] { billID, productID, count };
        String query = "{CALL USP_insertBillInfo ( ? , ? , ? )}";
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }

    public boolean deleteBillInfoByProductID(int productID) {
        Object[] parameter = new Object[] { productID };
        String query = "DELETE FROM dbo.BillInfo WHERE idProduct = ?";
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        return result > 0;
    }
}
