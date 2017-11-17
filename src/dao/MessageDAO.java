package dao;

import pojo.Message;

import java.sql.ResultSet;
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
    }
    public ArrayList<Message> getSenderMessage(String uid) {
        String sqlquery = "SELECT" +
                " M_ID,"+
                "  U1.nickname AS 'SN'," +
                "  U2.nickname AS 'RN'," +
                "  message" +
                "FROM MESSAGE" +
                "  INNER JOIN USER_REG U1 ON sender_uid = U1.uid\n" +
                "  INNER JOIN USER_REG U2 ON receiver_uid = U2.uid\n" +
                "WHERE sender_uid = ?";
        return executeQuery(sqlquery,new String[]{uid});
    }

    public ArrayList<Message> getReceiverMessage(String uid) {
        String sqlquery = "SELECT" +
                " M_ID,"+
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
        String sqlquery = "SELECT" +
                " M_ID,"+
                "  U1.nickname AS 'SN'," +
                "  U2.nickname AS 'RN'," +
                "  message" +
                "FROM MESSAGE" +
                "  INNER JOIN USER_REG U1 ON sender_uid = U1.uid\n" +
                "  INNER JOIN USER_REG U2 ON receiver_uid = U2.uid\n" +
                "WHERE receiiver_uid = ? AND read = 0";
        return executeQuery(sqlquery,new String[]{uid});
    }
    public boolean readMessage(String MID){
        String sqlquery = "UPDATE Message SET read = 1 WHERE M_ID = ?";
        return execute(sqlquery,new Object[]{MID});
    }
    public boolean addMessage(String senderid,String receiverid,String message){
        String sqlquery = "INSERT INTO Message(sender_id,receiver_id,message,read) VALUES (?,?,?,'0')";
        return execute(sqlquery,new Object[]{senderid,receiverid,message});
    }
    @Override
    protected Message parseCursor(ResultSet resultSet){
        try {
            String mid = resultSet.getString(resultSet.findColumn("M_ID"));
            String SN = resultSet.getString(resultSet.findColumn("SN"));
            String RN = resultSet.getString(resultSet.findColumn("RN"));
            String message = resultSet.getString(resultSet.findColumn("message"));
            return new Message(mid,SN,RN,message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

