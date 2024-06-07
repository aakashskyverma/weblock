package com.weblock.servlet;

import com.weblock.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        OutputStream outputStream = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("SELECT file_content FROM encrypted_files ORDER BY id DESC LIMIT 1");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Blob fileContent = resultSet.getBlob("file_content");
                String fileName = "encrypted_file.enc";

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

                outputStream = response.getOutputStream();
                outputStream.write(fileContent.getBytes(1, (int) fileContent.length()));
            } else {
                response.getWriter().println("No encrypted files found.");
            }
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new ServletException("Error closing resources: " + e.getMessage());
            }
        }
    }
}
