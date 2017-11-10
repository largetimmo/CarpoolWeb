package web;

import dao.UserManagementDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenjunhao on 2017/11/6.
 */
public class ForgetPassHandler extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        if (UserManagementDAO.getInstance().RecoverPass(username,email) == 1){
            req.getSession().setAttribute("USERNAME_TEMP",username);
            try {
                req.getRequestDispatcher("recoverypass.jsp").forward(req,res);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }else {
            res.sendRedirect("/forgetpass.html?code=-1");
        }
    }
}
