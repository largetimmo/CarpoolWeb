package web;

import core.CarpoolInfo;
import core.DateTime;
import dao.CarpoolDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
/**
 * Created by admin on 2017/8/15.
 */
public class SearchingHandler extends HttpServlet{
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        /*
        从浏览器提交的表单中获取出发地，到达地，时间，人数
         */
        String departure = req.getParameter("from").toLowerCase();
        String destination = req.getParameter("to").toLowerCase();
        int passenger = Integer.parseInt(req.getParameter("passengers"));
        String date = req.getParameter("date");

        /*
            从DAO类中获取符合上述信息的carpoo列表
         */
        List<CarpoolInfo> carpoolInfoList = CarpoolDAO.searchAvaliableVehicle(departure,destination,passenger,date);
        List<JSONObject> results = new ArrayList<>();

        for(CarpoolInfo current: carpoolInfoList){
            /*
                对于每个carpoo信息整合成json对象
                然后加入到results列表中
             */
            int carpooID = current.getId();
            String nickname = current.getUser().getNickname();
            String userlevel = Integer.toString(current.getUser().getUserlevel());
            String vehicle = current.getUser().getVehicle();
            String price = Integer.toString(current.getPrice());
            String capacity = Integer.toString(current.getCapacity());
            DateTime dateTime = current.getDateTime();
            String remainseat = current.getRemainseat();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",carpooID);
            jsonObject.put("nickname",nickname);
            jsonObject.put("userlevel",userlevel);
            jsonObject.put("vehicle",vehicle);
            jsonObject.put("price",price);
            jsonObject.put("capacity",capacity);
            jsonObject.put("date",dateTime.toString());
            jsonObject.put("remainseat",remainseat);
            results.add(jsonObject);
        }
        /*
            设置返回数据格式
         */
        req.getSession().setAttribute("seats",passenger);
        res.setContentType("text/html");
        JSONObject jsonresult = new JSONObject();
        jsonresult.put("results",results.toArray());
        System.out.print(jsonresult);
        res.getWriter().print(jsonresult);

    }
}
