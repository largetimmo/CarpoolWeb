package web;

import org.apache.commons.lang.StringUtils;

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
            if(req.getParameter("msg")!=null){
                //only support when uri start with @
                req.setAttribute("msg",req.getParameter("msg"));
            }
            if (redirAddr.startsWith("@")){
                //server go to another method
                res.sendRedirect(redirAddr.substring(1));
            }else if(redirAddr.startsWith("#")){
                //only support when uri with no prefix
                req.setAttribute("msg", StringUtils.substringAfter(redirAddr,"msg="));
                req.getRequestDispatcher(StringUtils.substringBetween(redirAddr,"#","?")).forward(req,res);
            }
            else {
                //client go to another page
                req.getRequestDispatcher(redirAddr).forward(req,res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
