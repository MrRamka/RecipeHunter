package com.recipehunter.servlets;

import com.recipehunter.dao.RecipeUserDAO;
import com.recipehunter.entities.Recipe;
import com.recipehunter.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/saved")
public class SavedRecipesServlet extends HttpServlet {

    private RecipeUserDAO recipeUserDAO = new RecipeUserDAO();
    private static final int PAGE_RECIPE_AMOUNT = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("current_user");
        if (currentUser != null) {
            try {
                int currentPage = Integer.parseInt(req.getParameter("page"));
                int recipeAmount = recipeUserDAO.getAmountSavedRecipes(currentUser.getId()) / PAGE_RECIPE_AMOUNT + 1;

                List<Recipe> recipes = recipeUserDAO.getUsersSavedRecipes(currentUser.getId(),
                        PAGE_RECIPE_AMOUNT * 1, (currentPage - 1) * PAGE_RECIPE_AMOUNT);
                req.setAttribute("current_page", currentPage);
                req.setAttribute("page_amount", recipeAmount);
                req.setAttribute("recipes", recipes);

            } catch (SQLException e) {
                req.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error_page.jsp").forward(req, resp);
                return;
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/includes/saved.jsp").forward(req, resp);
    }

}
