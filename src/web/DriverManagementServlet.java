package web;

import dao.VehicleOwnerInfoDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverManagementServlet extends BaseServlet {
    public String verifydriver(HttpServletRequest req, HttpServletResponse res) {
        String uid = req.getSession().getAttribute("uid").toString();
        VehicleOwnerInfoDAO vehicleOwnerInfoDAO = VehicleOwnerInfoDAO.getInstance();
        if (vehicleOwnerInfoDAO.verifyCarpoolOwner(uid)) {
            req.getSession().setAttribute("driver", "1");
        } else {
            req.getSession().setAttribute("driver", "0");
        }
        return "@user_carpool_postinfo";
    }

    public String addvehivleinfo(HttpServletRequest req, HttpServletResponse res) {
        return null;
    }

    public String add(HttpServletRequest req, HttpServletResponse res) {
        String platenum = req.getParameter("platenum");
        String vehicle = req.getParameter("vehicle");
        String uid = req.getSession().getAttribute("uid").toString();
        VehicleOwnerInfoDAO vehicleOwnerInfoDAO = VehicleOwnerInfoDAO.getInstance();
        if (vehicleOwnerInfoDAO.AddVehicleOwner(uid, vehicle, platenum)) {
            req.setAttribute("msg", "Add vehicle information success");
        } else {
            req.setAttribute("msg", "Add vehicle information fail");
        }
        return "@user_user_profile";
    }

    public String updatevehicleinfo(HttpServletRequest req, HttpServletResponse res) {
        return null;

    }

    public String update(HttpServletRequest req, HttpServletResponse res) {
        String msg = req.getAttribute("msg").toString();
        if (msg.contains("success")) {
            req.setAttribute("msg", "Update message success");
        } else {
            req.setAttribute("msg", "Update message failed");
        }
        return "@user_user_profile";
    }
}