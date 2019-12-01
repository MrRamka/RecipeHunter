package com.recipehunter.servlets;

import com.recipehunter.dao.UserDAO;
import com.recipehunter.entities.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editpass")
public class EditPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("current_user");
        if (user != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/edit_pass.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPass = req.getParameter("user_password");
        String newPass = req.getParameter("user_new_password");
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("current_user");
        if (user != null) {
            String hashPass = oldPass + user.getSalt();
            String hashedPass = DigestUtils.md5Hex(hashPass);
            if (hashedPass.equals(user.getPassword())) {
                String newHashPass = DigestUtils.md5Hex(newPass + user.getSalt());
                UserDAO userDAO = new UserDAO();
                try {
                    userDAO.updateUserPassword(user.getId(), newHashPass);
                    User userNewPass = userDAO.getUserById(user.getId());
                    httpSession.setAttribute("current_user", userNewPass);
//                    resp.setContentType("text/plain");
//                    resp.getWriter().write("Password updated successfully");
                    req.setAttribute("status", true);
                } catch (SQLException e) {
                    req.setAttribute("error", e);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/error_page.jsp").forward(req, resp);
                }
            } else {
//                resp.getWriter().println("Old password is incorrect");
                req.setAttribute("status", false);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/edit_pass.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);

        }

    }
}
