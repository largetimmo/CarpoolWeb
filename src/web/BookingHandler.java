package web;

import dao.CarpoolDAO;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/8/23.
 */
public class BookingHandler extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("bookid");
        String uid = req.getSession().getAttribute("uid").toString();
        String seats = req.getSession().getAttribute("seats").toString();
        if(seats == null){
            return;
        }
        JSONObject jsonObject = new JSONObject();
        Boolean success = CarpoolDAO.bookCarpool(uid,id,seats);
        if(success){
            jsonObject.put("code",1);
        }
        else {
            jsonObject.put("code",0);
        }
        res.getWriter().print(jsonObject);

    }
}
