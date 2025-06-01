package com.thomas.filter;

import com.thomas.dao.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter(filterName = "SEOUrlFilter", urlPatterns = "/*")
public class SEOUrlFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());
        //VD: /belt/5/variant/10
        if (path.matches("^/belt/\\d+/variant/\\d+$")) {
            String[] parts = path.split("/");// ["", "belt", "5", "variant", "10"]
            String beltId = parts[2];
            String variantId = parts[4];

            String newUrl = "/productDetails?beltId=" + beltId + "&variantId=" + variantId;
            RequestDispatcher dispatcher = request.getRequestDispatcher(newUrl);
            dispatcher.forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}
