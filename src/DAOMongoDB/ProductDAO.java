package DAOMongoDB;

import java.util.*;

import org.bson.Document;
import com.mongodb.client.result.*;

import entityMongoDB.Product;

public class ProductDAO {
    private static ProductDAO instance = new ProductDAO();
    private static String COLLECTION = "Product";

    public static ProductDAO getInstance() {
        if (instance == null)
            instance = new ProductDAO();
        return instance;
    }

    // public List<Product> getListProductByCategoryName(String categoryName) {
    // ArrayList<Product> dataList = new ArrayList<Product>();
    // String query = "{CALL USP_getListProductByCategoryName ( ? )}";
    // Object[] parameter = new Object[] { categoryName };
    // ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
    // try {
    // while (rs.next()) {
    // dataList.add(new Product(rs));
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return dataList;
    // }

    public Product getProductByProductName(String productName) {
        String jsonSelect = "{ productID: 1, name: 1, idCategory: 1, price: 1 }";
        String jsonWhere = "{name: {$regex: '^" + productName + "$', $options: 'si'}}";
        Product product = null;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
            product = new Product(docs.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public int getProductCountByCategoryID(int categoryID) {
        String jsonSelect = "{ productID: 1, name: 1, idCategory: 1, price: 1 }";
        String jsonWhere = "{idCategory: " + categoryID + "}";
        int product = 0;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
            product = docs.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public int getLastProductID() {
        String jsonSelect = "{ productID: 1, name: 1, idCategory: 1, price: 1 }";
        String jsonSort = "{ productID: -1 }";
        int limitRow = 1;
        int id = -1;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, "{}", jsonSort, limitRow,
                    0);
            if (docs.size() > 0) {
                Product product = new Product(docs.get(0));
                id = product.getProductID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    // public ArrayList<Product> getListProductByProductName(String productName) {
    // ArrayList<Product> dataList = new ArrayList<Product>();
    // String query = "{CALL USP_getListProductByProductName ( ? )}";
    // Object[] parameter = new Object[] { productName };
    // ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
    // try {
    // while (rs.next()) {
    // dataList.add(new Product(rs));
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return dataList;
    // }

    // public ResultSet getListProductCustom() {
    // String query = "{CALL USP_getListProduct}";
    // ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, null);
    // return rs;
    // }

    // public ResultSet searchProductByCategoryNameAndProductName(String
    // productName, String categoryName) {
    // String query = "{CALL USP_searchProductByCategoryNameAndProductName ( ? , ?
    // )}";
    // Object[] parameter = new Object[] { productName, categoryName };
    // ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
    // return rs;
    // }

    // public ResultSet searchProductByCategoryName(String categoryName) {
    // String query = "{CALL USP_searchProductByCategoryName ( ? )}";
    // Object[] parameter = new Object[] { categoryName };
    // ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
    // return rs;
    // }

    // public ResultSet searchProductByProductName(String productName) {
    // String query = "{CALL USP_searchProductByProductName ( ? )}";
    // Object[] parameter = new Object[] { productName };
    // ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
    // return rs;
    // }

    // public boolean insertProduct(Product product) {
    // String query = "INSERT INTO dbo.Product (name, idCategory, Price) VALUES ( ?
    // , ? , ? )";
    // Object[] parameter = new Object[] { product.getName(),
    // product.getCategoryID(), product.getPrice() };
    // int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
    // return result > 0;
    // }

    // public boolean updateProduct(Product product) {
    // String query = "Update dbo.Product set name = ? , idCategory = ? , price = ?
    // Where id = ?";
    // Object[] parameter = new Object[] { product.getName(),
    // product.getCategoryID(), product.getPrice(),
    // product.getId() };
    // int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
    // return result > 0;
    // }

    // public boolean deleteProduct(int id) {
    // String query = "delete from dbo.Product Where id = ?";
    // Object[] parameter = new Object[] { id };
    // int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
    // return result > 0;
    // }
}
