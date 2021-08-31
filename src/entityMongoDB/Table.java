package entityMongoDB;

import org.bson.Document;

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

    public Table(Document doc) {
        this(doc.getInteger("id"), doc.getString("name"), doc.getString("status"));
    }
}
