package entityMongoDB;

import org.bson.Document;

public class Product {
    private int id, categoryID;
    private String name;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(int id, int categoryID, String name, double price) {
        this.id = id;
        this.categoryID = categoryID;
        this.name = name;
        this.price = price;
    }

    public Product(Document doc) {
        this(doc.getInteger("id"), doc.getInteger("idCategory"), doc.getString("name"), doc.getDouble("price"));
    }

}
