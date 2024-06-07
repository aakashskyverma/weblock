package com.weblock.servlet;

import com.weblock.util.DBUtil;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet("/ShareServlet")
public class ShareServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipientEmail = request.getParameter("email");
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            response.sendRedirect("download.jsp?error=Email is required");
            return;
        }

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT file_content FROM encrypted_files ORDER BY id DESC LIMIT 1");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Blob fileContent = resultSet.getBlob("file_content");
                byte[] fileBytes = fileContent.getBytes(1, (int) fileContent.length());
                String fileName = "encrypted_file.enc";

                sendEmailWithAttachment(recipientEmail, fileBytes, fileName);

                response.sendRedirect("download.jsp?success=File shared successfully");
            } else {
                response.sendRedirect("download.jsp?error=No encrypted files found");
            }
        } catch (SQLException | MessagingException e) {
            throw new ServletException("Error sharing file: " + e.getMessage(), e);
        }
    }

    private void sendEmailWithAttachment(String recipientEmail, byte[] fileBytes, String fileName) throws MessagingException {
        final String username = "weblock1262@gmail.com";
        final String password = "yhly ecmd mogb qmmv";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("your-email@example.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Encrypted File");

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Please find the encrypted file attached.");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new ByteArrayDataSource(fileBytes, "application/octet-stream");
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(fileName);
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
