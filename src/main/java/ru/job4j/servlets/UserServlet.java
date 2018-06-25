package ru.job4j.servlets;

import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;


public class UserServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getValidateService();
    private String message = "";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        Iterator<User> iterator = logic.findAll().iterator();
        while (iterator.hasNext()) {
            res.getWriter().println(iterator.next());
        }
        res.getWriter().println(message);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
       // choseAction(req);
        doGet(req, res);
    }

/*    public void choseAction(HttpServletRequest req) {
        Enumeration action = req.getParameterNames();
        String name = (String) req.getParameter("name");
        String index = (String) req.getParameter("index");
        boolean end = true;
        while (action.hasMoreElements() && end) {
            switch ((String) action.nextElement()) {
                case "add":
                    logic.add(new User(name));
                    end = false;
                    break;
                case "delete":
                    message = logic.delete(index);
                    end = false;
                    break;
                case "update":
                    message = logic.update(index);
                    end = false;
                    break;
            }
        }
    }*/
}
