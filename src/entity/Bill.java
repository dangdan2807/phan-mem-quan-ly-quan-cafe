package entity;

import java.sql.*;

public class Bill {
    private int id, idTable, status, discount;
    private Date dateCheckIn, dateCheckOut;

    public int getId() {
        return id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        if (discount < 0)
            discount = 0;
        this.discount = discount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Bill(int id, int idTable, Date dateCheckIn, Date dateCheckOut, int status, int discount) {
        this.id = id;
        this.idTable = idTable;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.status = status;
        this.discount = discount;
    }

    public Bill(ResultSet rs) throws SQLException {
        this(rs.getInt("id"), rs.getInt("idTable"), rs.getDate("dateCheckIn"), rs.getDate("dateCheckOut"),
                rs.getInt("status"), rs.getInt("Discount"));
    }

}
