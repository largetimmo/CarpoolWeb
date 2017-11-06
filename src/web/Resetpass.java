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
public class Resetpass extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String password = req.getParameter("password");

        if (req.getSession().getAttribute("USERNAME_TEMP")!=null & UserManagementDAO.getInstance().setPass(req.getSession().getAttribute("USERNAME_TEMP").toString(),password)){
            req.getSession().removeAttribute("USERNAME_TEMP");
            try {
                //Send back to login page with param code = 1
                res.sendRedirect("/login.html?code=1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            res.sendRedirect("/recoverypass.jsp?code=-1");
        }
    }
}
