package com.recipehunter.filters;


import com.recipehunter.dao.UserAuthDAO;
import com.recipehunter.dao.UserDAO;
import com.recipehunter.entities.User;
import com.recipehunter.entities.UserAuth;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;

@WebFilter("/*")
public class EntranceFilter implements Filter {
    private UserDAO userDAO = new UserDAO();
    private UserAuthDAO userAuthDAO = new UserAuthDAO();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Checking user
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpRequest.getSession();
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (httpSession.getAttribute("current_user") != null) {
            servletRequest.setAttribute("login", Boolean.TRUE.toString());
            servletRequest.setAttribute("user", ((User) httpSession.getAttribute("current_user")).getName());
        } else {
            //Checking user by cookie
            Cookie selector = findCookie(httpRequest, "selector");
            Cookie validator = findCookie(httpRequest, "validator");
            if (selector != null && validator != null) {
                try {
                    UserAuth userAuth = userAuthDAO.getUserAuthBySelector(selector.getValue());
                    String userValidator = DigestUtils.md5Hex(validator.getValue());

                    if (userAuth.getValidator().equals(userValidator)) {
                        User currentUser = userDAO.getUserById(userAuth.getUserId());
                        httpSession.setAttribute("current_user", currentUser);
                        String newSelector = DigestUtils.md5Hex(currentUser.getName() + String.valueOf(Instant.now()) + currentUser.getSalt());
                        String newRawValidator = DigestUtils.md5Hex(currentUser.getEmail() + String.valueOf(Instant.now()) + currentUser.getSalt());
                        String hashedValidator = DigestUtils.md5Hex(newRawValidator);
                        selector.setValue(newSelector);
                        validator.setValue(newRawValidator);
                        httpServletResponse.addCookie(selector);
                        httpServletResponse.addCookie(validator);
                        userAuthDAO.delete(userAuth.getUserId());
                        userAuthDAO.addAuth(newSelector, hashedValidator, userAuth.getUserId());
                        servletRequest.setAttribute("user", currentUser.getName());
                    } else {
                        servletRequest.setAttribute("login", Boolean.FALSE.toString());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error Code: " + e.getErrorCode());
//                    filterChain.doFilter(servletRequest, servletResponse);
//                    httpServletResponse.sendRedirect("/login");
                }

            }

        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

    private static Cookie findCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

}
