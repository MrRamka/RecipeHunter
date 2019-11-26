package com.recipehunter.servlets;

import com.recipehunter.dao.RecipeFindDAO;
import com.recipehunter.entities.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/recipes")
public class FindRecipesPage extends HttpServlet {
    private RecipeFindDAO recipeFindDAO = new RecipeFindDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/recipes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> checkbox = getCheckBoxes(req);
        try {
            List<Recipe> recipes = recipeFindDAO.getRecipiesByIngredients(checkbox);
            req.setAttribute("recipes", recipes);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/recipes.jsp").forward(req, resp);
    }
    private static Map<String, String> getCheckBoxes(HttpServletRequest request){
        Map<String, String> checkbox = new HashMap<>();
        checkbox.put("lemon", request.getParameter("lemon-check"));
        checkbox.put("chicken", request.getParameter("chicken-check"));
        checkbox.put("peach", request.getParameter("peach-check"));
        checkbox.put("cheese", request.getParameter("cheese-check"));
        checkbox.put("salt", request.getParameter("salt-check"));
        checkbox.put("pork", request.getParameter("pork-check"));
        checkbox.put("lime", request.getParameter("lime-check"));
        checkbox.put("banana", request.getParameter("banana-check"));
        checkbox.put("milk", request.getParameter("milk-check"));
        checkbox.put("rice", request.getParameter("rice-check"));
        checkbox.put("tomato", request.getParameter("tomato-check"));
        checkbox.put("butter", request.getParameter("butter-check"));
        checkbox.put("flour", request.getParameter("flour-check"));
        checkbox.put("onion", request.getParameter("onion-check"));
        checkbox.put("carrot", request.getParameter("carrot-check"));
        checkbox.put("egg", request.getParameter("egg-check"));
        checkbox.put("pasta", request.getParameter("pasta-check"));
        checkbox.put("apple", request.getParameter("apple-check"));
        checkbox.put("beef", request.getParameter("beef-check"));
        checkbox.put("turkey", request.getParameter("turkey-check"));
        checkbox.put("orange", request.getParameter("orange-check"));
        checkbox.put("potato", request.getParameter("potato-check"));
        checkbox.put("mushrooms", request.getParameter("mushrooms-check"));
        checkbox.put("cabbage", request.getParameter("cabbage-check"));
        checkbox.put("pepper", request.getParameter("pepper-check"));
        checkbox.put("garlic", request.getParameter("garlic-check"));
        checkbox.put("fish", request.getParameter("fish-check"));
        checkbox.put("lamb", request.getParameter("lamb-check"));
        return checkbox;
    }
}
