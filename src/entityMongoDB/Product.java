package entityMongoDB;

import org.bson.Document;

public class Product {
    private int productID, categoryID;
    private String name;
    private double price;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int id) {
        this.productID = id;
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

    private static Double convertToDouble(Object object) {
        return Double.parseDouble(object.toString());
    }

    public Product(int productID, int categoryID, String name, double price) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.name = name;
        this.price = price;
    }

    public Product(Document doc) {
        this(doc.getInteger("productID"), doc.getInteger("idCategory"), doc.getString("name"),
                convertToDouble(doc.get("price")));
    }

    @Override
    public String toString() {
        return "Product [productID=" + productID + ", name=" + name + ", price=" + price
                + ", categoryID=" + categoryID + "]";
    }
}
