package com.recipehunter.servlets;

import com.recipehunter.utils.Db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Remove information from session and cookie
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("user") != null) {
            httpSession.setAttribute("user", null);
            String selectorValue = "";
            if (findCookie(req, "selector").getValue() != null)
                selectorValue = findCookie(req, "selector").getValue();
            Cookie cookieSelector = new Cookie("selector", "");
            Cookie cookieValidator = new Cookie("validator", "");
            resp.addCookie(cookieSelector);
            resp.addCookie(cookieValidator);
            req.setAttribute("login", Boolean.FALSE.toString());
            //Removing selector and validator from db
            try {
                Db db = new Db();
                Connection connection = db.getConnection();
                String remove_query = "DELETE FROM users_auth WHERE selector = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(remove_query);
                preparedStatement.setString(1,selectorValue);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/home");
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
