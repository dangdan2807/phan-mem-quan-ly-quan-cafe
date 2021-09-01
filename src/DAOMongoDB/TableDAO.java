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
        String jsonSelect = "{ 'tableID': 1, 'name': 1, 'status': 1}";
        List<Table> dataList = new ArrayList<Table>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, "{}", "{}", 0, 0);
            for (Document doc : docs) {
                dataList.add(new Table(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public Table getTableByTableID(int tableID) {
        String jsonSelect = "{ 'tableID': 1, 'name': 1, 'status': 1}";
        String jsonWhere = "{ tableID: " + tableID + " }";
        Table table = null;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
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
        String jsonSelect = "{ 'tableID': 1, 'name': 1, 'status': 1}";
        String jsonWhere = "{name: {$regex: '^" + tableName + "$', $options: 'si'}}";
        Table table = null;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
            table = new Table(docs.get(0));
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
        String jsonSelect = "{ 'tableID': 1, 'name': 1, 'status': 1}";
        String jsonWhere = "{name: {$regex: '" + tableName + "', $options: 'si'}}";
        List<Table> dataList = new ArrayList<Table>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
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
        String jsonSelect = "{ tableID: 1, name: 1, status: 1}";
        String jsonWhere = "{name: {$regex: '" + tableName + "', $options: 'i'}, status: {$regex: '" + status
                + "', $options: 'i'}}";
        List<Table> dataList = new ArrayList<Table>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
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
        String jsonSelect = "{ tableID: 1, name: 1, status: 1}";
        String jsonWhere = "{status: {$regex: '" + status + "', $options: 'i'}}";
        List<Table> dataList = new ArrayList<Table>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
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
    public int getLastCategoryID() {
        String jsonSelect = "{ tableID: 1, name: 1, status: 1 }";
        String jsonSort = "{ tableID: -1 }";
        int limitRow = 1;
        int id = -1;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, "{}", jsonSort, limitRow,
                    0);
            if (docs.size() > 0) {
                Table table = new Table(docs.get(0));
                id = table.gettableID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean insertTable(Table table) {
        int tableID = getLastCategoryID() + 1;
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
        String jsonWhere = "{ tableID: " + table.gettableID() + "}";
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

    public boolean deleteTable(int tableID) {
        String jsonWhere = "{ tableID: " + tableID + "}";
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
