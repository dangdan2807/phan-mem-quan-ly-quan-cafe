package DAOMongoDB;

import java.util.*;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.result.*;

import entityMongoDB.Category;

public class CategoryDAO {
    private static CategoryDAO instance = new CategoryDAO();
    private static String COLLECTION = "ProductCategory";

    public static CategoryDAO getInstance() {
        if (instance == null)
            instance = new CategoryDAO();
        return instance;
    }

    public List<Category> getListCategory() {
        String jsonSelect = "{ categoryID: 1, name:1 }";
        List<Category> dataList = new ArrayList<Category>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, "{}", "{}", 0, 0);
            for (Document doc : docs) {
                dataList.add(new Category(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public List<Category> getListCategoryByName(String categoryName) {
        String jsonSelect = "{ categoryID: 1, name: 1 }";
        String jsonWhere = "{ name: { $regex: '" + categoryName + "', $options: 'i'}}";
        List<Category> dataList = new ArrayList<Category>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
            for (Document doc : docs) {
                dataList.add(new Category(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public int getCategoryIDByCategoryName(String categoryName) {
        String jsonSelect = "{ categoryID: 1, name: 1 }";
        String jsonWhere = "{ name: { $regex: '^" + categoryName + "$', $options: 'si' }}";
        int categoryID = -1;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
            if (docs.size() > 0) {
                Category category = new Category(docs.get(0));
                categoryID = category.getCategoryID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryID;
    }

    public String getCategoryNameByID(int categoryID) {
        String jsonSelect = "{ categoryID: 1, name: 1 }";
        String jsonWhere = "{ categoryID: " + categoryID + "}";
        String categoryName = "";
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
            if (docs.size() > 0) {
                Category category = new Category(docs.get(0));
                categoryName = category.getName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryName;
    }

    public int getLastCategoryID() {
        String jsonSelect = "{ categoryID: 1, name: 1 }";
        String jsonSort = "{ categoryID: -1 }";
        int limitRow = 1;
        int categoryID = -1;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, "{}", jsonSort, limitRow,
                    0);
            if (docs.size() > 0) {
                Category table = new Category(docs.get(0));
                categoryID = table.getCategoryID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryID;
    }

    public boolean insertCategory(Category category) {
        int categoryID = getLastCategoryID() + 1;
        if (categoryID == 0)
            return false;
        String json = "{ categoryID: " + categoryID + ", name: '" + category.getName() + "'}";
        ObjectId id = null;
        boolean result = false;
        try {
            id = DataProvider.getInstance().insertData(COLLECTION, json);
            if (id != null) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateCategory(Category category) {
        String jsonWhere = "{ categoryID: " + category.getCategoryID() + "}";
        String jsonUpdate = "{$set: { name: '" + category.getName() + "'}}";
        UpdateResult data = null;
        boolean result = false;
        try {
            data = DataProvider.getInstance().updateData(COLLECTION, jsonWhere, jsonUpdate);
            if (data.getModifiedCount() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteCategory(int id) {
        String jsonWhere = "{ categoryID: " + id + "}";
        boolean result = false;
        try {
            DeleteResult data = DataProvider.getInstance().deleteData(COLLECTION, jsonWhere);
            if (data.getDeletedCount() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
