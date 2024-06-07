<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Download Encrypted File</title>
    <!-- Bootstrap CSS -->
     <%@include file="/include/header.jsp" %>
     <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/user.css" rel="stylesheet">
</head>
<body>
<%@include file="/include/navbar.jsp"%>
    <div class="container mt-5 text-white">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header text-center mt-3">
                        <h3>Download Encrypted File</h3>
                    </div>
                    <div class="card-body">
                        <p>Click the button below to download the latest encrypted file:</p>
                        <a href="DownloadServlet" class="btn btn-primary btn-block">Download</a>
                        <hr>
                        <form action="ShareServlet" method="post">
                            <div class="form-group">
                                <label for="email">Recipient's Email</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Enter recipient's email" required>
                            </div>
                            <button type="submit" class="btn btn-secondary btn-block">Share via Email</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <%@include file="/include/footer.jsp"%>
</body>
</html>