package entityMongoDB;

import org.bson.Document;

public class Category {
    private int categoryID;
    private String name;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public Category(int categoryID) {
        this.categoryID = categoryID;
        this.name = "";
    }

    public Category(String name) {
        this.categoryID = -1;
        this.name = name;
    }

    public Category(Document doc) {
        this(doc.getInteger("categoryID"), doc.getString("name"));
    }
}
