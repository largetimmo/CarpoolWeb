package web;

import dao.MessageDAO;
import net.sf.json.JSONObject;
import pojo.Message;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenjunhao on 2017/11/15.
 */
public class MessageHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse res){
        //get All messages related to user

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //Send messgae to user
        String sender_uid = req.getSession().getAttribute("uid").toString();
        String receiver_uid = req.getParameter("uid");
        String message = req.getParameter("message");
        String ref = req.getParameter("ref");
        JSONObject output = new JSONObject();
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
}
