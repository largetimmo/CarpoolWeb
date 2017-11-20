package pojo;

/**
 * Created by chenjunhao on 2017/11/15.
 */
public class Message {
    private String M_ID;
    private String sender;
    private String receiver;
    private String message;
    private String ref;

    public String getRef() {
        return ref;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public String getM_ID() {
        return M_ID;
    }

    public Message(String MID, String sender, String receiver, String message, String ref) {
        this.M_ID = MID;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.ref = ref;
    }

    public Message(String sender, String receiver, String message, String ref) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.ref = ref;
    }
}
