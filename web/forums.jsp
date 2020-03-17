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
        <c:if test="${not empty sessionScope.error}">
            <p><strong>Error:</strong> <%= request.getSession().getAttribute("error")%></p>
            <% request.getSession().removeAttribute("error");%>
        </c:if>
        <c:if test="${not empty sessionScope.message}">
            <p><strong>Error:</strong> <%= request.getSession().getAttribute("message")%></p>
            <% request.getSession().removeAttribute("message");%>
        </c:if>

        <c:forEach var="forum" items="${requestScope.forums}">
            <a href='<%= response.encodeURL("getPosts?id=")%><c:out value="${forum.id}" />'>
                <p><c:out value="${forum.title}" /></p></a>
            <p><c:out value="${forum.description}" /></p>
        </c:forEach>
    </body>
</html>
