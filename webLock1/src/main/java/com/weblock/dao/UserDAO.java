package com.weblock.dao;



import com.weblock.modal.User;
import java.sql.SQLException;

public interface UserDAO {
    void registerUser(User user) throws SQLException;
    User loginUser(String username, String password) throws SQLException;
}