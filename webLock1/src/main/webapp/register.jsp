<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Register</title>
    <!-- Bootstrap CSS -->
     <%@include file="/include/header.jsp" %>
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/user.css" rel="stylesheet">
    
</head>
<body>
     <%@include file="/include/indexnav.jsp" %>
    <div class="container mt-5">
        <div class="row justify-content-center mt-5">
            <div class="col-md-6">
                <div class="card text-white">
                    <div class="card-header text-center">
                        <h3>Register</h3>
                    </div>
                    <div class="card-body">
                        <form action="RegisterServlet" method="post">
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
                            </div>
                            <button type="submit" class="btn btn-success btn-block">Register</button>
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        <a href="login.jsp">Already have an account? Login here</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS, Popper.js, and jQuery -->
     <%@include file="/include/footer.jsp"%>
</body>
</html>
