package web;

import core.UserReg;
import dao.UserManagementDAO;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/8/20.
 */
public class RegisterHandler extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");
        String cell = req.getParameter("cell");
        UserReg userReg = new UserReg(username,password,email,nickname,cell);
        int code = UserManagementDAO.getInstance().Register(userReg);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        res.setContentType("text/html");
        res.getWriter().print(jsonObject);
    }
}
