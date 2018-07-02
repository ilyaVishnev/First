package ru.job4j.servlets;

import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getValidateService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=Windows-1251");
        User user = null;
        PrintWriter printWriter = new PrintWriter(res.getOutputStream());
        String message = "";
        try {
            user = logic.findById(req.getParameter("id"));
        } catch (Exception ex) {
            ex.getMessage();
            message = ex.getMessage();
        }
        String choice = "";
        if (user.getRole().equals("user")) {
            choice = "    <option value=\"user\">user</option>\n" +
                    "    <option value=\"admin\">admin</option>\n";
        } else {
            choice = "    <option value=\"admin\">admin</option>\n" +
                    "    <option value=\"user\">user</option>\n";
        }
        User observer = (User) req.getSession().getAttribute("myuser");
        if (observer.getRole().equals("user")) {
            choice = "    <option value=\"user\">user</option>\n";
        }
        printWriter.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" + message +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<form action=\"edit\" method=\"post\">\n" +
                "    <p>Введите имя пользователя : <input type=\"text\" name=\"name\" value=" + user.getName() + "></p>\n" +
                "    <p>Введите роль пользователя : <select name=\"role\"></p>\n" +
                choice +
                "    </select>\n" +
                "    <p>Введите логин пользователя : <input type=\"text\" name=\"login\" value=" + user.getLogin() + "></p>\n" +
                "    <p>Введите пароль пользователя : <input type=\"text\" name=\"password\" value=" + user.getPassword() + "></p>\n" +
                "    <p>Введите e-mail пользователя : <input type=\"text\" name=\"email\"  value=" + user.getEmail() + "></p>\n" +
                "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                "    <input type=\"submit\" value=\"Сохранить\" name=\"update\">\n" +
                "</form>" +
                "</body>\n" +
                "</html>");
        printWriter.flush();
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
        logic.update(user);
        doGet(req, res);
    }
}
