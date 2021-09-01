package DAOMongoDB;

import com.mongodb.client.*;
import com.mongodb.client.result.*;
import com.mongodb.MongoClient;
import org.bson.Document;
import org.bson.types.*;

import java.util.*;

import connectDB.ConnectMongo;

public class DataProvider {
    private static DataProvider instance = new DataProvider();
    private static ConnectMongo db = new ConnectMongo();
    private static String databaseName = "QuanLyQuanCafe";

    public static DataProvider getInstance() {
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }

    /**
     * Thêm 1 dòng vào Collection chỉ định
     * <p>
     * ví dụ json <code>String a = "{'UserName': 'nguyenVanA'}"</code>
     * 
     * @param Collection Collection cần truy vấn
     * @param jsonData   Json dữ liệu cần truyền (viết dạng json)
     * @return result trả về ObjectId nếu thành công | null nếu thất bại
     */
    public ObjectId insertData(String collection, String jsonData) {
        MongoClient client = null;
        ObjectId result = null;
        if (jsonData == null || jsonData.equals("")) {
            jsonData = "{}";
        }
        Document query = Document.parse(jsonData);
        try {
            db.connect();
            client = ConnectMongo.getConnection();

            MongoDatabase db = client.getDatabase(databaseName);
            MongoCollection<Document> collections = db.getCollection(collection);
            collections.insertOne(query);
            result = query.getObjectId("_id");
            client.close();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return result;
    }

    /**
     * Đọc data từ csdl
     * <p>
     * ví dụ json <code>String a = "{'UserName': 'nguyenVanA'}"</code>
     * <p>
     * Nếu không có select hoặc query thì gán bằng <code>{}</code>. Ví dụ:
     * <code>String a = "{}"</code>
     * 
     * @param Collection Collection cần truy vấn
     * @param jsonSelect Những trường cần lấy ra (viết dạng json)
     * @param jsonWhere  Những điều kiện tìm đối tượng (viết dạng json)
     * @param jsonSort   Những điều kiện sắp xếp các đối tượng tìm được (viết dạng
     *                   json)
     * @param limitRow   Giới hạn số lượng dòng được lấy ra (integer) mặc định = 0
     * @param skipRow    Bỏ qua x dòng được lấy ra đầu tiên (integer) mặc
     *                   định = 0
     * @return List trả về 1 list data
     */
    public List<Document> readData(String Collection, String jsonSelect, String jsonWhere, String jsonSort,
            int limitRow, int skipRow) {
        MongoClient client = null;
        List<Document> results = null;
        if (jsonSelect == null || jsonSelect.equals("")) {
            jsonSelect = "{}";
        } else if (jsonWhere == null || jsonWhere.equals("")) {
            jsonWhere = "{}";
        } else if (jsonSort == null || jsonSort.equals("")) {
            jsonSort = "{}";
        } else if (limitRow < 0) {
            limitRow = 0;
        } else if (skipRow < 0) {
            limitRow = 0;
        }
        Document whereValue = Document.parse(jsonWhere);
        Document selectValue = Document.parse(jsonSelect);
        Document sortValue = Document.parse(jsonSort);
        try {
            db.connect();
            client = ConnectMongo.getConnection();

            MongoDatabase db = client.getDatabase(databaseName);
            MongoCollection<Document> collection = db.getCollection(Collection);
            results = new ArrayList<>();
            collection.find(whereValue).projection(selectValue).sort(sortValue).skip(skipRow).limit(limitRow)
                    .into(results);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * cập nhật 1 dòng dữ liệu trong Collection chỉ định
     * <p>
     * ví dụ json <code>String a = "{'UserName': 'nguyenVanA'}"</code>
     * <p>
     * Nếu không có select hoặc query thì gán bằng <code>{}</code>. Ví dụ:
     * <code>String a = "{}"</code>
     * 
     * @param Collection Collection cần truy vấn
     * @param jsonWhere  Những điều kiện tìm đối tượng cần cập nhật (viết dạng json)
     * @param jsonUpdate Dữ liệu cần cập nhật (viết dạng json)
     */
    public UpdateResult updateData(String Collection, String jsonWhere, String jsonUpdate) {
        MongoClient client = null;
        UpdateResult result = null;
        if (jsonUpdate == null || jsonUpdate.equals("")) {
            jsonUpdate = "{}";
        } else if (jsonWhere == null || jsonWhere.equals("")) {
            jsonWhere = "{}";
        }
        Document query = Document.parse(jsonWhere);
        Document update = Document.parse(jsonUpdate);
        try {
            db.connect();
            client = ConnectMongo.getConnection();

            MongoDatabase db = client.getDatabase(databaseName);
            MongoCollection<Document> collection = db.getCollection(Collection);
            result = collection.updateOne(query, update);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Xóa 1 dòng dữ liệu trong Collection chỉ định
     * <p>
     * ví dụ json <code>String a = "{'UserName': 'nguyenVanA'}"</code>
     * <p>
     * Nếu không có select hoặc query thì gán bằng <code>{}</code>. Ví dụ:
     * <code>String a = "{}"</code>
     * 
     * @param collection Collection cần truy vấn
     * @param jsonWhere  Những điều kiện tìm đối tượng (viết dạng json)
     */
    public DeleteResult deleteData(String collection, String jsonWhere) {
        MongoClient client = null;
        DeleteResult result = null;
        if (jsonWhere == null || jsonWhere.equals("")) {
            jsonWhere = "{}";
        }
        Document query = Document.parse(jsonWhere);
        try {
            db.connect();
            client = ConnectMongo.getConnection();

            MongoDatabase db = client.getDatabase(databaseName);
            MongoCollection<Document> col = db.getCollection(collection);
            result = col.deleteOne(query);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
