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
        <c:if test="${not empty sessionScope.error}">
            <p><strong>Error:</strong> <%= request.getSession().getAttribute("error")%></p>
            <% request.getSession().removeAttribute("error");%>
        </c:if>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <h1>Welcome <c:out value="${user.firstName}"/></h1>
                <table style="width:25%">
                    <tr>
                        <th>First Name:</th>
                        <td><c:out value="${user.firstName}"/></td>
                        <th>
                    </tr>
                    <tr>
                        <th>Last Name:</th>
                        <td><c:out value="${user.lastName}"/></td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td><c:out value="${user.email}"/></td>
                    </tr>
                    <tr>
                        <th>Date of Birth:</th>
                        <td><c:out value="${user.dob}"/></td>
                    </tr>
                    <tr>
                        <th>Age:</th>
                        <td><c:out value="${user.age}"/></td>
                    </tr>
                    <tr>
                        <th>Gender:</th>
                        <td><c:out value="${user.gender}"/></td>
                    </tr>
                    <tr>
                        <th>Username:</th>
                        <td><c:out value="${user.username}"/></td>
                    </tr>
                </table>
                <br />
                <a href='<%= response.encodeURL(request.getContextPath() + "/getForums")%>'>View Forums>></a><br><br>
                <a href='<%= response.encodeURL(request.getContextPath() + "/logout")%>'>Logout>></a>
            </c:when>
            <c:otherwise>
                <%
                    request.getSession().setAttribute("error", "You need to be logged in to see this page");
                    RequestDispatcher dispatcher = getServletContext().
                            getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                %>
            </c:otherwise>
        </c:choose>
    </body>
</html>
