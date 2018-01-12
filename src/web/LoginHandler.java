package web;

import net.sf.json.JSONObject;
import util.Md5encrypt;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/8/20.
 */
public class LoginHandler extends HttpServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String password_md5 = Md5encrypt.getMd5(password);
        int userID = dao.UserManagementDAO.getInstance().Login(username,password_md5);
        JSONObject jsonObject;
        switch (userID){
            case -1:
                jsonObject = new JSONObject();
                jsonObject.put("code", -1);
                res.setContentType("text/html");
                res.getWriter().print(jsonObject);
                break;
            case -2:
                System.out.println("FETAL ERROR FROM LOGINHANDLER");
                break;
            default:
                jsonObject = new JSONObject();
                jsonObject.put("code",1);
                req.getSession().setAttribute("uid",userID);
                res.setContentType("text/html");
                res.getWriter().print(jsonObject);
                System.out.println(req.getSession().getAttribute("uid").toString());
                break;
        }
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res){
        try {
            req.getSession().removeAttribute("uid");
            res.sendRedirect("index.jsp?code=1");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
