package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by chenjunhao on 2017/10/17.
 */
public class BookedCarpoolDAO extends AbstractDAO {
    private BookedCarpoolDAO(){

    }
    private static BookedCarpoolDAO instance = new BookedCarpoolDAO();
    public static BookedCarpoolDAO getInstance(){return instance;}

    public boolean BookCarpool(String carpool_id,String user_id,String seat){
        boolean flag = false;
        try {
            Connection connection = ConnectionPool.getInstance().getBookedCarpoolConnection();
            String sqlquery = "INSERT INTO BOOKED_CARPOOL(uid,id,seat) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,user_id);
            preparedStatement.setString(2,carpool_id);
            preparedStatement.setString(3,seat);
            preparedStatement.execute();
            preparedStatement.close();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
