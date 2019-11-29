package com.recipehunter.servlets;

import com.recipehunter.dao.RecipeFindDAO;
import com.recipehunter.dao.RecipeUserDAO;
import com.recipehunter.entities.Recipe;
import com.recipehunter.entities.RecipeUser;
import com.recipehunter.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private RecipeFindDAO recipeFindDAO = new RecipeFindDAO();
    private RecipeUserDAO recipeUserDAO = new RecipeUserDAO();
    private static final int PAGE_RECIPE_AMOUNT = 5;
    private int pageAmount;
    private List<Recipe> recipes;
    private int currentPage = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("current_user");
        if (currentUser != null) {
            try {
                recipes = recipeUserDAO.getUsersSavedRecipes(currentUser.getId());
                req.setAttribute("recipes", recipes);
            } catch (SQLException e) {
                req.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error_page.jsp").forward(req, resp);
                return;
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }
}
