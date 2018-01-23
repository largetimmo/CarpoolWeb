package web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet{
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res){
        String inv_method = req.getSession().getAttribute("invMethod").toString();
        try {
            Method method = this.getClass().getMethod(inv_method,HttpServletRequest.class,HttpServletResponse.class);
            String redirAddr = method.invoke(this,req,res).toString();
            if (redirAddr.startsWith("@")){

                res.sendRedirect(redirAddr.substring(1));
            }else {
                req.getRequestDispatcher(redirAddr).forward(req,res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
