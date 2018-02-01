package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForeServlet extends BaseServlet {
    public String home(HttpServletRequest req, HttpServletResponse res){
        return "index.jsp";
    }
    public String forgetpass(HttpServletRequest req, HttpServletResponse res){
        return null;
    }
    public String login(HttpServletRequest req, HttpServletResponse res){
        return null;
    }
    public String register(HttpServletRequest req, HttpServletResponse res){
        return null;
    }
    public String search(HttpServletRequest req, HttpServletResponse res){
        //Search for new
        return null;
    }
}
