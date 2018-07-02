package ru.job4j.servlets;

import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.Iterator;

public class UsersController extends HttpServlet {

    private final ValidateService logic = ValidateService.getValidateService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=Windows-1251");
        HttpSession session = req.getSession();
        req.setAttribute("myusers", logic.findAll());
        req.setAttribute("myuser", req.getSession().getAttribute("myuser"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/listView.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String delete = req.getParameter("del");
        if (delete != null) {
            logic.delete(req.getParameter("id"));
        }
        res.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
