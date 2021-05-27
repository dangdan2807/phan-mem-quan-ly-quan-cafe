package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {
    private int id;
    private String name, status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Table(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Table(ResultSet rs) throws SQLException {
        this(rs.getInt("id"), rs.getString("name"), rs.getString("status"));
    }
}
