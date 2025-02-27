package com.arvin.megacitycab.dao;

import com.arvin.megacitycab.model.base.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User login(User user) throws SQLException;
    User addUser(User user) throws SQLException;
    List<User> getAllUsers(int type) throws SQLException;
    User getUserById(int id) throws SQLException;
    User updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
}
