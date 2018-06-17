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
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String delete = req.getParameter("del");
        if (delete != null) {
            logic.delete(req.getParameter("id"));
        }
        res.sendRedirect(String.format("%s/list.jsp",req.getContextPath()));
    }
}
