package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Product(int id, int categoryID, String name, double price) {
        this.productID = id;
        this.categoryID = categoryID;
        this.name = name;
        this.price = price;
    }

    public Product(ResultSet rs) throws SQLException {
        this(rs.getInt("productID"), rs.getInt("idCategory"), rs.getString("name"), rs.getDouble("price"));
    }

}
