<%-- 
    Document   : CreateDetails
    Created on : 11/03/2020, 10:39:10 AM
    Author     : rober
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Edit Profile | Forum</title>
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
                        <li class="nav-item dropdown">
                            <c:choose>
                                <c:when test="${not empty sessionScope.user}">
                                    <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Welcome ${user.username}
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item active" href="<%= response.encodeURL(request.getContextPath() + "/profile.jsp")%>">Profile</a>
                                        <a class="dropdown-item" href="<%= response.encodeURL(request.getContextPath() + "/logout")%>">Logout</a>
                                    </div>
                                </c:when>
                            </c:choose>
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

            <form class="form-signin" action="updateDetails" method="post">                
                <div class="form-group">
                    <label class="form-check-label">First Name</label>
                    <input type="text" class="form-control" value="<c:out value="${user.firstName}"/>" name="firstName" required>
                </div>

                <div class="form-group">
                    <label class="form-check-label">Last Name</label>
                    <input type="text" class="form-control" value="<c:out value="${user.firstName}"/>" name="lastName" required>
                </div>

                <div class="form-group">
                    <label class="form-check-label">Date Of Birth</label>
                    <input type="date" class="form-control" value="<c:out value="${user.dob}"/>" name="dob" required>
                </div>

                <div class="form-group">
                    <label class="form-check-label">Email Address</label>
                    <input type="email" class="form-control" value="<c:out value="${user.email}"/>" name="email" required readonly>
                </div>

                <div class="form-group">
                    <label class="form-check-label">Gender</label>
                    <br />
                    <div class="input-group">
                        <div class="form-check form-check-inline">
                            <c:set var="gender1" value="male"/>
                            <c:set var="gender" value="${user.gender}"/>
                            <input class="form-check-input" type="radio" id="gender-1" name="gender" 
                                   <c:if test="${fn:containsIgnoreCase(user.gender, 'male')}">checked</c:if>
                                       value="Male" required>
                                   <label class="form-check-label" for="gender-1">Male</label>
                            </div>
                            <div class="form-check form-check-inline">
                            <c:set var="gender2" value="Female"/>
                            <input class="form-check-input" type="radio" id="gender-2" name="gender"
                                   <c:if test="${fn:containsIgnoreCase(user.gender, 'female')}">checked</c:if>
                                       value="female" required>
                                   <label class="form-check-label" for="gender-2">Female</label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-check-label">Username</label>
                        <input type="text" class="form-control" value="<c:out value="${user.username}"/>" name="username" required readonly>
                </div>

                <button class="btn btn-primary btn-block" type="submit">Update Details</button>
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