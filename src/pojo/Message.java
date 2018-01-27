package pojo;

/**
 * Created by chenjunhao on 2017/11/15.
 */
public class Message {
    private String M_ID;
    private String sender_uid;
    private String receiver_uid;
    private String message;
    private String Carpool_ID;
    private String read;
    private String replied;
    private String sender_name;

    public Message() {
    }

    public Message(String sender_uid, String receiver_uid, String message, String carpool_ID, String sender_name) {
        this.sender_uid = sender_uid;
        this.receiver_uid = receiver_uid;
        this.message = message;
        Carpool_ID = carpool_ID;
        this.sender_name = sender_name;
    }

    public Message(String m_ID, String sender_id, String receiver_uid, String message, String carpool_ID, String read, String relied, String sender_name) {
        M_ID = m_ID;
        this.sender_uid = sender_id;
        this.receiver_uid = receiver_uid;
        this.message = message;
        Carpool_ID = carpool_ID;
        this.read = read;
        this.replied = relied;
        this.sender_name = sender_name;
    }

    public String getID() {
        return M_ID;
    }

    public String getSender_uid() {
        return sender_uid;
    }

    public String getReceiver_uid() {
        return receiver_uid;
    }

    public String getMessage() {
        return message;
    }

    public String getRef() {
        return Carpool_ID;
    }

    public String getRead() {
        return read;
    }

    public String getReplied() {
        return replied;
    }

    public String getSender_name() {
        return sender_name;
    }
}
