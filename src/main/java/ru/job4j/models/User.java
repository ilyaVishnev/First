package ru.job4j.models;

import java.util.Date;

public class User {
    private int id;
    private static int count = 1;
    private String name;
    private String role;
    private String login;
    private String password;
    private String email;
    private Date createDate;

    public User(String name) {
        this.name = name;
        this.id = count++;
        createDate = new Date();
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return this.getLogin().equals(other.getLogin()) && this.getPassword().equals(other.getPassword());
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
