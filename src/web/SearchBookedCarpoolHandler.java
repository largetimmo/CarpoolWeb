package web;

import core.BookedCarpoolInfo;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/31.
 */
public class SearchBookedCarpoolHandler extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        JSONObject jsonObject = new JSONObject();
        String uid = req.getSession().getAttribute("uid").toString();
        List<BookedCarpoolInfo> list = dao.CarpoolDAO.getInstance().searchAllBookedCarpool(uid);
        if(list.size()==0) {
            jsonObject.put("code", 0);
        }else {
            jsonObject.put("code",1);
            List<JSONObject> carpoolinfo_json_list = new ArrayList<>();
            for(BookedCarpoolInfo b:list){
                JSONObject carpoolinfo_json = new JSONObject();
                carpoolinfo_json.put("reference",b.getReference_number());
                carpoolinfo_json.put("seat",b.getSeats());
                carpoolinfo_json.put("departure",b.getCarpoolInfo().getFrom());
                carpoolinfo_json.put("destination",b.getCarpoolInfo().getTo());
                carpoolinfo_json.put("vehicle",b.getCarpoolInfo().getUser().getVehicle());
                carpoolinfo_json.put("date",b.getCarpoolInfo().getDateTime().toString());
                carpoolinfo_json.put("name",b.getCarpoolInfo().getUser().getNickname());
                carpoolinfo_json_list.add(carpoolinfo_json);
            }
            jsonObject.put("record",carpoolinfo_json_list.toArray());
        }
        res.getWriter().print(jsonObject);
    }
}
