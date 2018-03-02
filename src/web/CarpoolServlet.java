package web;

import dao.BookedCarpoolDAO;
import dao.CarpoolDAO;
import dao.VehicleOwnerInfoDAO;
import pojo.*;
import util.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CarpoolServlet extends BaseServlet {
    //jump to ride history
    //page
    public String ridelist(HttpServletRequest req, HttpServletResponse res) {
        String uid = req.getSession().getAttribute("uid").toString();
        List<BookedCarpoolInfo> allinfos = BookedCarpoolDAO.getInstance().getCarpoolInfoAsPassenger(uid);
        req.setAttribute("carpools",allinfos);
        return "/user/checkriding.jsp";
    }
    //jump to drive history
    //page
    public String drivelist(HttpServletRequest req, HttpServletResponse res) {
        String uid = req.getSession().getAttribute("uid").toString();
        List<CarpoolInfo> allinfo = CarpoolDAO.getInstance().getCarpoolInfoAsDriver(uid);
        req.setAttribute("carpools",allinfo);
        return "/user/checkdriving.jsp";
    }
    //get carpool detail
    //page
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

    /**
     * carpool detail entrance
     * will identify user is passenger or driver
     * @param req
     * @param res
     * @return
     */
    public String detailverify(HttpServletRequest req, HttpServletResponse res){
        String uid = req.getSession().getAttribute("uid").toString();
        String cid = req.getParameter("cid");
        if(CarpoolDAO.getInstance().driverVerify(uid,cid)){
            //driver
            return "@user_carpool_detail?id="+cid;
        }
        if(BookedCarpoolDAO.getInstance().passengerVerify(uid,cid)){
            //passenger
            return "@user_carpool_detail?ref="+cid;
        }
        return "@user_carpool_ridelist?msg=invalid request";

    }
    //cancel carpool
    public String cancel(HttpServletRequest req, HttpServletResponse res) {
        String uid = req.getSession().getAttribute("uid").toString();

        return null;
    }

    //jump to post carpool info page
    public String postinfo(HttpServletRequest req, HttpServletResponse res) {
        if(VehicleOwnerInfoDAO.getInstance().verifyCarpoolOwner(req.getSession().getAttribute("uid").toString())){
            //have submited vehicle info
            return "/user/postinfo.jsp";
        }else{
            return "#/user/verifydriver.jsp?msg=please provide your vehicle information first";
        }
    }
    //add carpool information
    public String add(HttpServletRequest req, HttpServletResponse res) {
        String uid = req.getSession().getAttribute("uid").toString();
        String dep = req.getParameter("departure");
        String des = req.getParameter("destination");
        String price = req.getParameter("price");
        String date = req.getParameter("date");
        String seat = req.getParameter("seat");
        DateTime date_obj = new DateTime(date);
        CarpoolInfo carpoolInfo = new CarpoolInfo();
        VehicleOwnerInfo vehicleOwnerInfo = new VehicleOwnerInfo();
        vehicleOwnerInfo.setUid(Integer.parseInt(uid));
        carpoolInfo.setUser(vehicleOwnerInfo);
        carpoolInfo.setFrom(dep);
        carpoolInfo.setTo(des);
        carpoolInfo.setCapacity(Integer.parseInt(seat));
        carpoolInfo.setPrice(Integer.parseInt(price));
        carpoolInfo.setDateTime(date_obj);
        CarpoolDAO.getInstance().addCarpool(carpoolInfo);
        return "@user_carpool_drivelist";
    }


}
