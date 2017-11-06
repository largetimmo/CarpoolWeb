package dao;

import core.BookedCarpoolInfo;
import core.CarpoolInfo;
import core.DateTime;
import core.VehicleOwnerInfo;

import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/15.
 */
public class CarpoolDAO extends AbstractDAO{
    private static CarpoolDAO instance = new CarpoolDAO();
    public static CarpoolDAO getInstance(){
        return instance;
    }
    private CarpoolDAO(){}
    public List<CarpoolInfo> searchAvaliableVehicle(String from, String to, int passenger, String date){
        List<CarpoolInfo> carpoolInfoList = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.getInstance().getCarpoolConnection();
            String sqlquery = "select * from CARPOOL where departure = ? AND  destination = ? AND remainseat >= ? AND date LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,from);
            preparedStatement.setString(2,to);
            preparedStatement.setString(3,Integer.toString(passenger));
            preparedStatement.setString(4,date+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                carpoolInfoList.add(parseData(resultSet));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carpoolInfoList;
    }
    public boolean storageCarpoolInfo(String departure, String destination,String capacity,String price,String date, String userid){
        try{
            Connection connection = ConnectionPool.getInstance().getCarpoolConnection();
            String sqlquery = "INSERT INTO CARPOOL(uid,date,price,capacity,departure,destination,remainseat) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,userid);
            preparedStatement.setString(2,date);
            preparedStatement.setString(3,price);
            preparedStatement.setString(4,capacity);
            preparedStatement.setString(5,departure);
            preparedStatement.setString(6,destination);
            preparedStatement.setString(7,capacity);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<BookedCarpoolInfo> searchAllBookedCarpool(int uid){
        return searchAllBookedCarpool(Integer.toString(uid));
    }
    public List<BookedCarpoolInfo> searchAllBookedCarpool(String uid){
        List<BookedCarpoolInfo> infoList = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.getInstance().getCarpoolConnection();
            String sql = "SELECT * FROM BOOKED_CARPOOL WHERE uid = ? ORDER BY booking_ref DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String refnumber = resultSet.getString(1);
                String id = resultSet.getString(resultSet.findColumn("id"));
                String seat = resultSet.getString(4);
                CarpoolInfo carpoolInfo = getCarpoolInfo(id);
                BookedCarpoolInfo bookedCarpoolInfo = new BookedCarpoolInfo(carpoolInfo,refnumber,seat);
                infoList.add(bookedCarpoolInfo);
            }
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return infoList;
    }
    public boolean bookCarpool(String uid, String id,String seat_string){
        /*
            1.获取剩余座位数
            2.判断如果超过剩余座位，则不进行预订
            3.更新剩余座位
            4.在carpool_book中加入详情
            5.
         */
        boolean flag = false;
        int seats = Integer.parseInt(seat_string);
        try {
            Connection connection = ConnectionPool.getInstance().getCarpoolConnection();
            String sqlquery = "SELECT remainseat FROM CARPOOL where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            int remainseat = 0;
            if (resultSet.next()){
                remainseat = resultSet.getInt(1);
            }
            preparedStatement.close();
            if(remainseat>=seats){
                sqlquery = "UPDATE carpool SET remainseat = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sqlquery);
                preparedStatement.setInt(1,remainseat-seats);
                preparedStatement.setString(2,id);
                preparedStatement.execute();
                preparedStatement.close();
                if (BookedCarpoolDAO.getInstance().BookCarpool(uid,id,seat_string)){
                    flag = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public CarpoolInfo getCarpoolInfo(String id){
        CarpoolInfo carpoolInfo = null;
        try {
            Connection connection = ConnectionPool.getInstance().getCarpoolConnection();
            String sqlquery = "SELECT * FROM CARPOOL WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               carpoolInfo = parseData(resultSet);
            }
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return carpoolInfo;
    }
    private CarpoolInfo parseData(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(resultSet.findColumn("id"));
        VehicleOwnerInfo vehicleOwnerInfo = CarpoolOwnerInfoDAO.getInstance().getUserInfo(resultSet.getInt(resultSet.findColumn("uid")));
        DateTime dateTime = new DateTime(resultSet.getString(resultSet.findColumn("date")));
        int price = resultSet.getInt(resultSet.findColumn("price"));
        int capacity = resultSet.getInt(resultSet.findColumn("capacity"));
        String remainseat = resultSet.getString(resultSet.findColumn("remainseat"));
        String departure = resultSet.getString(resultSet.findColumn("departure"));
        String dest = resultSet.getString(resultSet.findColumn("destination"));
        return new CarpoolInfo(vehicleOwnerInfo,id,price,capacity,dateTime,remainseat,departure,dest);
    }
    public ArrayList<CarpoolInfo> getPostedCarpool(String uid){
        String sqlquery = "SELECT * FROM CARPOOL WHERE uid = ?";
        ArrayList<CarpoolInfo> list = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.getInstance().getCarpoolConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(parseData(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
