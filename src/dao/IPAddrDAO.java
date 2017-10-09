package dao;

import java.sql.*;

/**
 * Created by admin on 2017/8/22.
 */
public class IPAddrDAO extends AbstractDAO {
    public static boolean checkBlockIP(String ipaddr) throws ClassNotFoundException, SQLException {
        Class.forName(MYSQL_CLASS_NAME);
        Connection connection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
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
