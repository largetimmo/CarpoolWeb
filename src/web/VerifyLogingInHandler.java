package web;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/8/23.
 */
public class VerifyLogingInHandler extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        JSONObject jsonObject = new JSONObject();
        if(req.getSession().getAttribute("uid")!=null){
            jsonObject.put("code",1);


        }else {
            jsonObject.put("code", 0);
        }
        res.setContentType("text/html");
        res.getWriter().print(jsonObject);
    }
}
