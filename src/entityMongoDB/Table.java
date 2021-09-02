package entityMongoDB;

import org.bson.Document;

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

    public Table(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public Table(String name) {
        this.name = name;
        this.status = "Trống";
    }
    
    public Table(Document doc) {
        this(doc.getInteger("tableID"), doc.getString("name"), doc.getString("status"));
    }

}
