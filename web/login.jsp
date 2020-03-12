<%-- 
    Document   : login
    Created on : 11/03/2020, 10:29:46 AM
    Author     : sinan.rassam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <c:choose>
            <c:when test="${not empty param}">
                <p><strong>Error:</strong> <%= request.getParameter("error")%></p>
            </c:when>
        </c:choose>
        <form action="login" method="POST">
            <p>
                Username:
                <input type="text" name="username"/>
            </p>
            <p>
                Password:
                <input type="password" name="password"/>
            </p>
            <input type="submit"/>
        </form>
    </body>
</html>
