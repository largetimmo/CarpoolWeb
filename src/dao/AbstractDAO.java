package dao;

import pojo.Message;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/8/21.
 */
public abstract class AbstractDAO<T> {
    T t;
    static final Map<String,String> CLASS_PAIR = new HashMap<>();
    static final Map<String,String> TYPE_PAIR = new HashMap<>();
    AbstractDAO(){
        init();
    }
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
    private void init(){
        CLASS_PAIR.put("int","java.lang.Integer");
        CLASS_PAIR.put("double","java.lang.Double");
        CLASS_PAIR.put("boolean","java.langBoolean");
        CLASS_PAIR.put("float","java.langFloat");
        CLASS_PAIR.put("long","java.lang.Long");
        TYPE_PAIR.put("int","Int");
        TYPE_PAIR.put("double","Double");
        TYPE_PAIR.put("boolean","Boolean");
        TYPE_PAIR.put("float","Float");
        TYPE_PAIR.put("long","Long");
    }
    protected T parseCursor(ResultSet resultSet){
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //get class of T
            Class t_cls =  t.getClass();
            //empty constructor
            Constructor t_cons = t_cls.getConstructor();
            //Create new object using the constructor
            T t_ins = (T) t_cons.newInstance();
            for (int i = 0 ;i<resultSetMetaData.getColumnCount();i++){
                //get the column name which is same as the attribute name
                String colname = resultSetMetaData.getColumnLabel(i+1);
                //find the field by using the colname
                Field t_field= t_cls.getDeclaredField(colname);
                t_field.setAccessible(true);
                if (t_field.getType().getName().equals("java.lang.String")){
                    //Attr is String
                    t_field.set(t,resultSet.getString(i+1));
                }else{
                    //get type name
                    String type = t_field.getType().getName();
                    //get Class by name
                    Class type_cls = Class.forName(CLASS_PAIR.get(type));
                    //get message_filed method for set data
                    Method field_meth = t_field.getClass().getMethod("set"+TYPE_PAIR.get(type), Object.class,(Class) type_cls.getDeclaredField("TYPE").get(type));
                    //get resultSet method for acquiring Data from resultSet
                    Method resultSet_method = resultSet.getClass().getMethod("get"+TYPE_PAIR.get(type),(Class) type_cls.getDeclaredField("TYPE").get(type));
                    //result from result_method
                    Object resultSet_result = resultSet_method.invoke(resultSet,i+1);
                    //invoke setting method
                    field_meth.invoke(t_field,t,resultSet_result);
                }
            }
            return t;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
