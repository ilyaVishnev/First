package ru.job4j.servlets;

import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class UsersServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getValidateService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=Windows-1251");
        Iterator<User> iterator = logic.findAll().iterator();
        PrintWriter printWriter = new PrintWriter(res.getOutputStream());
        StringBuilder stringBuilder = new StringBuilder("<table>");
        while (iterator.hasNext()) {
            User user = iterator.next();
            stringBuilder.append("<tr><td><p>" + "<form action=\"edit\" method=\"get\">" +
                    "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                    "<p><input type=\"submit\" value=\"Редактировать\" name=\"update\"> </p>" +
                    "</form></p>" + "<p>" + "<form action=\"list\" method=\"post\">" +
                    "<input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">" +
                    "<p><input type=\"submit\" value=\"Удалить\" name=\"del\"> </p>" +
                    "</form></p></td><td>" + user + "</td></tr>");
        }
        stringBuilder.append("</table>");
        printWriter.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" + stringBuilder +
                "\n" +
                "<form action=\"create\" method=\"get\">" +
                "<p><input type=\"submit\" value=\"Создать пользователя\" name=\"create\"> </p>" +
                "</form>" +
                "</body>\n" +
                "</html>");
        printWriter.flush();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String delete = req.getParameter("del");
        if (delete != null) {
            logic.delete(req.getParameter("id"));
        }
        doGet(req, res);
    }
}
