package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.User;

import java.util.List;

public interface UserRepo {

    boolean createUser(User user);
    boolean deleteUser(int index);
    User updateUser(User user);
    User getuser(int index);
    List<User> getUsers();
}
