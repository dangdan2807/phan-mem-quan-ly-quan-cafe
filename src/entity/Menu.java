package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu {
    private String productName;
    private int count;
    private double price, totalPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Menu(String productName, int count, double price, double totalPrice) {
        this.productName = productName;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Menu(ResultSet rs) throws SQLException {
        this(rs.getString("name"), rs.getInt("count"), rs.getDouble("price"), rs.getDouble("totalPrice"));
    }
}
