
package com.recipehunter.servlets;

import com.recipehunter.dao.UserAuthDAO;
import com.recipehunter.dao.UserDAO;
import com.recipehunter.entities.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    private UserAuthDAO userAuthDAO = new UserAuthDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("user_email");
            String password = req.getParameter("user_password");
            String remember = req.getParameter("remember_me");

            User currentUser = userDAO.getUserByEmail(email);
            if(currentUser != null){
                String md5HexPassword = password + currentUser.getSalt();
                md5HexPassword= DigestUtils.md5Hex(md5HexPassword);
                if(currentUser.getPassword().equals(md5HexPassword)){
                    HttpSession session = req.getSession();
                    session.setAttribute("current_user", currentUser);

                    if (remember != null && remember.equals("on")){
                        String selector = DigestUtils.md5Hex(email + currentUser.getSalt());
                        String rawValidator = DigestUtils.md5Hex(md5HexPassword + currentUser.getSalt());
                        String validator = DigestUtils.md5Hex(rawValidator);
                        Cookie cookieSelector = new Cookie("selector", selector);
                        Cookie cookieValidator = new Cookie("validator", rawValidator);
                        cookieSelector.setMaxAge(60 * 60 * 24 * 14);
                        cookieValidator.setMaxAge(60 * 60 * 24 * 14);
                        resp.addCookie(cookieSelector);
                        resp.addCookie(cookieValidator);
                        userAuthDAO.addAuth(selector, validator, currentUser.getId());
                    }
                    resp.sendRedirect("/home");
                }
                else {
                    resp.sendRedirect("/login");
                }
            }
        } catch (SQLException e) {
            resp.sendRedirect("/login");
        }

    }
}
