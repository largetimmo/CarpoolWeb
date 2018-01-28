package web;


import dao.UserManagementDAO;
import pojo.UserInfo;
import pojo.UserReg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends BaseServlet {

    public String logout(HttpServletRequest req, HttpServletResponse res){
        //Input:None
        if (req.getSession().getAttribute("uid")!= null){
            req.getSession().removeAttribute("uid");
        }
        return "index.jsp";
        //return "@fore_home";
    }

    public String changeinfo(HttpServletRequest req, HttpServletResponse res) {
        UserManagementDAO userManagementDAO = UserManagementDAO.getInstance();
        String uid = req.getSession().getAttribute("uid").toString();
        UserReg userReg = userManagementDAO.getUserRegInfo(uid);
        req.setAttribute("userinfo",userReg);
        return "/user/profile.jsp";
    }
    public String update(HttpServletRequest req, HttpServletResponse res){
        String email = req.getParameter("email");
        String cell = req.getParameter("cell");
        String nickname = req.getParameter("nickname");
        String uid = req.getSession().getAttribute("uid").toString();
        UserReg userReg = new UserReg(null,email,nickname,cell);
        if (UserManagementDAO.getInstance().updateUserInfo(userReg,uid)){
            req.setAttribute("msg","Update Success");
        }else{
            req.setAttribute("msg","Update failed");
        }
        return "@user_user_changeinfo";
    }
    public String changepass(HttpServletRequest req, HttpServletResponse res){
        String password = req.getParameter("password");
        String uid = req.getSession().getAttribute("uid").toString();
        return null;
    }

}
