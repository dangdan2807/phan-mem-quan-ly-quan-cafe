package connectDB;

import com.mongodb.MongoClient;

public class ConnectMongo {
    public static MongoClient client = null;
    private static ConnectMongo instance = new ConnectMongo();

    public static ConnectMongo getInstance() {
        if (instance == null)
            instance = new ConnectMongo();
        return instance;
    }

    public void connect() {
        String ip = "localhost";
        int port = 27017;
        client = new MongoClient(ip, port);
    }

    public void disconnect() {
        if (client != null) {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static MongoClient getConnection() {
        return client;
    }
}
