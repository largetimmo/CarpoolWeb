package dao;

import core.VehicleOwnerInfo;

import java.sql.*;

/**
 * Created by admin on 2017/8/15.
 */
public class CarpoolOwnerInfoDAO extends AbstractDAO{
    public static VehicleOwnerInfo getUserInfo(int uid){
        VehicleOwnerInfo vehicleOwnerInfo = null;
        try {
            Class.forName(MYSQL_CLASS_NAME);
            Connection connection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            String sqlquery = "select * from user_carpool_info where uid = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,Integer.toString(uid));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //exist
                String nickname = resultSet.getString(2);
                int userlevel = resultSet.getInt(3);
                String vehicle = resultSet.getString(4);
                vehicleOwnerInfo = new VehicleOwnerInfo(uid,userlevel,nickname,vehicle);
            }
            preparedStatement.close();;
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleOwnerInfo;
    }
    public static Boolean verifyCarpoolOwner(String uid){
        //验证用户是否已提交车辆信息
        try{
            Class.forName(MYSQL_CLASS_NAME);
            Connection connection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_carpool_info WHERE uid = ?");
            preparedStatement.setString(1,uid);
            Boolean a = preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            if(a){
                return true;
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
