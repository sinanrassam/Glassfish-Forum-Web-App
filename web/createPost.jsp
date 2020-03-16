<%-- 
    Document   : createPost
    Created on : 11/03/2020, 10:29:46 AM
    Author     : sinan.rassam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Post.Post"%>

<c:if test="${sessionScope.user == null}">
    <%
        request.getSession().setAttribute("error", "You need to be logged in to see this page");
        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    %>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a New Post</title>
    </head>
    <body>
        <h1>Create a New Post</h1>
        <c:if test="${not empty sessionScope.error}">
            <p><strong>Error:</strong> <%= request.getSession().getAttribute("error")%></p>
            <% request.getSession().removeAttribute("error");%>
        </c:if>
        <c:if test="${not empty sessionScope.message}">
            <p><strong>Message:</strong> <%= request.getSession().getAttribute("message")%></p>
            <% request.getSession().removeAttribute("message");%>
        </c:if>
        <form action="createPost" method="POST">
            <p>
                Title:
                <input type="text" name="title"/>
            </p>
            <p>
                Description:
                <textArea name="description"></textarea>
            </p>
            <input type="submit"/>
        </form>
            
        <a href='<%= response.encodeURL(request.getContextPath())%>/getPosts'><input type="button" value="Load Posts"/></a>
            
        <h1> Forum posts: </h1>
        <%
            List<Post> posts = (ArrayList<Post>) request.getAttribute("posts");

            if (posts != null) {
                for (Post post : posts) {
                    out.print("Name: " + post.getUsername() + " Date: " + post.getCreationDate());
                    out.print("<br/>");
                    out.print("Post: <br/>" + post.getDescription());
                    out.print("<br/>");
                    out.print("<br/>");
                }
            }

        %>
        <br><a href='/animals/confirmation.jsp'> Return </a>
    </body>
</html>