<%-- 
    Document   : CreateDetails
    Created on : 11/03/2020, 10:39:10 AM
    Author     : rober
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Creation</title>
    </head>
    <body>
        <h1>Please Enter User Information</h1>
        <form method="post" action="CreateAccountServlet">
            <p>
                First Name:
                <input type="text" name="firstName" value="steven"/>
            </p>
            <p>
                Last Name:
                <input type="text" name="lastName" value="steven"/>
            </p>
            <p>
                Date of Birth:
                <input type="text" name="dob"/>
            </p>
            <p>
                Email Address:
                <input type="text" name="email" value="steven@steve.com"/>
            </p>
            <p>
                Gender:
                <input type="radio" name="gender" value="male"/>Male
                <input type="radio" name="gender" value="female"/>Female
            </p>
            <p>
                Username:
                <input type="text" name="username" value="steven"/>
            </p>
            <p>
                Password:
                <input type="text" name="password" value="steven"/>
            </p>
            <input type="submit" value="Search"/>
        </form>
        <br/>
        <a href='<%= response.encodeURL(request.getContextPath())%>'>
            Return to main page
        </a>
    </body>
</html>