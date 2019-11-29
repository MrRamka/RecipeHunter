package com.recipehunter.servlets;

import com.recipehunter.dao.Stat;
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

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("current_user");
        if (user != null) {
            if (user.getRole().equals("Admin")) {
                req.setAttribute("is_admin", true);
                getServletContext().getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
            } else {
                req.setAttribute("is_admin", false);
                getServletContext().getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String password = req.getParameter("user_password");
        User user = (User) req.getSession().getAttribute("current_user");
        String hashedPassword = DigestUtils.md5Hex(password + user.getSalt());
        if (hashedPassword.equals(user.getPassword())) {
            Stat stat = new Stat();
            try {
                req.setAttribute("users_amount", stat.getUsersAmount());
                req.setAttribute("recipes_amount", stat.getRecipesAmount());
            } catch (SQLException e) {
                req.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error_page.jsp").forward(req, resp);
                return;
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/admin_page.jsp").forward(req, resp);
        } else {
            req.setAttribute("is_admin", true);
            req.setAttribute("status", false);
            getServletContext().getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
        }

    }
}
