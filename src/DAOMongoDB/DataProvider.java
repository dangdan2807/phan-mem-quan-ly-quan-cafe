package DAOMongoDB;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
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
    public ObjectId insertDocument(String collection, String jsonData) {
        MongoClient client = null;
        ObjectId result = null;
        if (jsonData.matches("^\\{.+\\}$")) {
            try {
                db.connect();
                client = ConnectMongo.getConnection();

                MongoDatabase db = client.getDatabase(databaseName);
                MongoCollection<Document> collections = db.getCollection(collection);
                Document doc = Document.parse(jsonData);
                collections.insertOne(doc);
                result = doc.getObjectId("_id");
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    public List<Document> readDocuments(String Collection, String[] jsonData, int limitRow, int skipRow) {
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
     * @param jsonData   Những điều kiện tìm đối tượng cần cập nhật (viết dạng json)
     *                   <p>
     *                   ví dụ: { < String tìm kiếm >, < String update > }
     * @param options    các hàm hỗ trợ truy vấn
     * @return Document
     */
    public Document updateDocument(String Collection, String[] jsonData, FindOneAndUpdateOptions options) {
        Document result = null;
        List<Document> filter = new ArrayList<Document>();
        for (String json : jsonData) {
            if (json.matches("^\\{.+\\}$")) {
                Document doc = Document.parse(json);
                filter.add(doc);
            }
        }
        MongoClient client = null;
        if (filter.size() >= 2) {
            try {
                db.connect();
                client = ConnectMongo.getConnection();
                MongoDatabase db = client.getDatabase(databaseName);
                MongoCollection<Document> collection = db.getCollection(Collection);
                if (options != null) {
                    result = collection.findOneAndUpdate(filter.get(0), filter.get(1), options);
                } else {
                    result = collection.findOneAndUpdate(filter.get(0), filter.get(1));
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
     * @param jsonFilter Những điều kiện tìm đối tượng (viết dạng json)
     * @param options    các hàm hỗ trợ truy vấn
     * @return Document
     */
    public Document deleteDocument(String collection, String jsonFilter, FindOneAndDeleteOptions options) {
        MongoClient client = null;
        Document resultDoc = null;
        if (jsonFilter.matches("^\\{.+\\}$")) {
            try {
                db.connect();
                client = ConnectMongo.getConnection();
                MongoDatabase db = client.getDatabase(databaseName);
                MongoCollection<Document> col = db.getCollection(collection);
                Document filterDoc = Document.parse(jsonFilter);
                if (options != null) {
                    resultDoc = col.findOneAndDelete(filterDoc, options);
                }
                resultDoc = col.findOneAndDelete(filterDoc);
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultDoc;
    }
}
