package com.recipehunter.servlets;


import com.recipehunter.dao.RecipeDAO;
import com.recipehunter.entities.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/recipe/*")
public class RecipeServlet extends HttpServlet {
    private RecipeDAO recipeDAO = new RecipeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeId = getRecipeId(req.getRequestURI());
        if (recipeId != -1) {
            try {
                Recipe recipe = recipeDAO.getRecipeById(recipeId);
                if (recipe != null) {
                    req.setAttribute("recipe", recipe);
                }else {
                    req.setAttribute("error", true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("error", true);
                System.out.println(e.getMessage() + ">>>" + e.getErrorCode());
            }
        }else {
            req.setAttribute("error", true);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/recipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/recipe.jsp").forward(req, resp);
    }

    private int getRecipeId(String path) {
        String[] params = path.split("/");
        int recipe_id = -1;
        try {
            recipe_id = Integer.valueOf(params[2]);
        } catch (NumberFormatException e) {
            return recipe_id;
        }
        return recipe_id;
    }
}
