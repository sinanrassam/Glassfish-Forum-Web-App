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
        <h2>Please Enter User Information</h2>
        <form method="post" action="CreateAccountServlet">
            <p>
                First Name:
                <input type="text" name="firstName"/>
            </p>
            <p>
                Last Name:
                <input type="text" name="lastName"/>
            </p>
            <p>
                Day:
                <input type="text" name="day"/>
                Month:
                    <select name="month">
                    <option value="1">January</option>
                    <option value="2">February</option>
                    <option value="3">March</option>
                    <option value="4">April</option>
                    <option value="5">May</option>
                    <option value="6">June</option>
                    <option value="7">July</option>
                    <option value="8">August</option>
                    <option value="9">September</option>
                    <option value="10">October</option>
                    <option value="11">November</option>
                    <option value="12">December</option>
                    </select>
                Year:
                <input type="text" name="year"/>
            </p>
            <p>
                Email Address:
                <input type="text" name="email"/>
            </p>
            <p>
                Gender:
                <input type="radio" name="gender"/>Male
                <input type="radio" name="gender"/>Female
            </p>
            <p>
                Username:
                <input type="text" name="username"/>
            </p>
            <p>
                Password:
                <input type="text" name="password"/>
            </p>
            <input type="submit" value="Create User"/>
        </form>
        <br/>
        <a href='<%= response.encodeURL(request.getContextPath())%>'>
            Return to main page
        </a>
    </body>
</html>