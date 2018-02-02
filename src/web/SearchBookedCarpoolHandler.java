package web;

import pojo.BookedCarpoolInfo;
import pojo.CarpoolInfo;
import dao.CarpoolDAO;
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
        String type = req.getParameter("type");
        String uid = req.getSession().getAttribute("uid").toString();
        JSONObject jsonObject;
        if(type.equals("1")){
            //get booked
            jsonObject = getBookedCarpool(uid);
        }else {
            //get posted
            jsonObject = getPostedCarpool(uid);
        }
        res.getWriter().print(jsonObject);
    }
    private JSONObject getBookedCarpool(String uid){
        JSONObject jsonObject = new JSONObject();
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
                carpoolinfo_json.put("departure",b.getCarpoolInfo().getDeparture());
                carpoolinfo_json.put("destination",b.getCarpoolInfo().getDestination());
                carpoolinfo_json.put("vehicle",b.getCarpoolInfo().getUser().getVehicle());
                carpoolinfo_json.put("date",b.getCarpoolInfo().getDateTime().toString());
                carpoolinfo_json.put("name",b.getCarpoolInfo().getUser().getNickname());
                carpoolinfo_json_list.add(carpoolinfo_json);
            }
            jsonObject.put("record",carpoolinfo_json_list.toArray());
        }
        return jsonObject;
    }
    private JSONObject getPostedCarpool(String uid){
        JSONObject result = new JSONObject();
        List<CarpoolInfo> list =CarpoolDAO.getInstance().getPostedCarpool(uid);
        if (list.size() == 0){
            result.put("code", 0);

        }else {
            result.put("code",1);
            List<JSONObject> carpool_list = new ArrayList<>();
            for (CarpoolInfo carpoolInfo:list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",carpoolInfo.getId());
                jsonObject.put("capacity",carpoolInfo.getCapacity());
                jsonObject.put("remain_seat",carpoolInfo.getRemainseat());
                jsonObject.put("datetime",carpoolInfo.getDateTime().toString());
                jsonObject.put("departure",carpoolInfo.getDeparture());
                jsonObject.put("destination",carpoolInfo.getDestination());
                jsonObject.put("price",carpoolInfo.getPrice());
                carpool_list.add(jsonObject);
            }
            result.put("record",carpool_list.toArray());
        }
        return result;
    }
}
