package com.thomas.filter;

import com.thomas.dao.model.User;
import com.thomas.services.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "SessionFilter", urlPatterns = "/*",dispatcherTypes = {DispatcherType.REQUEST})
public class SessionFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        AuthService authService = new AuthService();

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if (session == null) {
            String sessionId = req.getRequestedSessionId();
            if (sessionId != null) {
                try {
                    User u = authService.getUserBySessionID(sessionId);
                    if (u != null) {
                        session = req.getSession(true);
                        String newSessionId = session.getId();
                        authService.updateSessionId(u.getId(), newSessionId);
                        session.setAttribute("auth", u);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


        chain.doFilter(request, response);
    }
}