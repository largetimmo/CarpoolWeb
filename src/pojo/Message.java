package pojo;

/**
 * Created by chenjunhao on 2017/11/15.
 */
public class Message {
    public String M_ID;
    public String sender;
    public String receiver;
    public String message;
    public String ref;
    public Message(String MID, String sender, String receiver, String message, String ref) {
        //basic constructor
        this.M_ID = MID;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.ref = ref;
    }

    public Message(String sender, String receiver, String message, String ref) {
        //for 'getter' in DAO
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.ref = ref;
    }
    public Message(){

    }

}
