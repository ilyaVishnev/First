package ru.job4j.models;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateService {
    private static final ValidateService validateService = new ValidateService();
    //private final Store persistent = MemoryStore.getMemoryStore();
    private final Store persistent = DBStore.getDBStore();
    String message;
    private User myUser;


    public static ValidateService getValidateService() {
        return validateService;
    }

    public String getMessage() {
        return message;
    }

    public void add(User user) throws Exception {
        if (this.emailValid(user.getEmail())) {
            persistent.add(user);
        } else {
            throw new Exception("email is invalid");
        }
    }

    public String update(User user) {
        if (checkNumber(String.valueOf(user.getId())) && checkExistence(String.valueOf(user.getId()))) {
            if (this.emailValid(user.getEmail())) {
                persistent.update(user);
            } else {
                return "email is invalid";
            }
        }
        return message;
    }

    public String delete(String index) {
        if (checkNumber(index) && checkExistence(index)) {
            persistent.delete(Integer.parseInt(index));
        }
        return message;
    }

    public List<User> findAll() {
        return persistent.findAll();
    }

    public User findById(String index) throws Exception {
        if (checkNumber(index) && checkExistence(index)) {
            return persistent.findById(Integer.parseInt(index));
        }
        throw new Exception(message);
    }


    public boolean checkNumber(String indexS) {
        boolean result = true;
        int index;
        try {
            index = Integer.parseInt(indexS);
        } catch (Exception ex) {
            message = "index should be a number";
            result = false;
        }
        return result;
    }

    public boolean checkExistence(String indexS) {
        boolean result = true;
        try {
            User user = this.findAll().get(Integer.parseInt(indexS));
        } catch (IndexOutOfBoundsException ex) {
            message = "there isn't such user";
            result = false;
        }
        return result;
    }

    public User isCredential(String login, String password) {
        return persistent.isCredential(login, password);
    }

    public List<Country> getListCountries() {
        return persistent.getListOfCounries();
    }

    public List<City> getListCities() {
        return persistent.getListOfCities();
    }

    public int getIdByCountry(String country) {
        Country countryN = null;
        Iterator<Country> iterator = this.getListCountries().iterator();
        while (iterator.hasNext()) {
            if ((countryN = iterator.next()).getCountry().equals(country)) {
                break;
            }
        }
        return countryN.getId();
    }

    public String getCountryById(int id) {
        Country countryN = null;
        Iterator<Country> iterator = this.getListCountries().iterator();
        while (iterator.hasNext()) {
            if ((countryN = iterator.next()).getId() == id) {
                break;
            }
        }
        return countryN.getCountry();
    }

    public boolean emailValid(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
