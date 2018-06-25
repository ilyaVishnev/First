package ru.job4j.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBStore implements Store {

    private static final DBStore dbStore = new DBStore();

    public static DBStore getDBStore() {
        return dbStore;
    }

    private PGPoolingDataSource source;

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


    private List<User> users = new CopyOnWriteArrayList();

    public void add(User user) {
        try (Connection connection = this.getDataSource().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("insert into myuser(id, name, login, email) values(?,?,?,?)");) {
            try {
                pstmt.setInt(1, user.getId());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getLogin());
                pstmt.setString(4, user.getEmail());
                pstmt.addBatch();
                pstmt.execute();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void update(User user) {
        try (Connection connection = this.getDataSource().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("update myuser set name = ?, login = ?, email = ? where id = ?");) {
            try {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getLogin());
                pstmt.setString(3, user.getEmail());
                pstmt.setInt(4, user.getId());
                pstmt.addBatch();
                pstmt.execute();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void delete(Integer index) {
        try (Connection connection = this.getDataSource().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("DELETE FROM myuser WHERE id=?");) {
            try {
                pstmt.setInt(1, index);
                pstmt.addBatch();
                pstmt.execute();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public List<User> findAll() {
        users.clear();
        try (Connection connection = this.getDataSource().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM myuser");) {
            try (ResultSet resultSet = pstmt.executeQuery();) {
                User user = null;
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setLogin(resultSet.getString(3));
                    user.setEmail(resultSet.getString(4));
                    user.setCreateDate(resultSet.getDate(5));
                    users.add(user);
                }

            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return users;
    }

    public User findById(Integer index) {
        User user = null;
        try (Connection connection = this.getDataSource().getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM myuser WHERE id=?");
        ) {
            pstmt.setInt(1, index);
            pstmt.addBatch();
            pstmt.execute();
            try (ResultSet resultSet = pstmt.executeQuery();) {
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setName(resultSet.getString(2));
                    user.setLogin(resultSet.getString(3));
                    user.setEmail(resultSet.getString(4));
                    user.setCreateDate(resultSet.getDate(5));
                }
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return user;
    }
}
