package web;

import dao.BookedCarpoolDAO;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import pojo.BookedCarpoolDetail;
import util.Pair;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by chenjunhao on 2017/11/16.
 */
public class DetailBookingInfoHandler extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String ref = req.getParameter("ref");
        String uid = req.getSession().getAttribute("uid").toString();
        String type = req.getParameter("type");
        JSONObject output = null;
        if (type.equals("passenger")){
            output = deatilAsPassenger(ref);
        }
        res.getWriter().write(output.toString());
    }
    private JSONObject deatilAsPassenger(String ref){
        JSONObject output = new JSONObject();
        BookedCarpoolDetail bookedCarpoolDetail = BookedCarpoolDAO.getInstance().getBookedCarpoolDetailAsPassenger(ref);
        if (bookedCarpoolDetail == null){
            output.put("code","-1");
        }else {
            output.put("code","1");
            JSONObject detail = new JSONObject();
            detail.put("departure",bookedCarpoolDetail.getDeparture());
            detail.put("destination",bookedCarpoolDetail.getDestination());
            detail.put("date",bookedCarpoolDetail.getDate());
            ArrayList<JSONObject> userlist = new ArrayList<>();
            for (Pair<String,String> p:bookedCarpoolDetail.getUsers()){
                JSONObject userinfo = new JSONObject();
                userinfo.put("nickname",p.first);
                userinfo.put("uid",p.second);
                userlist.add(userinfo);
            }
            detail.put("user",userlist);
            output.put("detail",detail);
        }
        return output;
    }
}
