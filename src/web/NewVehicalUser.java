package web;

import dao.CarpoolOwnerInfoDAO;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenjunhao on 2017/11/7.
 */
public class NewVehicalUser extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //verify user have submitted vehical information or not
        String uid = req.getSession().getAttribute("uid").toString();
        JSONObject output = new JSONObject();
        if(!CarpoolOwnerInfoDAO.getInstance().verifyCarpoolOwner(uid)){
            output.put("code","-1");
        }else {
            output.put("code","1");
        }
        res.getWriter().write(output.toString());

    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //handler for post vehical information
        String uid = req.getSession().getAttribute("uid").toString();
        String vehicle = req.getParameter("vehicle_name");
        JSONObject obj = new JSONObject();
        if(CarpoolOwnerInfoDAO.getInstance().AddVehicleOwner(uid,vehicle)){
            obj.put("code","1");
        }else {
            obj.put("code","-1");
        }
        res.sendRedirect("/user/usermanagement.html?code=2");
    }

}
