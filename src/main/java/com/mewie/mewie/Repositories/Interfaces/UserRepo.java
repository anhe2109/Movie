package com.mewie.mewie.Repositories.Interfaces;

import com.mewie.mewie.Beans.User;

import java.util.List;

public interface UserRepo {

    boolean createUser(User user);
    boolean deleteUser(int index);
    boolean updateUser(User user);
    User getUser(int index);
    List<User> getUsers();
}
