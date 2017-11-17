package dao;

import pojo.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by admin on 2017/8/21.
 */
public abstract class AbstractDAO<T> {
    protected  boolean execute(String sqlquery, Object[] params){
        boolean flag = false;
        Connection connection = ConnectionPool.getInstance().getGeneralConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            for (int i = 0 ;i<params.length;i++){
                preparedStatement.setString(i+1,params[i].toString());
            }
            preparedStatement.execute();
            preparedStatement.close();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;

    }
    protected ArrayList<T> executeQuery(String sqlquery, Object[] params){
        Connection connection = ConnectionPool.getInstance().getGeneralConnection();
        ArrayList<T> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            int index = 1;
            for (Object obj : params){
                preparedStatement.setString(index++,obj.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    result.add(parseCursor(resultSet));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    protected abstract T parseCursor(ResultSet resultSet);

}
