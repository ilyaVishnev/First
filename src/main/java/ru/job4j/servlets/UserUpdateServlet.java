package ru.job4j.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserUpdateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getValidateService();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=Windows-1251");
        User user = null;
        try {
            user = logic.findById(req.getParameter("id"));
        } catch (Exception ex) {
            ex.getMessage();
        }
        req.setAttribute("user", user);
        User observer = (User) req.getSession().getAttribute("myuser");
        req.setAttribute("myuser", observer.getRole());
        req.setAttribute("countryId", logic.getIdByCountry(user.getCountry()));
        req.getRequestDispatcher("/WEB-INF/views/updateUser.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = null;
        try {
            user = logic.findById(req.getParameter("id"));
        } catch (Exception ex) {
            ex.getMessage();
        }
        user.setName(req.getParameter("name"));
        user.setRole(req.getParameter("role"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setCountry(logic.getCountryById(Integer.parseInt(req.getParameter("country"))));
        user.setCity(req.getParameter("city"));
        String message = logic.update(user);
        req.setAttribute("error", message);
        doGet(req, res);
    }
}
