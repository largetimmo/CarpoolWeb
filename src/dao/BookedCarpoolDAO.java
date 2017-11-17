package dao;

import pojo.BookedCarpoolDetail;
import util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chenjunhao on 2017/10/17.
 */
public class BookedCarpoolDAO extends AbstractDAO {
    private BookedCarpoolDAO() {

    }

    private static BookedCarpoolDAO instance = new BookedCarpoolDAO();

    public static BookedCarpoolDAO getInstance() {
        return instance;
    }

    public boolean BookCarpool(String carpool_id, String user_id, String seat) {
        boolean flag = false;
        try {
            Connection connection = ConnectionPool.getInstance().getBookedCarpoolConnection();
            String sqlquery = "INSERT INTO BOOKED_CARPOOL(uid,id,seat) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, carpool_id);
            preparedStatement.setString(3, seat);
            preparedStatement.execute();
            preparedStatement.close();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public BookedCarpoolDetail getBookedCarpoolDetailAsPassenger(String ref) {
        String sqlquery = "SELECT departure,destination,date,nickname,USER_REG.uid AS uid FROM BOOKED_CARPOOL INNER JOIN CARPOOL ON BOOKED_CARPOOL.id = CARPOOL.id INNER JOIN USER_REG ON CARPOOL.uid = USER_REG.uid WHERE BOOKED_CARPOOL.booking_ref = ?";
        BookedCarpoolDetail bookedCarpoolDetail = null;
        try {
            Connection connection = ConnectionPool.getInstance().getBookedCarpoolConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1, ref);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String dep = resultSet.getString(resultSet.findColumn("departure"));
                String des = resultSet.getString(resultSet.findColumn("destination"));
                String date = resultSet.getString(resultSet.findColumn("date"));
                String nickname = resultSet.getString(resultSet.findColumn("nickname"));
                String cuid = resultSet.getString(resultSet.findColumn("uid"));
                ArrayList<Pair<String, String>> userlist = new ArrayList<>();
                userlist.add(new Pair<String, String>(nickname, cuid));
                bookedCarpoolDetail = new BookedCarpoolDetail(dep, des, date, userlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedCarpoolDetail;
    }

    //TODO:getBookedCarpoolDetailAsDriver-->BookedCarpoolDetail
    //TODO:getBookedCarpoolUserList-->ArrayList<Pair<String,String>>
    @Override
    protected Object parseCursor(ResultSet resultSet) {
        return null;
    }
}
