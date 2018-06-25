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
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void update(User user) {
        users.set(user.getId(), user);
    }

    @Override
    public void delete(Integer index) {
        users.remove(findById(index));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Integer index) {
        return users.get(index);
    }
}
