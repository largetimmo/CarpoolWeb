package web;

import dao.UserManagementDAO;
import pojo.UserReg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForeServlet extends BaseServlet {
    public String home(HttpServletRequest req, HttpServletResponse res){
        //page
        return "/index.jsp";
    }
    public String forgetpass(HttpServletRequest req, HttpServletResponse res){
        //action
        return null;
    }
    public String login(HttpServletRequest req, HttpServletResponse res){
        //action
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int userID = dao.UserManagementDAO.getInstance().Login(username,password);
        String msg = "";
        switch (userID){
            case -1:
                msg = "No matching username/password";
                break;
            case -2:
                System.out.println("FETAL ERROR FROM LOGINHANDLER");
                msg="FETAL ERROR";
                break;
            default:
                //success
                msg = "Login success";
                req.getSession().setAttribute("uid",userID);
                return "@fore_home?msg="+msg;
        }
        return "#/login.jsp?msg="+msg;

    }
    public String register(HttpServletRequest req, HttpServletResponse res){
        //action
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");
        String cell = req.getParameter("cell");
        UserReg userReg = new UserReg(username,password,email,nickname,cell);
        String msg = UserManagementDAO.getInstance().Register(userReg);
        if(msg.equals("Register success")){
            return "@fore_home?msg="+msg;
        }
        return "#/login.jsp?msg="+msg;
    }
    public String search(HttpServletRequest req, HttpServletResponse res){
        //action
        //Search for new
        return null;
    }
    public String book(HttpServletRequest req, HttpServletResponse res){
        //action
        return null;
    }

}
