package ru.job4j.models;

import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class DBStore implements Store {

    private static final DBStore dbStore = new DBStore();
    private static Logger log = Logger.getLogger(DBStore.class.getName());

    public static DBStore getDBStore() {
        return dbStore;
    }

    private PGPoolingDataSource source = this.getDataSource();

    private PGPoolingDataSource getDataSource() {
        if (source == null) {
            source = new PGPoolingDataSource();
            source.setDataSourceName("Logistica");
            source.setServerName("localhost");
            source.setPortNumber(5432);
            source.setDatabaseName("users");
            source.setUser("postgres");
            source.setPassword("pobeda");
            source.setMaxConnections(30);
        }
        return source;
    }


    public void add(User user) {
        try (Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("insert into myuser(id, name,role, login, password, email,country,city) values(?,?,?,?,?,?,?,?)");) {
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getRole());
            pstmt.setString(4, user.getLogin());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getCountry());
            pstmt.setString(8, user.getCity());
            pstmt.execute();
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        }
    }

    public void update(User user) {
        try (Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("update myuser set name = ?,role = ?, login = ?,password=?, email = ?,country = ?,city = ? where id = ?");) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getRole());
            pstmt.setString(3, user.getLogin());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getCountry());
            pstmt.setString(7, user.getCity());
            pstmt.setInt(8, user.getId());
            pstmt.execute();
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        }
    }

    public void delete(Integer index) {
        try (Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("DELETE FROM myuser WHERE id=?");) {
            pstmt.setInt(1, index);
            pstmt.execute();
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        }
    }

    public List<User> findAll() {
        List<User> users = new CopyOnWriteArrayList();
        try (Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM myuser");
             ResultSet resultSet = pstmt.executeQuery();
        ) {
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setRole(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setCreateDate(resultSet.getDate(7));
                user.setCountry(resultSet.getString(8));
                user.setCity(resultSet.getString(9));
                users.add(user);
            }
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        }
        return users;
    }

    public User findById(Integer index) {
        User user = null;
        try (Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM myuser WHERE id=?");
        ) {
            pstmt.setInt(1, index);
            pstmt.execute();
            try (ResultSet resultSet = pstmt.executeQuery();) {
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setRole(resultSet.getString(2));
                    user.setName(resultSet.getString(3));
                    user.setLogin(resultSet.getString(4));
                    user.setPassword(resultSet.getString(5));
                    user.setEmail(resultSet.getString(6));
                    user.setCreateDate(resultSet.getDate(7));
                    user.setCountry(resultSet.getString(8));
                    user.setCity(resultSet.getString(9));
                }
            } catch (Exception e) {
                connection.rollback();
                log.info(e.getMessage());
            }
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        }
        return user;
    }

    @Override
    public User isCredential(String login, String password) {
        User user = null;
        try (Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM myuser WHERE login=? AND password=?");
        ) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.execute();
            try (ResultSet resultSet = pstmt.executeQuery();) {
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setRole(resultSet.getString(2));
                    user.setName(resultSet.getString(3));
                    user.setLogin(resultSet.getString(4));
                    user.setPassword(resultSet.getString(5));
                    user.setEmail(resultSet.getString(6));
                    user.setCreateDate(resultSet.getDate(7));
                    user.setCountry(resultSet.getString(8));
                    user.setCity(resultSet.getString(9));
                }
            } catch (Exception e) {
                connection.rollback();
                log.info(e.getMessage());
            }
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        }
        return user;
    }

    public List<Country> getListOfCounries() {
        List<Country> countries = new CopyOnWriteArrayList();
        try (Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM countries");) {
            try (ResultSet resultSet = pstmt.executeQuery();) {
                Country country = null;
                while (resultSet.next()) {
                    country = new Country();
                    country.setId(resultSet.getInt(1));
                    country.setCountry(resultSet.getString(2));
                    countries.add(country);
                }
            } catch (Exception e) {
                connection.rollback();
                log.info(e.getMessage());
            }
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        }
        return countries;
    }

    public List<City> getListOfCities() {
        List<City> cities = new CopyOnWriteArrayList();
        try (Connection connection = source.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM cities");) {
            try (ResultSet resultSet = pstmt.executeQuery();) {
                City city = null;
                while (resultSet.next()) {
                    city = new City();
                    city.setId(resultSet.getInt(1));
                    city.setCity(resultSet.getString(2));
                    city.setId_country(resultSet.getInt(3));
                    cities.add(city);
                }
            } catch (Exception e) {
                connection.rollback();
                log.info(e.getMessage());
            }
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        }
        return cities;
    }
}
