package ru.job4j.models;

/**
 * Created by Пользователь on 26.07.2018.
 */
public class City {
    private int id;
    private String city;
    private int id_country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public int getId_country() {
        return id_country;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }
}
