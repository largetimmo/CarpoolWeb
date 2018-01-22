package dao;

import org.apache.commons.lang.reflect.FieldUtils;
import pojo.Message;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjunhao on 2017/11/15.
 */
public class MessageDAO extends AbstractDAO<Message> {
    //read == 0 unread
    //read ==1 read
    //replied == 0 not replied
    //replied == 1 replied
    private static MessageDAO INSTANCE = new MessageDAO();

    public static MessageDAO getInstance() {
        return INSTANCE;
    }
    private MessageDAO() {
        super();
    }
    /*
    //V1 method
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
    */
    public ArrayList<Message> getReceiverMessage(String uid) {
        //V2 method
        String sqlquery = "SELECT * FROM MESSAGE WHERE receiver_uid = ?";
        return executeQuery(sqlquery,new String[]{uid});
    }
    public ArrayList<Message> getUnReadMessage(String uid){
        //V2
        String sqlquery = "SELECT * FROM MESSAGE WHERE sender_uid = ?";

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
        //V2
        String sqlquery = "UPDATE MESSAGE SET `read` = 1 WHERE M_ID = ?";
        return execute(sqlquery,new Object[]{MID});
    }
    public boolean replyMessage(String mid){
        //V2
        String sqlquery = "UPDATE MESSAGE SET replied = 1 WHERE M_ID = ?";
        return execute(sqlquery,null);
    }
    public boolean addMessage(Message message){
        //V2
        return add(message);
    }
    public Message getMessageByID(String id){
        //V2
        String sqlquery = "SELECT * FROM MESSAGE WHERE M_ID = ?";
        Message m = null;
        try {
            PreparedStatement preparedStatement = ConnectionPool.getInstance().getGeneralConnection().prepareStatement(sqlquery);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                m = parseCursor(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
    private boolean add(Message message){
        //V2
        String sqlquery = "INSERT INTO MESSAGE(sender_uid,receiver_uid,message,Carpool_ID,sender_name) VALUES (?,?,?,?,?)";
        return execute(sqlquery,new String[]{message.getSender_uid(),message.getReceiver_uid(),message.getMessage(),message.getRef(),message.getSender_name()});

    }
    @Override
    protected Message parseCursor(ResultSet resultSet){
        Message m = null;
        //using JAVA reflection is very convenient way to acquire all data
        Field[] allfield = Message.class.getDeclaredFields();
        String[] parms = new String[allfield.length];
        try {
            for (int i = 0;i<allfield.length;i++){
                String data = resultSet.getString(resultSet.findColumn(allfield[i].getName()));
                parms[i] = data;
            }
            m = new Message(parms[0],parms[1],parms[2],parms[3],parms[4],parms[5],parms[6],parms[7]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
    /*
    @Override
    protected Message parseCursor(ResultSet resultSet){
    //V1 method
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

