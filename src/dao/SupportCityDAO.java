package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chenjunhao on 2017/11/9.
 */
public class SupportCityDAO extends AbstractDAO {
    private static SupportCityDAO instance = new SupportCityDAO();
    public static SupportCityDAO getInstance(){
        return instance;
    }
    private final ArrayList<String> supportcity;
    private SupportCityDAO(){
        update();
        supportcity = new ArrayList<>();
    }


    public ArrayList<String> getSupportCity(){
        return supportcity;
    }
    private void update(){
        new Thread(() -> {
            while (true){
                Connection connection = ConnectionPool.getInstance().getGeneralConnection();
                String sqlquery = "SELECT * FROM SUPPORTCITY";
                synchronized (supportcity) {
                    supportcity.clear();
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
                        ResultSet rs = preparedStatement.executeQuery();
                        while (rs.next()) {
                            supportcity.add(rs.getString(1));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
