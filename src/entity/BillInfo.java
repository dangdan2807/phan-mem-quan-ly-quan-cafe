package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillInfo {
    private int id, idBill, idProduct, count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BillInfo(int id, int idBill, int idProduct, int count) {
        this.id = id;
        this.idBill = idBill;
        this.idProduct = idProduct;
        this.count = count;
    }

    public BillInfo(ResultSet rs) throws SQLException {
        this(rs.getInt("id"), rs.getInt("idBill"), rs.getInt("idProduct"), rs.getInt("count"));
    }
}
