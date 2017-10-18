package dao;

import java.sql.*;

/**
 * Created by admin on 2017/8/22.
 */
public class IPAddrDAO extends AbstractDAO {
    private IPAddrDAO(){

    }
    private static IPAddrDAO instance = new IPAddrDAO();
    public static IPAddrDAO getInstance(){
        return instance;
    }
    public boolean checkBlockIP(String ipaddr) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getGeneralConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM blocked_ip WHERE ipaddr = ?");
        preparedStatement.setString(1,ipaddr);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            preparedStatement.close();
            connection.close();
            return true;
        }
        preparedStatement.close();
        connection.close();
        return false;
    }
}
