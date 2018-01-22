package web;

import dao.MessageDAO;
import dao.UserManagementDAO;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import pojo.Message;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenjunhao on 2017/11/15.
 */
public class MessageServlet extends BaseServlet {
    /*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //v1
        //Send messgae to user
        String sender_uid = req.getSession().getAttribute("uid").toString();
        String receiver_uid = req.getParameter("uid");
        String message = req.getParameter("message");
        String ref = req.getParameter("ref");
        JSONObject output = new JSONObject();
        MessageDAO.getInstance().getUnReadMessage(sender_uid);
        if (MessageDAO.getInstance().addMessage(new Message(sender_uid,receiver_uid,message,ref))){
            output.put("code","1");
        }else {
            output.put("code","-1");
        }

        res.getWriter().write(output.toString());
    }
    */
    /*
    private JSONObject getUnreadMessageAsReceiver(String uid){
        JSONObject output = new JSONObject();
        ArrayList<Message> allmessages = MessageDAO.getInstance().getUnReadMessage(uid);
        ArrayList<JSONObject> messages = new ArrayList<>();
        for (Message m: allmessages){
            JSONObject msg_json = new JSONObject();
            msg_json.put("ref",m.ref);
            msg_json.put("msg",m.message);
            msg_json.put("sender",m.sender);
            msg_json.put("id",m.M_ID);
            messages.add(msg_json);
        }
        output.put("result",messages);
        return output;
    }
    */
    public String list(HttpServletRequest req, HttpServletResponse res){
        //get user id
        String uid = req.getSession().getAttribute("uid").toString();
        //get message instance
        MessageDAO messageDAO = MessageDAO.getInstance();
        List<Message> msg_list = messageDAO.getReceiverMessage(uid);
        req.getSession().setAttribute("messages",msg_list);
        return "/user/message.jsp";
    }
    public String read(HttpServletRequest req,HttpServletResponse res){
        String mid = req.getParameter("mid");
        MessageDAO messageDAO = MessageDAO.getInstance();
        Message m = messageDAO.getMessageByID(mid);
        req.getSession().setAttribute("message",m);
        messageDAO.readMessage(mid);
        return "/user/message_reply.jsp";
    }
    public String send(HttpServletRequest req, HttpServletResponse res){
        Message prev_m = (Message)req.getSession().getAttribute("message");
        String rec_uid = prev_m.getSender_uid();
        String sd_uid = prev_m.getReceiver_uid();
        String message_send = req.getParameter("message_send");
        String nickname = UserManagementDAO.getInstance().getUserNicknameByUID(sd_uid);
        String ref = prev_m.getRef();
        Message newMsg = new Message(sd_uid,rec_uid,message_send,ref,nickname);
        if(MessageDAO.getInstance().addMessage(newMsg)){
            req.getSession().setAttribute("msg","Send message successfully");
        }else {
            req.getSession().setAttribute("msg","Send message failed");
        }
        return "@user_message_list";
    }
}
