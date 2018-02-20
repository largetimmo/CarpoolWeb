package dao;

import pojo.UserReg;
import util.Md5encrypt;
import util.VerifyInput;

import java.sql.*;

/**
 * Created by admin on 2017/8/21.
 */
public class UserManagementDAO extends AbstractDAO{


    private UserManagementDAO(){

    }
    private static UserManagementDAO instance = new UserManagementDAO();
    public static UserManagementDAO getInstance() {
        return instance;
    }
    public int Login(String username, String password){
        /*
            log in with username and password
            return uid if username and password corrent
            else return -1
            return -2 for internal error
         */
        try {
            Connection connection = ConnectionPool.getInstance().getUserManagementConnection();
            String sqlquery = "SELECT uid FROM USER_REG WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String uid_string = resultSet.getString(1);
                int uid = Integer.parseInt(uid_string);
                preparedStatement.close();
                return uid;
            }else {
                preparedStatement.close();
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -2;
    }

    public int Register(UserReg userReg){
        //Todo: move verify part to another Java class
        /*
            Register new user
            return 1 for success
            return 0 for unknown errors
            return -1 for username exist
            return -2 for username contain invalid characters
            return -6 for invalid username length
            return -3 for password contain invalid characters
            return -4 for invalid password length
            return -5 for invalid email
            return -7 for invalid nickname
         */

        /*
            Checking invalid character in email
        */
        if(!VerifyInput.verifyEmail(userReg.getEmail())){
            return -5;
        }
        /*
        checking invalid character in username
         */
        if (!VerifyInput.verifyUNPS(userReg.getUsername())){
            return -2;
        }
        /*
        checking invalid character in password
         */
        if (!VerifyInput.verifyUNPS(userReg.getPassword())){
            return -3;
        }
        /*
        checking invalid character in nickname
         */
        if(!VerifyInput.verifyUNPS(userReg.getNickname())){
            return -7;
        }
        /*
        checking username length
         */
        if(userReg.getUsername().length()>=30 || userReg.getUsername().length()<6){
            return -6;
        }

        /*
        checking password length
         */
        if(userReg.getPassword().length()!=32){
            System.out.println("Fatal ERROR!!!!! FROM UserManagementDAO");
            return -4;
        }

        try {
            Connection connection = ConnectionPool.getInstance().getUserManagementConnection();
            String sqlquery = "SELECT * FROM USER_REG where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,userReg.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return -1;
            }
            sqlquery = "INSERT INTO USER_REG (username, password, nickname,email,cell) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,userReg.getUsername());
            preparedStatement.setString(2,userReg.getPassword());
            preparedStatement.setString(3,userReg.getNickname());
            preparedStatement.setString(4,userReg.getEmail());
            preparedStatement.setString(5,userReg.getCell());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public int RecoverPass(String username, String email){
        if (VerifyInput.verifyUNPS(username)){
            if(VerifyInput.verifyEmail(email)){
                String sqlquery = "SELECT * FROM USER_REG WHERE username = ? AND email = ?";
                try {
                    Connection connection = ConnectionPool.getInstance().getUserManagementConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,email);
                    if (preparedStatement.executeQuery().next()){
                        //the username and email are matched
                        return 1;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        //username or email cannot pass reg_exp
        //or
        //cannot found the pair matched the username and email
        return 0;
    }
    public boolean setPass(String username, String password){
        if (VerifyInput.verifyUNPS(username) && VerifyInput.verifyUNPS(password)){
            String pass_md5 = Md5encrypt.getMd5(password);
            String sqlquery = "UPDATE USER_REG SET password = ? WHERE username = ?";
            try {
                Connection connection = ConnectionPool.getInstance().getUserManagementConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
                preparedStatement.setString(1,pass_md5);
                preparedStatement.setString(2,username);
                preparedStatement.execute();
                return true;
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public UserReg getUserRegInfo(String uid) {
        String sqlquery = "SELECT * FROM USER_REG WHERE UID = ?";
        UserReg userinfo = null;
        Connection connection = ConnectionPool.getInstance().getUserManagementConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String username = resultSet.getString(resultSet.findColumn("username"));
                String email = resultSet.getString(resultSet.findColumn("email"));
                String cell = resultSet.getString(resultSet.findColumn("cell"));
                String nickname = resultSet.getString(resultSet.findColumn("nickname"));
                userinfo = new UserReg(username,email,nickname,cell);
                preparedStatement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return userinfo;
    }
    public boolean updateUserInfo(UserReg userinfo,String uid){
        //TODO:ERROR CHECKING
        //v1 method
        boolean flag = false;
        String sqlquery = "UPDATE USER_REG SET ";
        if(userinfo.getPassword() != null){
            sqlquery += "password = ?, ";
        }
        sqlquery += "nickname = ?, email = ?, cell = ? WHERE uid = ?";
        Connection connection = ConnectionPool.getInstance().getUserManagementConnection();
        try {
            int index = 1;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            if (userinfo.getPassword()!=null){
                preparedStatement.setString(index++,userinfo.getPassword());
            }
            preparedStatement.setString(index++,userinfo.getNickname());
            preparedStatement.setString(index++,userinfo.getEmail());
            preparedStatement.setString(index++, userinfo.getCell());
            preparedStatement.setString(index,uid);
            preparedStatement.execute();
            preparedStatement.close();
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    public String getUserNicknameByUID(String uid){
        String sqlquery = "SELECT nickname from USER_REG WHERE uid = ?";
        String name = "";
        try {
            PreparedStatement preparedStatement = ConnectionPool.getInstance().getGeneralConnection().prepareStatement(sqlquery);
            preparedStatement.setString(1,uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                name = resultSet.getString(1);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public boolean changePassword(String uid, String oldpass, String newpass){
        boolean flag = false;
        String sqlquery = "SELECT COUNT(*) FROM USER_REG WHERE uid = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = ConnectionPool.getInstance().getUserManagementConnection().prepareStatement(sqlquery);
            preparedStatement.setString(1,uid);
            preparedStatement.setString(2,oldpass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                sqlquery = "UPDATE USER_REG SET password = ? WHERE uid = ?";
                preparedStatement = ConnectionPool.getInstance().getUserManagementConnection().prepareStatement(sqlquery);
                preparedStatement.setString(1,newpass);
                preparedStatement.setString(2,uid);
                preparedStatement.execute();
            }
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @Override
    protected Object parseCursor(ResultSet resultSet) {
        return null;
    }

    public String getUsernameByID(String uid){
        try {
            PreparedStatement preparedStatement = ConnectionPool.getInstance().getUserManagementConnection().prepareStatement("SELECT uid FROM USER_REG WHERE uid = ?");
            preparedStatement.setString(1,uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getUIDByUsername(String username){
        try {
            PreparedStatement preparedStatement = ConnectionPool.getInstance().getUserManagementConnection().prepareStatement("SELECT nickanme FROM USER_REG WHERE uid = ?");
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
