package ru.job4j.servlets;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.job4j.models.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Пользователь on 18.07.2018.
 */
public class PeopleServlet extends HttpServlet {

    ObjectMapper mapper = new ObjectMapper();
    ConcurrentHashMap<Integer, Person> map = new ConcurrentHashMap<Integer, Person>();
    final JSONArray jsonArray = new JSONArray();
    static int index = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/index.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String next;
        StringBuilder stringBuilder = new StringBuilder();
        while ((next = reader.readLine()) != null) {
            stringBuilder.append(next);
        }
        Person person = mapper.readValue(stringBuilder.toString(), Person.class);
        JSONObject jsonObj = new JSONObject();
        JSONObject jsonSend = new JSONObject();
        jsonObj.put("name", person.getName());
        jsonObj.put("firstName", person.getFirstName());
        jsonObj.put("sex", person.getSex());
        jsonObj.put("description", person.getDescription());
        map.put(index++, person);
        jsonArray.add(jsonObj);
        jsonSend.put("array", jsonArray);
        resp.setContentType("application/json");
        mapper.writeValue(resp.getOutputStream(), jsonSend);
        doGet(req, resp);
    }
}
