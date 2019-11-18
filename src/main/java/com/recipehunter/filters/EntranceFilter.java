package com.recipehunter.filters;


import com.recipehunter.utils.Db;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebFilter("/*")
public class EntranceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Checking user
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpRequest.getSession();
        if (httpSession.getAttribute("user") != null) {
            servletRequest.setAttribute("login", Boolean.TRUE.toString());
            servletRequest.setAttribute("user", httpSession.getAttribute("user"));

        } else {
            //Checking user by cookie
            Cookie selector = findCookie(httpRequest, "selector");
            Cookie validator = findCookie(httpRequest, "validator");
            if (selector != null && validator != null) {
                try {
                    Db db = new Db();
                    Connection connection = db.getConnection();
                    String auth_query = "SELECT name, validator FROM users_auth JOIN users ON user_id = users.id WHERE selector = ?";
                    PreparedStatement preparedStatementAuth = connection.prepareStatement(auth_query);
                    preparedStatementAuth.setString(1, selector.getValue());
                    ResultSet resultSet = preparedStatementAuth.executeQuery();

                    while (resultSet.next()){
                        String db_validator = resultSet.getString("validator");
                        String user_validator = DigestUtils.md5Hex(validator.getValue());
                        if (db_validator.equals(user_validator)){
                            HttpSession session = httpRequest.getSession();
                            session.setAttribute("user", resultSet.getString("name"));
                            servletRequest.setAttribute("login", Boolean.TRUE.toString());
                            servletRequest.setAttribute("user", httpSession.getAttribute("user"));
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
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
