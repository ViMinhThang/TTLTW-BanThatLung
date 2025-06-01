package com.thomas.filter;

import com.thomas.dao.model.User;
import com.thomas.services.PermissionService;
import com.thomas.services.UploadUserService;
import com.thomas.services.UsesUsageService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.ipAddress;

import java.io.IOException;

@WebFilter(filterName = "ActivityTrackerFilter", urlPatterns = "/*")
public class ActivityTrackerFilter implements Filter {
    UsesUsageService usesUsageService = new UsesUsageService();
    PermissionService permissionService = new PermissionService();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpSession session = rq.getSession(false);
        if (session != null && session.getAttribute("auth") != null) {
            User user = (User) session.getAttribute("auth");
            if (user.getId() != 0) {
                usesUsageService.trackUserActivityNotLogin(user.getId(), ((HttpServletRequest) request).getContextPath());

            }
            Boolean wasAdmin = (Boolean) session.getAttribute("wasAdmin");
            if (wasAdmin != null && wasAdmin && !permissionService.checkPermission("adminPage", user.getId(), "access")) {
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response;

                req.getSession().invalidate();
                res.sendRedirect("/login?msg=access_revoked");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}