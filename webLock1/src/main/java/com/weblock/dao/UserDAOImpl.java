package com.weblock.dao;


import com.weblock.modal.User;
import com.weblock.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static final String INSERT_USER_SQL = "INSERT INTO users (username, password) VALUES (?, ?)";
    private static final String SELECT_USER_SQL = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";

    @Override
    public void registerUser(User user) throws SQLException {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public User loginUser(String username, String password) throws SQLException {
        User user = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        }
        return user;
    }
}
