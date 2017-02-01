package com.epam.web.filter;

import com.epam.web.entity.User;
import com.epam.web.entity.type.RoleType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"}, servletNames = {"Controller"})
public class RoleAssigningFilter implements Filter {
    private static final String USER_ATTR = "user";

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        User user = (User) httpServletRequest.getSession().getAttribute(USER_ATTR);
        if (user == null || user.getRole() == null) {
            user = new User();
            user.setRole(RoleType.GUEST);
            httpServletRequest.getSession().setAttribute(USER_ATTR, user);
        }
        filterChain.doFilter(request, response);
    }

    public void destroy() {
    }
}
