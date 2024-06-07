<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/include/header.jsp" %>
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/user.css" rel="stylesheet">
      
</head>
<body>
    <%@include file="/include/indexnav.jsp" %>
    <div class="container">
        <div class="row justify-content-center mt-5">
            <div class="col-md-6">
                <div class="card  " >
                    <div class="card-header text-center text-white">
                        <h3>Login</h3>
                    </div>
                    <div class="card-body text-white">
                        <form action="LoginServlet" method="post">
                            <div class="form-group ">
                                <label for="username">Username</label>
                                <input type="text" class="form-control text-grey" id="username" name="username" placeholder="Enter username" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Login</button>
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        <a href="register.jsp">Don't have an account? Register here</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <%@include file="/include/footer.jsp"%>
</body>
</html>
ml>