package ru.job4j.servlets;

import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getValidateService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=Windows-1251");
        PrintWriter printWriter = new PrintWriter(res.getOutputStream());
        printWriter.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<form action=\"create\" method=\"post\">\n" +
                "    <p>Введите имя пользователя : <input type=\"text\" name=\"name\"></p>\n" +
                "    <p>Введите логин пользователя : <input type=\"text\" name=\"login\"></p>\n" +
                "    <p>Введите e-mail пользователя : <input type=\"text\" name=\"email\"></p>\n" +
                "    <input type=\"submit\" value=\"Создать\" name=\"create\">\n" +
                "</form>" +
                "</body>\n" +
                "</html>");
        printWriter.flush();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User user = new User(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        logic.add(user);
        doGet(req, res);
    }
}