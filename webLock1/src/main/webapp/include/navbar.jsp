<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<nav class="navbar navbar-expand-lg navbar-light bg-transparent">
  <div class="container">
     <h1 ><a class="navbar-brand" href="index.jsp" style="font-size: 25px;"><span class="text-warning"><i class='bx bxl-xing'></i>Web</span><span>Lock</samp></a></h1>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto" style="font-size: 20px;">
        <li class="nav-item active ">
          <a class="nav-link text-warning" href="welcome.jsp"> Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="encryption.jsp">Encryption</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="decryption.jsp">Decryption</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="LogoutServlet">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
