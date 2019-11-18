package com.recipehunter.servlets;

import com.recipehunter.entities.UserRole;
import com.recipehunter.utils.Db;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

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
            String md5Hex = DigestUtils.md5Hex(newPass);
            Db db = new Db();
            Connection connection = db.getConnection();
            try {
                String query = "INSERT INTO users (name, email, password, role, salt) values( ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, md5Hex);
                preparedStatement.setString(4, UserRole.USER.getTitle());
                preparedStatement.setString(5, salt);
                preparedStatement.executeUpdate();
                req.setAttribute("status", Boolean.TRUE.toString());
            } catch (SQLException e) {
                req.setAttribute("status", Boolean.FALSE.toString());
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }
}
