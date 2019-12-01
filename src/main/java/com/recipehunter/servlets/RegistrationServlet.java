package com.recipehunter.servlets;

import com.recipehunter.dao.UserDAO;
import com.recipehunter.entities.User;
import com.recipehunter.entities.UserRole;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Getting information from user
        String name = req.getParameter("user_name");
        String email = req.getParameter("user_email");
        String pass = req.getParameter("user_pass");
        String conf_pass = req.getParameter("conf_user_pass");
        String salt = String.valueOf(Instant.now().toString().hashCode());
        //Adding new user to db
        if (pass.equals(conf_pass)) {
            String newPass = pass + salt;
            String md5HexPassword = DigestUtils.md5Hex(newPass);

            try {
                userDAO.addUser(name, email, md5HexPassword, UserRole.USER.getTitle(), salt);
                HttpSession session = req.getSession();
                UserDAO userDAO = new UserDAO();
                User current_user = userDAO.getUserByEmail(email);
                session.setAttribute("current_user", current_user);
                resp.sendRedirect("/home");
                return;
            } catch (SQLException e) {
                req.setAttribute("status", Boolean.FALSE.toString());
            }

        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }
}
