package com.recipehunter.servlets;

import com.recipehunter.dao.IngredientDAO;
import com.recipehunter.dao.RecipeDAO;
import com.recipehunter.dao.RecipeFindDAO;
import com.recipehunter.entities.RecipeType;
import com.recipehunter.entities.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/create")
public class CreateRecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("current_user");
        if (user != null) {
            RecipeFindDAO recipeFindDAO = new RecipeFindDAO();
            try {
                List<RecipeType> recipeTypes = recipeFindDAO.getRecipesType();
                req.setAttribute("r_categories", recipeTypes);
            } catch (SQLException e) {
                getServletContext().getRequestDispatcher("/WEB-INF/views/error_page.jsp").forward(req, resp);
                return;
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/includes/create.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("current_user");
        if (user != null) {
            String recipeTitle = req.getParameter("recipe_name");
            int recipeType = Integer.parseInt(req.getParameter("recipe_type"));
            String recipeTime = req.getParameter("recipe_time");
            String recipeDirections = req.getParameter("recipe_steps");
            int ingredients_amount = Integer.parseInt(req.getParameter("ingredient_amount"));
            String name = "recipe_ingredient_name_";
            String amount = "recipe_ingredient_amount_";
            String amountType = "recipe_ingredient_amount_type_";

            RecipeDAO recipeDAO = new RecipeDAO();
            IngredientDAO ingredientDAO = new IngredientDAO();
            try {
                recipeDAO.addRecipe(recipeTitle, recipeType, recipeTime, recipeDirections, user.getId());
                int recipeId = recipeDAO.getRecipeIdByParams(recipeTitle);
                for (int i = 0; i < ingredients_amount; i++) {
                    String ingredient_name = req.getParameter(name + (i + 1));
                    double ingredient_amount = Double.parseDouble(req.getParameter(amount + (i + 1)));
                    String ingredient_amountType = req.getParameter(amountType + (i + 1));
                    ingredientDAO.addIngredient(recipeId, ingredient_name, ingredient_amount, ingredient_amountType);
                }

            } catch (SQLException | NumberFormatException e) {
                try {
                    ingredientDAO.deleteIngredientByRecipeId(recipeDAO.getRecipeIdByParams(recipeTitle));
                    recipeDAO.deleteRecipeIdByParams(recipeTitle);
                } catch (SQLException e1) {
                    resp.getWriter().write("Cant create recipe");
                    return;
                }
                resp.getWriter().write("Cant create recipe");
                return;
            }
            resp.getWriter().write("Recipe created successfully");
        }else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }

    }
}
