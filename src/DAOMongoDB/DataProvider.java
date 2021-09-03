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
     * @param jsonData   json dùng để truy vấn (dạng json)
     * @param limitRow   Giới hạn số lượng dòng được lấy ra (integer) mặc định = 0
     * @param skipRow    Bỏ qua x dòng được lấy ra đầu tiên (integer) mặc định = 0
     * @return <code>List</code> Account
     */
    public List<Document> readData(String Collection, String[] jsonData, int limitRow, int skipRow) {
        MongoClient client = null;
        List<Document> resultDocs = new ArrayList<Document>();
        List<Document> docs = new ArrayList<Document>();
        for (String json : jsonData) {
            if (json.matches("^\\{.+\\}$")) {
                Document doc = Document.parse(json);
                docs.add(doc);
            }
        }
        if (limitRow >= 1) {
            Document limitValue = Document.parse("{ $limit: " + limitRow + "}");
            docs.add(limitValue);
        }
        if (skipRow >= 1) {
            Document skipValue = Document.parse("{ $skip: " + skipRow + "}");
            docs.add(skipValue);
        }
        if (docs.size() > 0) {
            try {
                db.connect();
                client = ConnectMongo.getConnection();
                MongoDatabase db = client.getDatabase(databaseName);
                MongoCollection<Document> collection = db.getCollection(Collection);
                AggregateIterable<Document> results = collection.aggregate(docs);
                for (Document doc : results) {
                    resultDocs.add(doc);
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultDocs;
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
