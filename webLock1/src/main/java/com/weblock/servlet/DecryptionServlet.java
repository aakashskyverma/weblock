package com.weblock.servlet;

import com.weblock.util.FileEncryptionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/DecryptionServlet")
@MultipartConfig
public class DecryptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String key = request.getParameter("key");

        if (filePart != null && key != null && !key.isEmpty()) {
            try {
                String fileName = filePart.getSubmittedFileName();
                byte[] fileContent = filePart.getInputStream().readAllBytes();
                byte[] decryptedContent = FileEncryptionUtil.decrypt(fileContent, key);

                // Remove the errorMessage attribute if decryption is successful
                request.removeAttribute("errorMessage");
                request.getRequestDispatcher("decryption.jsp").forward(request, response);

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName.replace(".enc", ""));
                response.getOutputStream().write(decryptedContent);
                response.getOutputStream().flush();
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Invalid decryption key. Please try again.");
                request.getRequestDispatcher("decryption.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("decryption.jsp");
        }
    }
}
