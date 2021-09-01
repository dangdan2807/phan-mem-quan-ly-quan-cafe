package DAOMongoDB;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.result.*;

import entityMongoDB.*;

public class AccountDAO {
    private static AccountDAO instance = new AccountDAO();
    private static String COLLECTION = "Account";

    public static AccountDAO getInstance() {
        if (instance == null)
            instance = new AccountDAO();
        return instance;
    }

    /**
     * Lấy danh sách tài khoản từ database
     * 
     * @return <code>List</code> Account
     */
    public List<Account> getListAccount() {
        String select = "{ 'UserName': 1, 'DisplayName': 1, 'Type': 1, '_id': 0 }";
        String where = "{}";
        String sort = "{}";
        List<Account> dataList = new ArrayList<Account>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, select, where, sort, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Account(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * (Hàm chưa hoàn thiện)
     * <p>
     * Hàm này kiểm tra có tồn tại tài khoản, mật khẩu được truyền nào
     * <p>
     * trả về <code>true</code> nếu tồn tại
     * <p>
     * trả về <code>false</code> nếu không tồn tại
     * 
     * @param username tài khoản đăng nhập
     * @param password mật khẩu đăng nhập
     * @return boolean
     */
    public boolean Login(String username, String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        byte[] hashData = md.digest();
        String hashPass = "";
        for (byte item : hashData) {
            hashPass += item;
        }

        int count = 0;
        // vùng cần bổ sung
        String where = "{UserName: " + username + ", Password: " + hashPass + "}";
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, "{}", where, "{}", 0, 0);
            count = docs.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count > 0;
    }

    /**
     * Lấy ra 1 <code>Account</code> dựa trên username được truyền vào
     * 
     * @param username tài khoản cần tìm thông tin
     * @return Account
     */
    public Account getAccountByUsername(String username) {
        String select = "{UserName: 1, DisplayName: 1, Type: 1}";
        String where = "{UserName: " + username + "}";
        Account account = null;
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, select, where, "{}", 0, 0);
            account = new Account(docs.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    /**
     * Lấy ra danh sách <code>Account</code> dựa trên username
     * 
     * @param username tài khoản cần tìm thông tin
     * @return <code>List<code> Account
     */
    public List<Account> searchAccountListByUsername(String username) {
        String jsonSelect = "{ UserName: 1, DisplayName: 1, Type: 1, _id: 1 }";
        String jsonWhere = "{ UserName: {$regex: '" + username + "', $options: 'i'}}";
        List<Account> dataList = new ArrayList<Account>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
            for (Document doc : docs) {
                dataList.add(new Account(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Lấy danh sách tài khoản dựa trên chức vụ (<code>Type</code>)
     * <p>
     * <code>Type = 1</code> là quản lý
     * <p>
     * <code>Type = 0</code> là nhân viên
     * 
     * @param type loại chức vụ cần tìm
     * @return <code>List</code> Account
     */
    public List<Account> searchAccountListByType(int type) {
        String jsonSelect = "{ UserName: 1, DisplayName: 1, Type: 1, _id: 1 }";
        String jsonWhere = "{ Type: " + type + " }";
        List<Account> dataList = new ArrayList<Account>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
            for (Document doc : docs) {
                dataList.add(new Account(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Lấy danh sách tài khoản dựa trên tài khoản (<code>username</code>) và chức vụ
     * (<code>Type</code>)
     * 
     * @param username Tài khoản cần tìm
     * @param type     Chức vụ cần tìm
     * @return <code>List</code> Account
     */
    public List<Account> searchAccountListByUsernameAndType(String username, int type) {
        String jsonSelect = "{ UserName: 1, DisplayName: 1, Type: 1, _id: 1 }";
        String jsonWhere = "{ UserName: {$regex: '" + username + "', $options: 'i'} ,Type: " + type + " }";
        List<Account> dataList = new ArrayList<Account>();
        try {
            List<Document> docs = DataProvider.getInstance().readData(COLLECTION, jsonSelect, jsonWhere, "{}", 0, 0);
            for (Document doc : docs) {
                dataList.add(new Account(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Thêm 1 tài khoản vào csdl
     * <p>
     * Trả về <code>true</code> nếu thêm thành công
     * <p>
     * Trả về <code>false</code> nếu thêm thất bại
     * 
     * @param account là <code>Account</code> cần thêm
     * @return boolean
     */
    public boolean insertAccount(Account account) {
        String json = "{ UserName: '" + account.getUsername() + "', Password: '" + account.getPassword()
                + "', DisplayName: '" + account.getDisplayName() + "', Type: " + account.getType() + " }";
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

    // public boolean updateAccountInfo(String username, String displayName, String
    // password, String newPassword) {
    // // String query = "{CALL UPS_updateAccount ( ? , ? , ? , ? )}";
    // // Object[] parameter = new Object[] { username, displayName, password,
    // // newPassword };
    // // int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
    // String collection = "Account";
    // Document query = new Document().append("UserName", username);
    // Document update = new Document().append("$addToSet",
    // new Document().append("Password", password)
    // .append("DisplayName", displayName)
    // .append("Type", newPassword)
    // );
    // ObjectId id = DataProvider.getInstance().updateData(collection, query,
    // update);
    // boolean result = false;
    // if (id != null) {
    // result = true;
    // }
    // return result;
    // }

    /**
     * Cập nhật thông tin 1 tài khoản vào csdl
     * <p>
     * Trả về <code>true</code> nếu cập nhật thành công
     * <p>
     * Trả về <code>false</code> nếu cập nhật thất bại
     * 
     * @param account là <code>Account</code> cần cập nhật
     * @return boolean
     */
    public boolean updateAccount(Account account) {
        String jsonWhere = "{UserName: '" + account.getUsername() + "'}";
        String jsonUpdate = "{'$set': { Password: '" + account.getPassword() + "', DisplayName: '"
                + account.getDisplayName() + "', Type: " + account.getType() + "}}";
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

    /**
     * Xóa 1 tài khoản trong csdl
     * <p>
     * Trả về <code>true</code> nếu xóa thành công
     * <p>
     * Trả về <code>false</code> nếu xóa thất bại
     * 
     * @param username là tài khoản cần xóa
     * @return boolean
     */
    public boolean deleteAccount(String username) {
        String jsonWhere = "{UserName: '" + username + "'}";
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

    /**
     * Đặt lại mật khẩu của tài khoản được chọn
     * <p>
     * Trả về <code>true</code> nếu thành công
     * <p>
     * Trả về <code>false</code> nếu thất bại
     * 
     * @param username là tài khoản cần đặt lại mật khẩu
     * @return boolean
     */
    public boolean resetPassword(String username) {
        // password: 123456
        String newPassword = "-3110-365773-7089-85-6686-3287-1415-12062";
        String jsonWhere = "{UserName: '" + username + "'}";
        String jsonUpdate = "{$set: {Password: '" + newPassword + "'}}";
        boolean result = false;
        try {
            UpdateResult data = DataProvider.getInstance().updateData(COLLECTION, jsonWhere, jsonUpdate);
            if (data.getModifiedCount() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
