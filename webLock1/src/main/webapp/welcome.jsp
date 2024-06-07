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
<html>
<head>
<meta charset="ISO-8859-1">
 <%@include file="/include/header.jsp" %>
  <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/user.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
 <%@include file="/include/navbar.jsp"%>


	<div class="container-wrapper">
    <div class="container " data-aos="fade-up">
        <div class="row justify-content-center text-white">
            <div class="col-lg-10 pt-4 pt-lg-0 order-2 order-lg-1 content" style="text-align: justify;" data-aos="fade-right" data-aos-delay="100">
                <h1>Welcome To WebLock</h1>
            </div>
        </div>
    </div>
</div>

		
 <%@include file="/include/footer.jsp"%>
</body>
</html>