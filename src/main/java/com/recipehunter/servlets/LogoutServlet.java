package com.recipehunter.servlets;

import com.recipehunter.dao.UserAuthDAO;
import com.recipehunter.entities.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private UserAuthDAO userAuthDAO = new UserAuthDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Remove information from session and cookie
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("current_user") != null) {
            User current_user = (User) httpSession.getAttribute("current_user");
            httpSession.setAttribute("current_user", null);
            Cookie cookieSelector = findCookie(req, "selector");
            Cookie cookieValidator = findCookie(req,"validator");
            if (cookieSelector != null){
                cookieSelector.setMaxAge(0);
                resp.addCookie(cookieSelector);
            }
            if (cookieValidator != null){
                cookieValidator.setMaxAge(0);
                resp.addCookie(cookieValidator);
            }

            req.setAttribute("login", Boolean.FALSE.toString());

            try {

                userAuthDAO.delete(current_user.getId());
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
