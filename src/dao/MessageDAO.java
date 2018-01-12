package dao;

import org.apache.commons.lang.reflect.FieldUtils;
import pojo.Message;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chenjunhao on 2017/11/15.
 */
public class MessageDAO extends AbstractDAO<Message> {
    //read == 0 unread
    //read ==1 read
    private static MessageDAO INSTANCE = new MessageDAO();

    public static MessageDAO getInstance() {
        return INSTANCE;
    }
    private MessageDAO() {
        super();
    }
    public ArrayList<Message> getSenderMessage(String uid) {
        String sqlquery = "SELECT M_ID, Carpool_ID, U2.nickname AS RN, message FROM MESSAGE INNER JOIN USER_REG U2 ON receiver_uid = U2.uid WHERE sender_uid = ?";
        Connection connection = ConnectionPool.getInstance().getGeneralConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executeQuery(sqlquery,new String[]{uid});
    }

    public ArrayList<Message> getReceiverMessage(String uid) {
        String sqlquery = "SELECT" +
                " M_ID,"+
                " Carpool_ID,"+
                "  U1.nickname AS 'SN'," +
                "  U2.nickname AS 'RN'," +
                "  message" +
                "FROM MESSAGE" +
                "  INNER JOIN USER_REG U1 ON sender_uid = U1.uid\n" +
                "  INNER JOIN USER_REG U2 ON receiver_uid = U2.uid\n" +
                "WHERE receiver_uid = ?";
       return executeQuery(sqlquery,new String[]{uid});
    }
    public ArrayList<Message> getUnReadMessage(String uid){
        String sqlquery = "SELECT M_ID,nickname AS sender,Carpool_ID AS ref,message FROM MESSAGE INNER JOIN USER_REG ON MESSAGE.sender_uid = USER_REG.uid WHERE `read` = 0 AND receiver_uid = ?";

        ArrayList<Message> messages = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getGeneralConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1,uid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                messages.add(parseCursor(resultSet));
                /*
                String id = resultSet.getString(resultSet.findColumn("M_ID"));
                String nickname = resultSet.getString(resultSet.findColumn("sender"));
                String cid = resultSet.getString(resultSet.findColumn("ref"));
                String msg = resultSet.getString(resultSet.findColumn("message"));
                Message message = new Message();
                message.message=msg;
                message.M_ID = id;
                message.sender= nickname;
                message.ref = cid;
                messages.add(message);
                */
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return messages;
    }
    public boolean readMessage(String MID){
        String sqlquery = "UPDATE Message SET `read` = 1 WHERE M_ID = ?";
        return execute(sqlquery,new Object[]{MID});
    }
    public boolean addMessage(Message message){
        String sqlquery = "INSERT INTO Message(sender_uid,receiver_uid,message,`read`,Carpool_ID) VALUES (?,?,?,'0',?)";
        return execute(sqlquery,new Object[]{message.sender,message.receiver,message.message,message.ref});
    }
    /*
    @Override
    protected Message parseCursor(ResultSet resultSet){
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            Message message = new Message();
            Class msg_cls = message.getClass();
            for (int i = 0 ;i<resultSetMetaData.getColumnCount();i++){
                String colname = resultSetMetaData.getColumnLabel(i+1);
                Field message_field= msg_cls.getDeclaredField(colname);
                message_field.setAccessible(true);
                if (message_field.getType().getName().equals("java.lang.String")){
                    //Attr is String
                    message_field.set(message,resultSet.getString(i+1));
                }else{
                    //get type name
                    String type = message_field.getType().getName();
                    //get Class by name
                    Class type_cls = Class.forName(CLASS_PAIR.get(type));
                    //get message_filed method for set data
                    Method field_meth = message_field.getClass().getMethod("set"+TYPE_PAIR.get(type), Object.class,(Class) type_cls.getDeclaredField("TYPE").get(type));
                    //get resultSet method for acquiring Data from resultSet
                    Method resultSet_method = resultSet.getClass().getMethod("get"+TYPE_PAIR.get(type),(Class) type_cls.getDeclaredField("TYPE").get(type));
                    //result from result_method
                    Object resultSet_result = resultSet_method.invoke(resultSet,i+1);
                    //invoke setting method
                    field_meth.invoke(message_field,message,resultSet_result);
                }
            }
            return message;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    */
}

