package web;

import core.CarpoolInfo;
import dao.CarpoolDAO;
import dao.CarpoolOwnerInfoDAO;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/8/23.
 */
public class PostBookingInfoHandler extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String uid = req.getSession().getAttribute("uid").toString();
        if(!CarpoolOwnerInfoDAO.verifyCarpoolOwner(uid)){
            //用户尚未提交车辆信息
            JSONObject a = new JSONObject();
            a.put("code",-100);
            res.getWriter().print(a);
        }else{
            String departure = req.getParameter("from");
            String destination = req.getParameter("to");
            String passenger = req.getParameter("passengers");
            String date = req.getParameter("date");
            String price = req.getParameter("price");
            Boolean success = CarpoolDAO.storageCarpoolInfo(departure,destination,passenger,price,date,uid);
            JSONObject jsonObject = new JSONObject();
            if(success){
                jsonObject.put("code",1);
            }
            else{
                //暂时先设为0
                //error checking 补上后发送不同的error code
                jsonObject.put("code",0);
            }
            res.getWriter().print(jsonObject);
        }
    }

}
