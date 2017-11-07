package web;

import core.UserReg;
import dao.UserManagementDAO;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenjunhao on 2017/11/6.
 */
public class UserInformationHandler extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //update user information
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String email = req.getParameter("email");
        String cell = req.getParameter("cell");
        String uid = req.getSession().getAttribute("uid").toString();
        if (password.equals("")){
            password = null;
        }
        UserReg userReg = new UserReg(null,password,email,nickname,cell);
        if (UserManagementDAO.getInstance().updateUserInfo(userReg,uid)){
            res.sendRedirect("/user/usermanagement.html?code=1");
        }else {
            res.sendRedirect("/user/usermanagement.html?code=0");
        }



    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //acquire user information
        String uid = req.getSession().getAttribute("uid").toString();
        UserReg userinfo = UserManagementDAO.getInstance().getUserRegInfo(uid);
        JSONObject user_json = new JSONObject();
        user_json.put("username",userinfo.getUsername());
        user_json.put("cell",userinfo.getCell());
        user_json.put("email",userinfo.getEmail());
        user_json.put("nickname",userinfo.getNickname());
        res.getWriter().write(user_json.toString());
    }
}
