package com.weblock.servlet;


import com.weblock.dao.UserDAO;
import com.weblock.dao.UserDAOImpl;
import com.weblock.modal.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            userDAO.registerUser(user);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
