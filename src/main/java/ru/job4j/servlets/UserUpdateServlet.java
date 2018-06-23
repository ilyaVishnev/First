package ru.job4j.servlets;

import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getValidateService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=Windows-1251");
        User user = null;
        PrintWriter printWriter = new PrintWriter(res.getOutputStream());
        try {
            user = logic.findById(req.getParameter("id"));
        } catch (Exception ex) {
            ex.getMessage();
        }
        printWriter.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<form action=\"edit\" method=\"post\">\n" +
                "    <p>Введите имя пользователя : <input type=\"text\" name=\"name\" value=" + user.getName() + "></p>\n" +
                "    <p>Введите логин пользователя : <input type=\"text\" name=\"login\" value=" + user.getLogin() + "></p>\n" +
                "    <p>Введите e-mail пользователя : <input type=\"text\" name=\"email\"  value=" + user.getEmail() + "></p>\n" +
                "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                "    <input type=\"submit\" value=\"Сохранить\" name=\"update\">\n" +
                "</form>" +
                "</body>\n" +
                "</html>");
        printWriter.flush();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User user = null;
        try {
            user = logic.findById(req.getParameter("id"));
        } catch (Exception ex) {
            ex.getMessage();
        }
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        logic.update(user);
        doGet(req, res);
    }
}
