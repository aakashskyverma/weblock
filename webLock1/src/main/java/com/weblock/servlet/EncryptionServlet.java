package com.weblock.servlet;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.weblock.util.FileEncryptionUtil;
import com.weblock.util.DBUtil;

@WebServlet("/EncryptionServlet")
@MultipartConfig
public class EncryptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String key = request.getParameter("key");

        if (filePart != null && key != null && !key.isEmpty()) {
            try {
                String fileName = filePart.getSubmittedFileName();
                byte[] fileContent = filePart.getInputStream().readAllBytes();
                byte[] encryptedContent = FileEncryptionUtil.encrypt(fileContent, key);

                long fileId = saveEncryptedFile(fileName, encryptedContent);

                // Redirect to a page where the user can download the file by fileId
                response.sendRedirect("download.jsp?id=" + fileId);
            } catch (Exception e) {
                throw new ServletException("File encryption failed", e);
            }
        } else {
            response.sendRedirect("encryption.jsp");
        }
    }

    private long saveEncryptedFile(String fileName, byte[] encryptedContent) throws SQLException, IOException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO encrypted_files (file_content) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setBytes(1, encryptedContent);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1); // Return the generated fileId
                } else {
                    throw new SQLException("Failed to retrieve fileId");
                }
            }
        }
    }
}
