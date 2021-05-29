package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillInfo {
    private int id, billID, productID, count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    

    public BillInfo(int id, int billID, int productID, int count) {
        this.id = id;
        this.billID = billID;
        this.productID = productID;
        this.count = count;
    }

    public BillInfo(ResultSet rs) throws SQLException {
        this(rs.getInt("id"), rs.getInt("idBill"), rs.getInt("idProduct"), rs.getInt("count"));
    }
}
