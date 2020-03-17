<%-- 
    Document   : CreateDetails
    Created on : 11/03/2020, 10:39:10 AM
    Author     : rober
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${not empty sessionScope.user}">
    <%
        request.getSession().setAttribute("error", "You are already logged in");
        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/profile.jsp");
        dispatcher.forward(request, response);
    %>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Register | Forum</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container"
                 <a class="navbar-brand" href="<%= response.encodeURL(request.getContextPath())%>">Forum</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="<%= response.encodeURL(request.getContextPath())%>">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%= response.encodeURL(request.getContextPath() + "/getForums")%>">Forum</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav nav-right">
                        <li class="nav-item dropdown active">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                User
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="<%= response.encodeURL(request.getContextPath() + "/login.jsp")%>">Login</a>
                                <a class="dropdown-item active" href="<%= response.encodeURL(request.getContextPath() + "/register.jsp")%>">Register</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <c:if test="${not empty sessionScope.error}">
            <div class="alert alert-danger" role="alert">
                <strong>Error:</strong> <%= request.getSession().getAttribute("error")%>
            </div>
            <% request.getSession().removeAttribute("error");%>
        </c:if>

        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-info" role="alert">
                <strong>Message</strong> <%= request.getSession().getAttribute("message")%>
            </div>
            <% request.getSession().removeAttribute("message");%>
        </c:if>

        <div class="container">
            <h1>Please Enter User Information</h1>

            <form class="form-signin" action="register" method="post">
                <div class="form-group">
                    <input value="Hi" type="text" class="form-control" placeholder="First Name" name="firstName" required autofocus>
                </div>

                <div class="form-group">
                    <input value="Hi" type="text" class="form-control" placeholder="Last Name" name="lastName" required>
                </div>

                <div class="form-group">
                    <input type="date" class="form-control" placeholder="Date of Birth" name="dob" required>
                </div>

                <div class="form-group">
                    <input type="email" class="form-control" placeholder="Email Address" name="email" required>
                </div>

                <div class="form-group">
                    <label class="form-check-label">Gender</label>
                    <br />
                    <div class="input-group">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="gender-1" name="gender" value="Male" required>
                            <label class="form-check-label" for="gender-1">Male</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" id="gender-2" name="gender" value="Female" required>
                            <label class="form-check-label" for="gender-2">Female</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Username" name="username" required>
                </div>

                <div class="form-group">
                    <input value="test123" type="password" class="form-control" placeholder="Password" name="password" required>
                </div>

                <button class="btn btn-primary btn-block" type="submit">Register</button>
            </form>
            <br/>

            <div class="text-center">
                <a href='<%= response.encodeURL(request.getContextPath() + "/login.jsp")%>'>
                    Already have an account yet?
                </a>
                <br />
                <a href='<%= response.encodeURL(request.getContextPath())%>'>
                    Return to main page
                </a>
            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>