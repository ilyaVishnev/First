package ru.job4j.models;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryStore implements Store {

    private static final MemoryStore memoryStore = new MemoryStore();
    private List<User> users = new CopyOnWriteArrayList();

    public static MemoryStore getMemoryStore() {
        return memoryStore;
    }


    @Override
    public void add(HttpServletRequest req) {
        users.add(new User((String) req.getParameter("name")));
    }

    @Override
    public boolean update(HttpServletRequest req){
        boolean result=true;
        try {
            findById(req);
        }catch (Exception ex){
            result=false;
        }
        users.get(Integer.parseInt(req.getParameter("index"))).setName((String) req.getParameter("name"));
        return result;
    }

    @Override
    public boolean delete(HttpServletRequest req)  {
        boolean result=true;
        try {
            findById(req);
        }catch (Exception ex){
            result=false;
        }
        users.remove(Integer.parseInt(req.getParameter("index")));
        return result;
    }

    @Override
    public List findAll(HttpServletRequest req) {
        return users;
    }

    @Override
    public User findById(HttpServletRequest req) throws MyException {
        User user = users.get(Integer.parseInt(req.getParameter("index")));
        if (user == null) {
            throw new MyException("there isn't such user");
        }
        return user;
    }
}
