package DAOMongoDB;

import java.util.*;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.model.*;
import entityMongoDB.*;

public class AccountDAO {
    private static AccountDAO instance = new AccountDAO();
    private static String COLLECTION = "Account";

    public static AccountDAO getInstance() {
        if (instance == null)
            instance = new AccountDAO();
        return instance;
    }

    public List<Account> getListAccount() {
        String jsonSelect = "{ $project: { UserName: 1, Password: 1, DisplayName: 1, Type: 1, _id: 0 }}";
        String[] jsonData = { jsonSelect };
        List<Account> dataList = new ArrayList<Account>();
        try {
            List<Document> docs = DataProvider.getInstance().readDocuments(COLLECTION, jsonData, 0, 0);
            for (Document doc : docs) {
                dataList.add(new Account(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public Account getAccountByUsername(String Username) {
        String jsonSelect = "{ $project: { UserName: 1, Password: 1, DisplayName: 1, Type: 1, _id: 0 }}";
        String jsonWhere = "{ $match: { UserName: { $regex: '^" + Username + "$', $options: 'm'}}}";
        // String jsonWhere = "{ $match: { UserName: '" + Username + "'}}";
        String[] jsonData = { jsonSelect, jsonWhere };
        Account table = null;
        try {
            List<Document> docs = DataProvider.getInstance().readDocuments(COLLECTION, jsonData, 0, 0);
            if (docs.size() > 0) {
                table = new Account(docs.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
}
