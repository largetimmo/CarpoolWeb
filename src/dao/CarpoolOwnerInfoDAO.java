package dao;

import core.VehicleOwnerInfo;

import java.sql.*;

/**
 * Created by admin on 2017/8/15.
 */
public class CarpoolOwnerInfoDAO extends AbstractDAO{
    private CarpoolOwnerInfoDAO(){

    }
    private static CarpoolOwnerInfoDAO instance = new CarpoolOwnerInfoDAO();
    public  static CarpoolOwnerInfoDAO getInstance(){
        return instance;
    }
    public VehicleOwnerInfo getUserInfo(int uid){
        //获取用户的车辆信息
        VehicleOwnerInfo vehicleOwnerInfo = null;
        try {
            Connection connection = ConnectionPool.getInstance().getCarpoolOwnerConnection();
            String sqlquery = "SELECT * FROM VEHICLE_OWNER INNER JOIN USER_REG ON VEHICLE_OWNER.uid = USER_REG.uid WHERE VEHICLE_OWNER.uid = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,Integer.toString(uid));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //exist
                String nickname = resultSet.getString(resultSet.findColumn("nickname"));
                int userlevel = resultSet.getInt(resultSet.findColumn("userlevel"));
                String vehicle = resultSet.getString(resultSet.findColumn("vehicle"));
                vehicleOwnerInfo = new VehicleOwnerInfo(uid,userlevel,nickname,vehicle);
            }
            preparedStatement.close();;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleOwnerInfo;
    }
    public boolean verifyCarpoolOwner(String uid){
        //验证用户是否已提交车辆信息
        try{
            Connection connection = ConnectionPool.getInstance().getCarpoolOwnerConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM VEHICLE_OWNER WHERE uid = ?");
            preparedStatement.setString(1,uid);
            boolean a = preparedStatement.execute();
            preparedStatement.close();
            if(a){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean AddVehicleOwner(VehicleOwnerInfo vehicleOwnerInfo){
        boolean flag = false;
        try {
            Connection connection = ConnectionPool.getInstance().getCarpoolOwnerConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO VEHICLE_OWNER(uid,vehicle) VALUES (?,?)");
            preparedStatement.setInt(1,vehicleOwnerInfo.getUid());
            preparedStatement.setString(2,vehicleOwnerInfo.getNickname());
            preparedStatement.execute();
            preparedStatement.close();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
