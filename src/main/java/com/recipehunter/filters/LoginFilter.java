package com.recipehunter.filters;

import com.recipehunter.utils.Db;
import org.apache.commons.codec.digest.DigestUtils;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebFilter("/login")
public class LoginFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
            //Getting information from user form
            String email = servletRequest.getParameter("user_email");
            String password = servletRequest.getParameter("user_password");
            String remember = servletRequest.getParameter("remember_me");
            //Getting connection to db
            Db db = new Db();
            Connection connection = db.getConnection();
            try {
                //Getting info from db
                String query = "SELECT id, name, salt, password FROM users WHERE email = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String newPass = password + resultSet.getString("salt");
                    String md5Hex = DigestUtils.md5Hex(newPass);
                    //Checking passwords
                    if (md5Hex.equals(resultSet.getString("password"))) {
                        servletRequest.setAttribute("status", resultSet.getString("name"));
                        HttpSession session = httpRequest.getSession();
                        session.setAttribute("user", resultSet.getString("name"));
                        servletRequest.setAttribute("login", Boolean.TRUE.toString());

                        //Set cookie for remember me
                        if (remember != null && remember.equals("on")) {
                            String selector = DigestUtils.md5Hex(email + resultSet.getString("salt"));
                            String rawValidator = DigestUtils.md5Hex(md5Hex + resultSet.getString("salt"));
                            String validator = DigestUtils.md5Hex(rawValidator);
                            Cookie cookieSelector = new Cookie("selector", selector);
                            Cookie cookieValidator = new Cookie("validator", rawValidator);
                            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                            httpServletResponse.addCookie(cookieSelector);
                            httpServletResponse.addCookie(cookieValidator);

                            //Adding selector and validator to db
                            String cookieQuery = "INSERT INTO users_auth (selector, validator, user_id) values(?, ?, ?)";
                            PreparedStatement preparedStatementCookieAuth = connection.prepareStatement(cookieQuery);
                            preparedStatementCookieAuth.setString(1, selector);
                            preparedStatementCookieAuth.setString(2, validator);
                            preparedStatementCookieAuth.setString(3, resultSet.getString("id"));
                            preparedStatementCookieAuth.executeUpdate();

                        }
                    }
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            servletRequest.setAttribute("status", Boolean.FALSE.toString());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
