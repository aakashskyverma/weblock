<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.weblock.modal.User" %>
<%
    HttpSession session1 = request.getSession(false);
    if (session1 == null || session1.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    User user = (User) session1.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>File Decryption</title>
    <!-- Bootstrap CSS -->
    <%@include file="/include/header.jsp" %>
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/user.css" rel="stylesheet">
</head>
<body>
<%@include file="/include/navbar.jsp"%>
    <div class="container text-white">
        <div class="row justify-content-center mt-5">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header text-center">
                        <h3>File Decryption</h3>
                    </div>
                    <div class="card-body">
                        <form action="DecryptionServlet" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="file" style="font-size:20px;">Choose Encrypted File</label>
                                <input type="file" class="form-control-file" id="file" name="file" required>
                            </div>
                            <div class="form-group">
                                <label for="key" style="font-size:20px;">Decryption Key</label>
                                <input type="password" class="form-control" id="key" name="key" placeholder="Enter decryption key" required>
                            </div>
                            <button type="submit" class="btn btn-success btn-block">Decrypt</button>
                        </form>
                        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                            <div class="alert alert-danger mt-3" role="alert">
                                <%= errorMessage %>
                            </div>
                        <% } else{request.removeAttribute("errorMessage");}  %>
                       
                        
                        <% String decryptedFile = (String) request.getAttribute("decryptedFile"); %>
                        <% if (decryptedFile != null && !decryptedFile.isEmpty()) { %>
                            <div class="mt-3">
                                <a href="<%= decryptedFile %>" class="btn btn-primary btn-block" download>Download Decrypted File</a>
                            </div>
                        <% } %>
                    </div>
                    <div class="card-footer text-center">
                        <a href="encryption.jsp">Need to encrypt a file? Click here</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <%@include file="/include/footer.jsp" %>
</body>
</html>
