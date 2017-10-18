package dao;

import com.sun.org.apache.regexp.internal.RE;
import core.UserReg;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2017/8/21.
 */
public class UserManagementDAO extends AbstractDAO{
    private static final String REG_EXP_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    private static final String REG_EXP_USERNAME_PASSWORD = "^[A-Za-z0-9]+$";

    private UserManagementDAO(){

    }
    private static UserManagementDAO instance = new UserManagementDAO();
    public static UserManagementDAO getInstance() {
        return instance;
    }
    public static int Login(String username, String password){
        /*
            log in with username and password
            return uid if username and password corrent
            else return -1
            return -2 for internal error
         */
        try {
            Connection connection = ConnectionPool.getInstance().getUserManagementConnection();
            String sqlquery = "SELECT uid FROM user_reg_info WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String uid_string = resultSet.getString(1);
                int uid = Integer.parseInt(uid_string);
                preparedStatement.close();
                connection.close();
                return uid;
            }else {
                preparedStatement.close();
                connection.close();
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -2;
    }



    public static int Register(UserReg userReg){
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
        Pattern pattern = Pattern.compile(REG_EXP_EMAIL);
        Matcher matcher = pattern.matcher(userReg.getEmail());
        if(!matcher.find()){
            return -5;
        }
        /*
        checking invalid character in username
         */
        pattern = Pattern.compile(REG_EXP_USERNAME_PASSWORD);
        matcher = pattern.matcher(userReg.getUsername());
        if(!matcher.find()){
            return -2;
        }
        /*
        checking invalid character in password
         */
        matcher = pattern.matcher(userReg.getPassword());
        if(!matcher.find()){
            return -3;
        }

        /*
        checking invalid character in nickname
         */

        matcher = pattern.matcher(userReg.getNickname());
        if (!matcher.find()){
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
            sqlquery = "INSERT INTO USER_REG (username, password, nickname,email) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,userReg.getUsername());
            preparedStatement.setString(2,userReg.getPassword());
            preparedStatement.setString(3,userReg.getNickname());
            preparedStatement.setString(4,userReg.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

}
