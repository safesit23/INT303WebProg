<%-- 
    Document   : Login
    Created on : Sep 18, 2018, 3:40:15 PM
    Author     : jatawatsafe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <style>
            body{
                padding: 10px;
                font-size: 1.2em;
            }
        </style>
        <h1>Login</h1>
        <form action="Login" method="post">
            Username: <input type="text" name="username" required />
            Password: <input type="password" name="password" required />
            <input type="submit" value="SUBMIT" />
        </form>
    </body>
</html>
