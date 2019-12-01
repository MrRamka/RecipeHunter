package com.recipehunter.servlets;

import com.recipehunter.dao.UserDAO;
import com.recipehunter.entities.User;
import com.recipehunter.entities.UserRole;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;

@WebServlet("/vklogin")
public class VKOAuthServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            String client_id = "7229885";
            String secret_key = "z1mYxziH9UWDsbqRKGxM";
            String url = "https://oauth.vk.com/access_token?client_id=" + client_id + "&client_secret=" + secret_key
                    + "&redirect_uri=http://localhost:8080/vklogin&v=5.103&code=" + code;
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            String content = getContent(url);
            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(content);
                String email = (String) jsonObject.get("email");
                long user_id = (long) jsonObject.get("user_id");
                String access_token = (String) jsonObject.get("access_token");
                String url2 = " https://api.vk.com/method/users.get?uids=" +
                        user_id + "&fields=uid,first_name,last_name" +
                        "&access_token=" + access_token + "&v=5.103";
                String content2 = getContent(url2);

                JSONObject jsonObject2 = (JSONObject) jsonParser.parse(content2);
                JSONArray jsonArray = (JSONArray) jsonObject2.get("response");
                JSONObject jsonObject3 = (JSONObject) jsonArray.get(0);
                String name = (String) jsonObject3.get("first_name");
                String surname = (String) jsonObject3.get("last_name");
                String salt = String.valueOf(Instant.now().toString().hashCode());
                String newPass = DigestUtils.md5Hex(String.valueOf(user_id)) + name + DigestUtils.md5Hex(surname) + salt;
                String hashedPass = DigestUtils.md5Hex(newPass);
                User user = userDAO.getUserByEmail(email);
                HttpSession session = req.getSession();
                if (user == null) {
                    userDAO.addUser(name, email, hashedPass, UserRole.USER.getTitle(), salt);
                    User current_user = userDAO.getUserByEmail(email);
                    session.setAttribute("current_user", current_user);
                    resp.sendRedirect("/home");
                    return;
                }else{
                    session.setAttribute("current_user", user);
                    resp.sendRedirect("/home");
                    return;
                }
            } catch (ParseException | SQLException e) {
                req.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/WEB-INF/views/error_page.jsp").forward(req, resp);
                return;
            }

        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }


    private String getContent(String url) {

        HttpURLConnection connection = null;
        try {
            URL obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return content.toString();
    }
}
