package web;

import dao.BookedCarpoolDAO;
import dao.CarpoolDAO;
import pojo.BookedCarpoolDetail;
import pojo.BookedCarpoolInfo;
import pojo.CarpoolInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CarpoolServlet extends BaseServlet {
    //jump to ride history
    public String ridelist(HttpServletRequest req, HttpServletResponse res) {
        String uid = req.getSession().getAttribute("uid").toString();
        List<BookedCarpoolInfo> allinfos = BookedCarpoolDAO.getInstance().getCarpoolInfoAsPassenger(uid);
        req.setAttribute("carpools",allinfos);
        return "/user/checkriding.jsp";
    }
    //jump to drive history
    public String drivelist(HttpServletRequest req, HttpServletResponse res) {
        String uid = req.getSession().getAttribute("uid").toString();
        List<CarpoolInfo> allinfo = CarpoolDAO.getInstance().getCarpoolInfoAsDriver(uid);
        req.setAttribute("carpools",allinfo);
        return "/user/checkdriving.jsp";
    }
    //get carpool detail
    public String detail(HttpServletRequest req, HttpServletResponse res){
        String uid = req.getSession().getAttribute("uid").toString();
        String ref = req.getParameter("ref");//As passenger
        String id = req.getParameter("id");//As driver
        if (ref != null){
            BookedCarpoolDetail bookedCarpoolDetail = BookedCarpoolDAO.getInstance().getBookedCarpoolDetailAsPassenger(ref);
            req.setAttribute("c",bookedCarpoolDetail);
            req.setAttribute("ref",ref);

        }else {
            BookedCarpoolDetail bookedCarpoolDetail = BookedCarpoolDAO.getInstance().getBookedCarpoolDetailAsDriver(id);
            req.setAttribute("c",bookedCarpoolDetail);
            req.setAttribute("id",id);
        }
        return "/user/bookedcarpooldetail.jsp";
    }
    //cancel carpool
    public String cancel(HttpServletRequest req, HttpServletResponse res) {
        return null;
    }

    //jump to post carpool info page
    public String postinfo(HttpServletRequest req, HttpServletResponse res) {
        Object driver = req.getSession().getAttribute("driver");
        if (driver == null) {
            return "@user_driver_verifydriver";
        }
        if (driver.toString().equals("0")) {
            return "@user_driver_addvehivleinfo";
        }
        req.setAttribute("msg","failed");
        String uid = req.getSession().getAttribute("uid").toString();
        String departure = req.getParameter("from");
        String destination = req.getParameter("to");
        String passenger = req.getParameter("passengers");
        String date = req.getParameter("date");
        String price = req.getParameter("price");
        Boolean success = CarpoolDAO.getInstance().storageCarpoolInfo(departure,destination,passenger,price,date,uid);
        if (success){
            req.setAttribute("msg","success");
        }
        return "@user_carpool_ridelist";
    }
    //add carpool information
    public String add(HttpServletRequest req, HttpServletResponse res) {

        return null;
    }


}
