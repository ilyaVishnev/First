package ru.job4j.servlets;

import ru.job4j.models.DBStore;
import ru.job4j.models.Store;
import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn extends HttpServlet {
    private final ValidateService logic = ValidateService.getValidateService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("myuser", null);
        req.getRequestDispatcher("/WEB-INF/views/signInView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int id = logic.isCredential(login, password);
        if (id != -1) {
            HttpSession session = req.getSession();
            User user = null;
            try {
                user = logic.findById(String.valueOf(id));
            } catch (Exception ex) {
                ex.getMessage();
            }
            session.setAttribute("myuser", user);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "dont have such user");
            doGet(req, resp);
        }
    }
}
