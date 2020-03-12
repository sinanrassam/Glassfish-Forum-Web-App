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

        <c:if test="${not empty sessionScope.user}">
            <table style="width:25%">
                <tr>
                    <th>First Name:</th>
                    <td> <c:out value="${user.firstName}"/></td>
                    <th>
                </tr>
                <tr>
                    <th>Last Name:</th>
                    <td> <c:out value="${user.lastName}"/></td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td> <c:out value="${user.email}"/></td>
                </tr>
                <tr>
                    <th>Date of Birth:</th>
                    <td> <c:out value="${user.dob}"/></td>
                </tr>
                <tr>
                    <th>Age:</th>
                    <td> <c:out value="${user.age}"/></td>
                </tr>
                <tr>
                    <th>Gender:</th>
                    <td> <c:out value="${user.gender}"/></td>
                </tr>
                <tr>
                    <th>Username:</th>
                    <td> <c:out value="${user.username}"/></td>
                </tr>
            </table>
        </c:if>
    </body>
</html>
