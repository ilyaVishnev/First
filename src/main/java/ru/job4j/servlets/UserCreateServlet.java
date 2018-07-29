package ru.job4j.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.models.City;
import ru.job4j.models.Country;
import ru.job4j.models.User;
import ru.job4j.models.ValidateService;
import sun.plugin.com.Dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class UserCreateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getValidateService();
    final JSONArray counryArray = new JSONArray();
    final JSONArray cityArray = new JSONArray();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/createUser.jsp").forward(req, res);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        User user = new User(req.getParameter("name"));
        user.setRole(req.getParameter("role"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setCountry(logic.getCountryById(Integer.parseInt(req.getParameter("country"))));
        user.setCity(req.getParameter("city"));
        try {
            logic.add(user);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        doGet(req, res);
    }
}
