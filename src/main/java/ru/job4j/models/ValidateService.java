package ru.job4j.models;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

public class ValidateService {
    private static final ValidateService validateService = new ValidateService();
    private final Store persistent = MemoryStore.getMemoryStore();
    private String message = "";
    private static final Logger Log = LoggerFactory.getLogger(ValidateService.class);


    public static ValidateService getValidateService() {
        return validateService;
    }


    public void add(HttpServletRequest req) {
        if (checkNumber(req)) {
            persistent.add(req);
        }
    }

    public boolean update(HttpServletRequest req) {
        boolean result = true;
        if (!checkNumber(req)) {
            result = false;
        }
        if (!persistent.update(req)) {
            result = false;
            Log.error("there isn't such user");
        }
        return result;
    }

    public boolean delete(HttpServletRequest req) {
        boolean result = true;
        if (!persistent.delete(req)) {
            result = false;
            Log.error("there isn't such user");
        }
        return result;
    }

    public List findAll(HttpServletRequest req) {
        return persistent.findAll(req);
    }

    public User findById(HttpServletRequest req) throws Exception {
        if (!checkNumber(req)) {
            throw new Exception();
        }
        return persistent.findById(req);
    }


    public boolean checkNumber(HttpServletRequest req) {
        boolean result = true;
        int index;
        try {
            index = Integer.parseInt(req.getParameter("index"));
        } catch (Exception ex) {
            Log.error("index should be a number");
            result = false;
        }
        return result;
    }

    public String getMessage() {
        return message;
    }
}
