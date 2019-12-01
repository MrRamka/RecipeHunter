package com.recipehunter.servlets;

import com.recipehunter.dao.RecipeDAO;
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

@WebServlet("/save_recipe")
public class SaveRecipeServlet extends HttpServlet {
    private RecipeDAO recipeDAO = new RecipeDAO();
    private RecipeUserDAO recipeUserDAO = new RecipeUserDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int recipeId = Integer.parseInt(req.getParameter("recipe_id"));
            System.out.println(recipeId);
            Recipe recipe = recipeDAO.getRecipeById(recipeId);
            if (recipe != null) {
                User currentUser = (User) req.getSession().getAttribute("current_user");

                if (currentUser != null) {
                    recipeUserDAO.addRecipeUser(currentUser.getId(), recipe.getId());
                    resp.getWriter().write("Recipe saved");
                } else {
                    resp.getWriter().write("Cant save recipe. You must log in to save recipes");
                }
            } else {
                resp.getWriter().write("Cant save recipe. Recipe doest exist");
            }
        } catch (SQLException e) {
            resp.getWriter().write("Cant save recipe (Maybe you saved this recipe)");

        }
    }

}
