package com.recipehunter.servlets;

import com.recipehunter.dao.RecipeUserDAO;
import com.recipehunter.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
    private RecipeUserDAO recipeUserDAO = new RecipeUserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int recipe_id = Integer.parseInt(req.getParameter("recipe_id"));
            HttpSession httpSession = req.getSession();
            User user = (User) httpSession.getAttribute("current_user");
            if (user != null) {
                recipeUserDAO.deleteSavedRecipe(user.getId(), recipe_id);

//                getServletContext().getRequestDispatcher("/WEB-INF/views/includes/edit_pass.jsp").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/WEB-INF/views/error_page.jsp").forward(req, resp);
            return;
        }

//        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }
}
