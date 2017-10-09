package filter;

import core.DateTime;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

/**
 * Created by admin on 2017/8/22.
 */
public class IPFilter implements Filter {
    //random error exist
    //suspend for maintenance
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.println(System.nanoTime()+req.getRemoteAddr());
        try {
            if(!dao.IPAddrDAO.checkBlockIP(req.getRemoteAddr())) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
