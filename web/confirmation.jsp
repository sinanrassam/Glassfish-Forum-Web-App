<%-- 
    Document   : confirmation
    Created on : 12/03/2020, 11:59:39 PM
    Author     : sinan.rassam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome </title>
    </head>
    <body>
        <h1>Welcome </h1>

    <c:if test="${not empty requestScope.User}">
        <c:out value="Customer bean found with name"/>
        <c:out value="${User.firstName}"/>
        <c:out value="${User.lastName}"/>
        <c:out value="and born on"/>
        <c:out value="${User.dob}"/>
        <c:out value="with gender"/>
        <c:out value="${User.gender}"/>
    </c:if>
</body>
</html>
