package web;

import dao.MessageDAO;
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
public class MessageHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse res){
        //get All messages related to user
        String uid = req.getSession().getAttribute("uid").toString();
        String type = req.getParameter("type");


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //Send messgae to user
        String sender_uid = req.getSession().getAttribute("uid").toString();
        String receiver_uid = req.getParameter("uid");
        String message = req.getParameter("message");
        String ref = req.getParameter("ref");
        JSONObject output = new JSONObject();
        MessageDAO.getInstance().getUnReadMessage(sender_uid);
        /**
         * TODO:Error Checking here
         * message too long
         */
        if (MessageDAO.getInstance().addMessage(new Message(sender_uid,receiver_uid,message,ref))){
            output.put("code","1");
        }else {
            output.put("code","-1");
        }
        res.getWriter().write(output.toString());
    }
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
    private JSONObject getAllMessageAsSender(String uid){
        JSONObject output = new JSONObject();
        return output;
    }
    private JSONObject getAllMessageAsReceiver(String uid){
        //TODO
        JSONObject output = new JSONObject();
        return output;
    }
    public JSONObject list(String uid){
        //TODO:
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }
}
