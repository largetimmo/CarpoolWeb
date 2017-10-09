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
    public static List<CarpoolInfo> searchAvaliableVehicle(String from, String to, int passenger, String date){
        List<CarpoolInfo> carpoolInfoList = new ArrayList<>();
        try {
            Class.forName(MYSQL_CLASS_NAME);
            Connection connection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            String sqlquery = "select * from carpool where departure = ? AND  destination = ? AND remainseat >= ? AND date LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,from);
            preparedStatement.setString(2,to);
            preparedStatement.setString(3,Integer.toString(passenger));
            preparedStatement.setString(4,date+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                VehicleOwnerInfo vehicleOwnerInfo = CarpoolOwnerInfoDAO.getUserInfo(resultSet.getInt(2));
                DateTime dateTime = new DateTime(resultSet.getString(3));
                int price = resultSet.getInt(4);
                int capacity = resultSet.getInt(5);
                String remainseat = resultSet.getString(8);
                carpoolInfoList.add(new CarpoolInfo(vehicleOwnerInfo,id,price,capacity,dateTime,remainseat));
            }
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carpoolInfoList;
    }
    public static boolean storageCarpoolInfo(String departure, String destination,String capacity,String price,String date, String userid){
        try{
            Class.forName(MYSQL_CLASS_NAME);
            Connection connection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            String sqlquery = "INSERT INTO carpool(uid,date,price,capacity,departure,destination,remainseat) VALUES(?,?,?,?,?,?,?)";
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
            connection.close();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static List<BookedCarpoolInfo> searchAllBookedCarpool(int uid){
        return searchAllBookedCarpool(Integer.toString(uid));
    }
    public static List<BookedCarpoolInfo> searchAllBookedCarpool(String uid){

        List<BookedCarpoolInfo> infoList = new ArrayList<>();
        try {
            Class.forName(MYSQL_CLASS_NAME);
            Connection connection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            String sql = "SELECT * FROM carpool_book WHERE uid = ? ORDER BY booking_ref DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String refnumber = resultSet.getString(1);
                String id = resultSet.getString(3);
                String seat = resultSet.getString(4);
                CarpoolInfo carpoolInfo = getCarpoolInfo(id);
                BookedCarpoolInfo bookedCarpoolInfo = new BookedCarpoolInfo(carpoolInfo,refnumber,seat);
                infoList.add(bookedCarpoolInfo);
            }
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return infoList;
    }
    public static boolean bookCarpool(String uid, String id,String seat_string){
        /*
            1.获取剩余座位数
            2.判断如果超过剩余座位，则不进行预订
            3.更新剩余座位
            4.在carpool_book中加入详情
            5.
         */
        int seats = Integer.parseInt(seat_string);
        try {
            Class.forName(MYSQL_CLASS_NAME);
            Connection connection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            String sqlquery = "SELECT remainseat FROM carpool where id = ?";
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
                sqlquery = "INSERT INTO carpool_book(uid,id,seat) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(sqlquery);
                preparedStatement.setString(1,uid);
                preparedStatement.setString(2,id);
                preparedStatement.setString(3,seat_string);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
                return true;
            }
            connection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static CarpoolInfo getCarpoolInfo(String id){
        CarpoolInfo carpoolInfo = null;
        try {
            Class.forName(MYSQL_CLASS_NAME);
            Connection connection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            String sqlquery = "SELECT * FROM carpool WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                VehicleOwnerInfo vehicleOwnerInfo = CarpoolOwnerInfoDAO.getUserInfo(resultSet.getInt(2));
                DateTime dateTime = new DateTime(resultSet.getString(3));
                int price = resultSet.getInt(4);
                int capacity = resultSet.getInt(5);
                String from = resultSet.getString(6);
                String to = resultSet.getString(7);
                String remainseat = resultSet.getString(8);
                carpoolInfo = new CarpoolInfo(vehicleOwnerInfo,price,capacity,dateTime,remainseat,from,to);
            }
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return carpoolInfo;
    }
}
