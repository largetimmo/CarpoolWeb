package filter;

import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/8/22.
 */
public class UserManagementAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        System.out.println("1");
        if (req.getSession().getAttribute("uid")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            res.getWriter().print(jsonObject);
        }

    }

    @Override
    public void destroy() {

    }
}
