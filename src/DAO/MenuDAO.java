package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Menu;

public class MenuDAO {
    private static MenuDAO instance = new MenuDAO();

    public static MenuDAO getInstance() {
        if (instance == null)
            instance = new MenuDAO();
        return instance;
    }

    public ArrayList<Menu> getListMenuByTableID(int tableID) {
        ArrayList<Menu> dataList = new ArrayList<Menu>();
        String query = "{CALL USP_getBillInfoListByTableID (?)}";
        Object[] parameter = new Object[] { tableID };
        ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, parameter);
        try {
            while (rs.next()) {
                dataList.add(new Menu(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
