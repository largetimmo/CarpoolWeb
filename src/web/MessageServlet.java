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
    public String list(HttpServletRequest req, HttpServletResponse res){
        //get user id
        String uid = req.getSession().getAttribute("uid").toString();
        //get message instance
        MessageDAO messageDAO = MessageDAO.getInstance();
        List<Message> msg_list = messageDAO.getReceiverMessage(uid);
        req.setAttribute("messages",msg_list);
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
        String msg = null;
        if(prev_m == null){
            //sent from new message
            String uid = req.getSession().getAttribute("uid").toString();
            String targetname = req.getParameter("to");
            String content = req.getParameter("content");
            String targetuid = UserManagementDAO.getInstance().getUIDByNickname(targetname);
            String ref = req.getParameter("ref");
            String id = req.getParameter("id");
            Message message = Message.createCarpoolMessage(uid,targetuid,content,ref==null?id:ref);
            if(MessageDAO.getInstance().addMessage(message)){
                msg = "success";
            }
            return "@user_carpool_detail?msg="+msg+(ref==null?("&id="+id):("&ref="+ref));

        }else {
            //sent from reply
            String rec_uid = prev_m.getSender_uid();
            String sd_uid = prev_m.getReceiver_uid();
            String message_send = req.getParameter("message_send");
            String ref = prev_m.getRef();
            Message newMsg = new Message(sd_uid,rec_uid,message_send,ref,null);
            if(MessageDAO.getInstance().addMessage(newMsg)){
                msg = "Send message successfully";
            }else {
                msg = "Send message failed";
            }
            req.getSession().removeAttribute("messgae");
        }
        return "@user_message_list?msg="+msg;
    }
    public String remove(HttpServletRequest req, HttpServletResponse res){
        String mid= req.getParameter("mid");
        String msg;
        if(MessageDAO.getInstance().delete(mid)){
            msg = "Delete Message success";
        }else{
            msg = "Delete Message failed";
        }
        return "@user_message_list?msg="+msg;
    }

    public String newmessage(HttpServletRequest req, HttpServletResponse res){
        String targerudername = "";
        String ref = req.getParameter("ref");
        String id = req.getParameter("id");
        if(ref!=null){
            req.setAttribute("ref",ref);
        }else {
            req.setAttribute("id",id);
        }
        if(req.getParameter("targetuid") != null){
            String targetuid = req.getParameter("targetuid");
            targerudername = UserManagementDAO.getInstance().getUserNicknameByUID(targetuid);

        }
        req.setAttribute("targetusername",targerudername);


        return "/user/newmessage.jsp";
    }

}
