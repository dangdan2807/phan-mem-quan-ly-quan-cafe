package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {
    private int tableID;
    private String name, status;

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Table(int tableID, String name, String status) {
        this.tableID = tableID;
        this.name = name;
        this.status = status;
    }

    public Table(ResultSet rs) throws SQLException {
        this(rs.getInt("id"), rs.getString("name"), rs.getString("status"));
    }
}
