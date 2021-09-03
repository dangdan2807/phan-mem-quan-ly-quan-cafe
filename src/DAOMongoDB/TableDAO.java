package DAOMongoDB;

import java.util.*;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.result.*;

import entityMongoDB.*;

public class TableDAO {
    private static TableDAO instance;
    private static String COLLECTION = "TableFood";
    public static int TABLE_WIDTH = 90;
    public static int TABLE_HEIGHT = 90;

    public static TableDAO getInstance() {
        if (instance == null)
            instance = new TableDAO();
        return instance;
    }

    /**
     * Lấy danh sách bàn
     * 
     * @return <code>List</code> Table
     */
    public List<Table> getListTable() {
        String jsonSelect = "{ $project: { tableID: 1, name: 1, status: 1, _id: 0 }}";
        String[] jsonData = { jsonSelect };
        List<Table> dataList = new ArrayList<Table>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Table(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public Table getTableByTableID(int tableID) {
        String jsonSelect = "{ $project: { tableID: 1, name: 1, status: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { tableID: " + tableID + " }}";
        String[] jsonData = { jsonSelect, jsonWhere };
        Table table = null;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            table = new Table(docs.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * Lấy thông tin của 1 bàn với tên bàn được chỉ định
     * 
     * @param tableName Tên bàn được chỉ định
     * @return <code>Table</code>
     */
    public Table getTableByTableName(String tableName) {
        String jsonSelect = "{ $project: { tableID: 1, name: 1, status: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { name: { $regex: '^" + tableName + "$', $options: 'si'}}}";
        String[] jsonData = { jsonSelect, jsonWhere };
        Table table = null;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            if (docs.size() > 0) {
                table = new Table(docs.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    // public void switchTable(int tableID1, int tableID2) {
    // String query = "{CALL USP_SwitchTable( ? , ? )}";
    // Object[] parameter = new Object[] { tableID1, tableID2 };
    // DataProvider.getInstance().ExecuteNonQuery(query, parameter);
    // }

    /**
     * Lấy danh sách bàn dựa trên tên bàn
     * 
     * @param tableName tên bàn cần tìm
     * @return <code>List</code> Table
     */
    public List<Table> getTableListByTableName(String tableName) {
        String jsonSelect = "{ $project: { tableID: 1, name: 1, status: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { name: {$regex: '" + tableName + "', $options: 'si'}}}";
        String[] jsonData = { jsonSelect, jsonWhere };
        List<Table> dataList = new ArrayList<Table>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Table(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Lấy danh sách bàn dựa trên tên bàn và trạng thái của bàn đó
     * 
     * @param tableName tên bàn cần tìm
     * @param status    trạng thái bàn cần tìm
     * @return <code>List</code> Table
     */
    public List<Table> getTableListByTableNameAndStatus(String tableName, String status) {
        String jsonSelect = "{ $project: { tableID: 1, name: 1, status: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { name: { $regex: '" + tableName + "', $options: 'si'}, status: { $regex: '" + status
                + "', $options: 'si'}}}";
        String[] jsonData = { jsonSelect, jsonWhere };
        List<Table> dataList = new ArrayList<Table>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Table(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Lấy danh sách bàn dựa trên trạng thái của bàn
     * 
     * @param status trạng thái bàn cần tìm
     * @return <code>List</code> Table
     */
    public List<Table> getTableListByStatus(String status) {
        String jsonSelect = "{ $project: { tableID: 1, name: 1, status: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { status: { $regex: '" + status + "', $options: 'si'}}}";
        String[] jsonData = { jsonSelect, jsonWhere };
        List<Table> dataList = new ArrayList<Table>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Table(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Lấy ID của bàn cuối cùng được thêm vào
     * 
     * @return <code>int</code> ID của bàn
     */
    public int getLastTableID() {
        String jsonSelect = "{ $project: { tableID: 1, name: 1, status: 1, _id: 0 }}";
        String jsonSort = "{ $sort: { tableID: -1 }}";
        String[] jsonData = { jsonSelect, jsonSort };
        int limitRow = 1;
        int id = -1;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonData, limitRow,
                    0);
            if (docs.size() > 0) {
                Table table = new Table(docs.get(0));
                id = table.getTableID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean insertTable(Table table) {
        int tableID = getLastTableID() + 1;
        if (tableID == 0)
            return false;
        String json = "{ tableID: " + tableID + ", name: '" + table.getName() + "', status: '" + table.getStatus()
                + "'}";
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

    public boolean updateTable(Table table) {
        String jsonWhere = "{ tableID: " + table.getTableID() + "}";
        String jsonUpdate = "{$set: { name: '" + table.getName() + "', status: '" + table.getStatus() + "'}}";
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

    public boolean deleteTable(int id) {
        String jsonWhere = "{ tableID: " + id + "}";
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
