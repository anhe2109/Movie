package com.mewie.mewie;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepolmpl implements UserRepo {

    private final String url = "jdbc:mysql://gruppe-a.cnjchjz7nynx.eu-central-1.rds.amazonaws.com:3306/mewie?useSSL=false";
    private final String username = "rewt";
    private final String password = "supersecurepassword";

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int index) {
        return false;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getuser(int index) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String stringSelect = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(stringSelect);

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("users_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                users.add(new User(id, username, password));
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
