package dao;

import pojo.*;
import util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    public List<BookedCarpoolInfo> getCarpoolInfoAsPassenger(String uid){
        LinkedList<BookedCarpoolInfo> allinfos = new LinkedList<>();
        try {
            Connection connection = ConnectionPool.getInstance().getBookedCarpoolConnection();
            String sqlquery = "SELECT * FROM BOOKED_CARPOOL INNER JOIN CARPOOL ON BOOKED_CARPOOL.id = CARPOOL.id INNER JOIN (SELECT uid, nickname FROM USER_REG) AS T ON CARPOOL.uid = T.uid  WHERE BOOKED_CARPOOL.uid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String bookingref = resultSet.getString(resultSet.findColumn("booking_ref"));
                int price = resultSet.getInt(resultSet.findColumn("price"));
                String departure = resultSet.getString(resultSet.findColumn("departure"));
                String destination = resultSet.getString(resultSet.findColumn("destination"));
                int remainseat = resultSet.getInt(resultSet.findColumn("remainseat"));
                String date_str = resultSet.getString(resultSet.findColumn("date"));
                DateTime date = new DateTime(date_str);
                int seat = resultSet.getInt(resultSet.findColumn("seat"));
                int driver_id = resultSet.getInt(resultSet.findColumn("CARPOOL.uid"));
                VehicleOwnerInfo vehicleOwnerInfo = VehicleOwnerInfoDAO.getInstance().getUserInfo(driver_id);
                CarpoolInfo carpoolInfo = new CarpoolInfo();
                carpoolInfo.setDateTime(date);
                carpoolInfo.setPrice(price);
                carpoolInfo.setTo(destination);
                carpoolInfo.setFrom(departure);
                carpoolInfo.setUser(vehicleOwnerInfo);
                carpoolInfo.setRemainseat(remainseat);
                BookedCarpoolInfo bookedCarpoolInfo = new BookedCarpoolInfo();
                bookedCarpoolInfo.setCarpoolInfo(carpoolInfo);
                bookedCarpoolInfo.setReference_number(bookingref);
                bookedCarpoolInfo.setSeats(seat);
                allinfos.add(bookedCarpoolInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allinfos;
    }
    //TODO:getBookedCarpoolDetailAsDriver-->BookedCarpoolDetail
    //TODO:getBookedCarpoolUserList-->ArrayList<Pair<String,String>>
    @Override
    protected Object parseCursor(ResultSet resultSet) {
        return null;
    }
}
