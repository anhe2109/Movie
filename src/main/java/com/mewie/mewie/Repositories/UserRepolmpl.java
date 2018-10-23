package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepolmpl extends JdbcFix implements UserRepo {


    @Override
    public boolean createUser(User user) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringInsert = "INSERT INTO users VALUES (default, '" + user.getUsername() + "', ' " + user.getPassword() + "') ;";
            statement.execute(stringInsert);
            closeConnection(connection);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(int index) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringDelete = "DELETE FROM users WHERE users_id=" + index + ";";
            statement.execute(stringDelete);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringUpdate = "SELECT FROM movies WHERE users_id =" + user + ";";
            statement.execute(stringUpdate);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUser(int index) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        try {
            connection = getConnection();
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
