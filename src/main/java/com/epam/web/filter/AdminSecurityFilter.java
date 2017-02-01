package com.epam.web.filter;

import com.epam.web.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.web.entity.type.RoleType.ADMIN;

@WebFilter(urlPatterns = {"/jsp/admin/*"},
        initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class AdminSecurityFilter implements Filter {
    private String indexPath;
    private static final String USER_ATTR = "user";

    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter("INDEX_PATH");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        User user = (User) httpServletRequest.getSession().getAttribute(USER_ATTR);
        if (user == null || !ADMIN.equals(user.getRole())) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + indexPath);
        }
        filterChain.doFilter(request, response);
    }

    public void destroy() {
    }
}
