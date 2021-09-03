package DAOMongoDB;

import java.util.*;

import org.bson.Document;

import entityMongoDB.Product;

public class ProductDAO {
    private static ProductDAO instance = new ProductDAO();
    private static String COLLECTION = "Product";

    public static ProductDAO getInstance() {
        if (instance == null)
            instance = new ProductDAO();
        return instance;
    }

    public List<Product> getListProductByCategoryName(String categoryName) {
        int categoryID = CategoryDAO.getInstance().getCategoryIDByCategoryName(categoryName);
        String jsonSelect = "{ $project: { productID: 1, name: 1, idCategory: 1, price: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { idCategory: " + categoryID + "}}";
        String[] jsonData = { jsonSelect, jsonWhere };
        List<Product> dataList = new ArrayList<Product>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Product(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public Product getProductByProductName(String productName) {
        String jsonSelect = "{ $project: { productID: 1, name: 1, idCategory: 1, price: 1, _id: 0 }}";
        String jsonWhere = "{ $match: {name: {$regex: '^" + productName + "$', $options: 'si'}}}";
        String[] jsonData = { jsonSelect, jsonWhere };
        Product product = null;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            if (docs.size() > 0) {
                product = new Product(docs.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public int getProductCountByCategoryID(int categoryID) {
        String jsonSelect = "{ $project: { productID: 1, idCategory: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { idCategory: " + categoryID + "}}";
        String jsonCount = "{ $count: 'count'}";
        String[] jsonData = { jsonSelect, jsonWhere, jsonCount };
        int product = 0;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            product = docs.get(0).getInteger("count");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public int getLastProductID() {
        String jsonSelect = "{ $project: { productID: 1, name: 1, idCategory: 1, price: 1, _id: 0 }}";
        String jsonSort = "{ $sort: { productID: -1 }}";
        String[] jsonData = { jsonSelect, jsonSort };
        int limitRow = 1;
        int id = -1;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, limitRow, 0);
            if (docs.size() > 0) {
                Product product = new Product(docs.get(0));
                id = product.getProductID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Product> getListProductByProductName(String productName) {
        String jsonSelect = "{ $project: { productID: 1, name: 1, idCategory: 1, price: 1, _id: 0 }}";
        String jsonWhere = "{ $match: {name: {$regex: '" + productName + "', $options: 'si'}}}";
        String[] jsonData = { jsonSelect, jsonWhere };
        List<Product> dataList = new ArrayList<Product>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Product(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public List<Product> getListProduct() {
        String jsonSelect = "{ $project: { productID: 1, name: 1, idCategory: 1, price: 1, _id: 0 }}";
        String[] jsonData = { jsonSelect };
        List<Product> dataList = new ArrayList<Product>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Product(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public List<Product> searchProductByCategoryNameAndProductName(String productName, String categoryName) {
        int categoryID = CategoryDAO.getInstance().getCategoryIDByCategoryName(categoryName);
        String jsonSelect = "{ $project: { productID: 1, name: 1, idCategory: 1, price: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { name: { $regex: '" + productName + "', $options: 'si'}, idCategory: "
                + categoryID + "}}";
        System.out.println("categoryID: " + categoryID);
        String[] jsonData = { jsonSelect, jsonWhere };
        List<Product> dataList = new ArrayList<Product>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Product(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public List<Product> searchProductByCategoryName(String categoryName) {
        int categoryID = CategoryDAO.getInstance().getCategoryIDByCategoryName(categoryName);
        String jsonSelect = "{ $project: { productID: 1, name: 1, idCategory: 1, price: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { idCategory: " + categoryID + "}}";
        String[] jsonData = { jsonSelect, jsonWhere };
        List<Product> dataList = new ArrayList<Product>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Product(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

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
