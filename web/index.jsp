<%-- 
    Document   : index
    Created on : 11/03/2020, 11:08:17 AM
    Author     : rober
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Index Page Assignment 1</title>
    </head>
    <body>
        <h1>Assignment 1 - Multi-Tier System</h1>
        <br/>
        <jsp:useBean id="today" class="java.util.Date"/>
        <c:out value="Todays date is"/>
        <fmt:formatDate value="${today}" type="date" dateStyle="full"/>
        <br/>
        <c:choose>
            <c:when test="${not empty param}">
                <c:out value="${fn:length(param)} request parameters:"/>
                <br/>
                <ul>
                    <c:forEach items="${param}" var="parameter">
                        <li>
                            <c:out value="name:${parameter.key} "/>
                            <c:out value="value:${parameter.value}"/>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <c:out value="No request parameters"/>
                <br/>
            </c:otherwise>
        </c:choose>
        <br/>
        <a href='<%= response.encodeURL("CreateNewUser.jsp")%>'>
            Create New User
        </a>
        <br>
        <a href='<%= response.encodeURL("login.jsp")%>'>
            Login
        </a>
    </body>
</html>
