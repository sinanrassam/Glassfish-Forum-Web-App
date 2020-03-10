<%-- 
    Document   : login
    Created on : 11/03/2020, 10:29:46 AM
    Author     : sinan.rassam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
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
