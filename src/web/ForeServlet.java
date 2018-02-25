package web;

import dao.CarpoolDAO;
import dao.UserManagementDAO;
import net.sf.json.JSONObject;
import pojo.CarpoolInfo;
import pojo.DateTime;
import pojo.UserReg;
import util.VerifyInput;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ForeServlet extends BaseServlet {
    public String home(HttpServletRequest req, HttpServletResponse res){
        //page
        return "/index.jsp";
    }
    public String forgetpassverify(HttpServletRequest req, HttpServletResponse res){
        //page
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        if (UserManagementDAO.getInstance().RecoverPass(username,email) == 1){
            req.setAttribute("USERNAME_TEMP",username);
            return "/recoverypass.jsp";
        }else {
            return "#/forgetpass.jsp?msg=Sorry, we cannot find realted username and the email";
        }
    }
    public String resetpass(HttpServletRequest req, HttpServletResponse res){
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        if (username!=null & UserManagementDAO.getInstance().setPass(username,password)){
            return "#/login.jsp?msg=Reset password success";
        }else {
            return "/forgetpass.jsp?msg=Reset password failed";
        }
    }
    public String login(HttpServletRequest req, HttpServletResponse res){
        //action
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int userID = dao.UserManagementDAO.getInstance().Login(username,password);
        String msg = "";
        switch (userID){
            case -1:
                msg = "No matching username/password";
                break;
            case -2:
                System.out.println("FETAL ERROR FROM LOGINHANDLER");
                msg="FETAL ERROR";
                break;
            default:
                //success
                msg = "Login success";
                req.getSession().setAttribute("uid",userID);
                return "@fore_home?msg="+msg;
        }
        return "#/login.jsp?msg="+msg;

    }
    public String register(HttpServletRequest req, HttpServletResponse res){
        //action
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");
        String cell = req.getParameter("cell");
        UserReg userReg = new UserReg(username,password,email,nickname,cell);
        String msg = UserManagementDAO.getInstance().Register(userReg);
        if(msg.equals("Register success")){
            return "@fore_home?msg="+msg;
        }
        return "#/login.jsp?msg="+msg;
    }
    public String search(HttpServletRequest req, HttpServletResponse res){
        //action
        //Search for new
        String departure = req.getParameter("from").toUpperCase();
        String destination = req.getParameter("to").toUpperCase();
        int passenger = Integer.parseInt(req.getParameter("passengers"));
        String date = req.getParameter("date");
        JSONObject jsonresult = new JSONObject();
        //ERROR CHECKING START
        //verify input param valid or not
        //ERROR CHECKING END
        departure = departure.toUpperCase();
        destination = destination.toUpperCase();
        /*
            从DAO类中获取符合上述信息的carpoo列表
         */
        List<CarpoolInfo> carpoolInfoList = CarpoolDAO.getInstance().searchAvaliableVehicle(departure,destination,passenger,date);
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
            int remainseat = current.getRemainseat();
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

        jsonresult.put("results",results.toArray());
        return "^"+jsonresult;
    }
    public String book(HttpServletRequest req, HttpServletResponse res){
        //action
        if(req.getSession().getAttribute("uid")==null){
            return "#/login.jsp?msg=Please login first";
        }
        String id = req.getParameter("bookid");
        String uid = req.getSession().getAttribute("uid").toString();
        String seats = req.getSession().getAttribute("seats").toString();
        Boolean success = CarpoolDAO.getInstance().bookCarpool(uid,id,seats);
        if(success){
            return "@user_carpool_ridelist?msg=Booking success";
        }else{
            return "^Booking failed";
        }
    }

}
