package com.weblock.servlet;



import com.weblock.dao.UserDAO;
import com.weblock.dao.UserDAOImpl;
import com.weblock.modal.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDAO.loginUser(username, password);
            if (user != null) {
            	HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
