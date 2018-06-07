package ru.job4j.models;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Store {
    public void add(HttpServletRequest req);

    public boolean update(HttpServletRequest req);

    public boolean delete(HttpServletRequest req);

    public List findAll(HttpServletRequest req);

    public User findById (HttpServletRequest req) throws Exception;
}
