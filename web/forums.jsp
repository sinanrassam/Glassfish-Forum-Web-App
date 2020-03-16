<%-- 
    Document   : forum
    Created on : 17/03/2020, 11:09:29 AM
    Author     : sinan.rassam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Post.Post"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forums</title>
    </head>
    <body>
        <h1>Forums</h1>
        <c:forEach var="forum" items="${forums}">
            <p><c:out value="${forum.id}" /></p>
            <p><c:out value="${forum.title}" /></p>
            <p><c:out value="${forum.author}" /></p>
            <p><c:out value="${forum.price}" /></p>
    </c:forEach>
</body>
</html>
