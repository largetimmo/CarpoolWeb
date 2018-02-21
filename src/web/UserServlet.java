package web;


import dao.UserManagementDAO;
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
        String oldpass = req.getParameter("oldpass");
        String newpass = req.getParameter("newpass");
        String uid = req.getSession().getAttribute("uid").toString();
        if (UserManagementDAO.getInstance().changePassword(uid,oldpass,newpass)){
            req.setAttribute("msg","Change password success");
        }else {
            req.setAttribute("msg","Change password failed");
        }
        return "/index.jsp";
        //return "forehome";
    }
}
