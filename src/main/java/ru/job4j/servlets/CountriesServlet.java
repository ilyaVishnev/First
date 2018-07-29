package ru.job4j.servlets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.job4j.models.City;
import ru.job4j.models.Country;
import ru.job4j.models.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Пользователь on 27.07.2018.
 */
public class CountriesServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getValidateService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final JSONArray countryArray = new JSONArray();
        final JSONArray cityArray = new JSONArray();
        JSONObject jsonSend = new JSONObject();
        Iterator<Country> iterator = logic.getListCountries().iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObj = new JSONObject();
            Country country = iterator.next();
            jsonObj.put("id", country.getId());
            jsonObj.put("country", country.getCountry());
            countryArray.add(jsonObj);
        }
        jsonSend.put("countryArray", countryArray);
        Iterator<City> iterator1 = logic.getListCities().iterator();
        while (iterator1.hasNext()) {
            JSONObject jsonObj = new JSONObject();
            City city = iterator1.next();
            jsonObj.put("id", city.getId());
            jsonObj.put("city", city.getCity());
            jsonObj.put("Id_country", city.getId_country());
            cityArray.add(jsonObj);
        }
        jsonSend.put("cityArray", cityArray);
        resp.getWriter().print(jsonSend);
    }
}
